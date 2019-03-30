package com.kunlinr.lifealien.excel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.kunlinr.lifealien.excel.annotation.ExcelColumn;

/**
 * excel工具类
 * @author kunlingou
 *
 */
public class ExcelUtil {
	private final static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

	private final static String EXCEL2003 = "xls";
	private final static String EXCEL2007 = "xlsx";
	
	public static <T> List<T> readExcel(String path, Class<T> cls, File file){
		MultipartFile multipartFile = getMultipartFile(file);
		return readExcel(path, cls, multipartFile);
		
	}
	
	public static <T> List<T> readExcel(String path, Class<T> cls, MultipartFile file) {
		Workbook workbook = getWorkbook(file);
		Assert.notNull(workbook,"workbook must not be null");
		List<T> dataList = new ArrayList<>();
		try {
			List<Field> fields = Stream.of(cls.getDeclaredFields()).collect(Collectors.toList());
			Map<String, List<Field>> classMap = getClassMap(fields);
			Map<Integer, List<Field>> reflectionMap = null;//索引-->columns
            Sheet sheet = workbook.getSheetAt(0);//默认读取第一个sheet
            boolean firstRow = true;
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;//忽略空白行
                if (firstRow) {//首行  提取注解
                	reflectionMap = getReflectionMap(row, classMap);
                    firstRow = false; continue;
                }
                try {
                    T t = cls.newInstance();
                    if (handleField(t, row, reflectionMap)) dataList.add(t);
                    else log.warn(String.format("row:%s is blank ignore!", i));
                } catch (Exception e) {
                    log.error(String.format("parse row:%s exception!", i), e); 
                } 
            }
		} catch (Exception e) {
			log.error(String.format("parse excel exception!"), e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					log.error(String.format("parse excel exception!"), e);
				}
			}
		}
		return dataList;
	}
	
	private static <T> boolean handleField(T t, Row row, Map<Integer, List<Field>> reflectionMap) {
		boolean allBlank = true;
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            if (reflectionMap.containsKey(j)) {
                Cell cell = row.getCell(j);
                String cellValue = getCellValue(cell);
                if (StringUtils.hasText(cellValue)) allBlank = false;
                List<Field> fieldList = reflectionMap.get(j);
                fieldList.forEach(
                        x -> {
                            try {
                                handleField(t, cellValue, x);
                            } catch (Exception e) {
                                log.error(String.format("reflect field:%s value:%s exception!", x.getName(), cellValue), e);
                            }
                        }
                );
            }
        }
		return !allBlank;
	}

	private static Map<Integer, List<Field>> getReflectionMap(Row row, Map<String, List<Field>> classMap) {
		Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);
            String cellValue = getCellValue(cell);
            if (classMap.containsKey(cellValue)) {
                reflectionMap.put(j, classMap.get(cellValue));
            }
        }
		return reflectionMap;
	}

	private static Map<String, List<Field>> getClassMap(List<Field> fields) {
		Map<String, List<Field>> classMap = new HashMap<>();
		fields.forEach( field -> {
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                String value = annotation.value();
                if (!StringUtils.hasText(value)) return;
                if (!classMap.containsKey(value)) {
                    classMap.put(value, new ArrayList<>());
                }
                field.setAccessible(true);
                classMap.get(value).add(field);
            }
        });
		return classMap;
	}

	private static void fileNameCheck(String fileName){
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new IllegalArgumentException("上传文件格式不正确");
		}
	}

	private static Workbook getWorkbook(MultipartFile file) {
		fileNameCheck(file.getOriginalFilename());
		String fileName = file.getOriginalFilename();
		Workbook workbook = null;
		InputStream is;
		try {
			is = file.getInputStream();
			if (fileName.endsWith(EXCEL2007)) {
				workbook = new XSSFWorkbook(is);
			}else if (fileName.endsWith(EXCEL2003)) {
				workbook = new HSSFWorkbook(is);
			}
		} catch (IOException e) {
			log.error("get Workbook exception");
		}
		return workbook;
	}

	private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        if (type == null || type == void.class || !StringUtils.hasText(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            //数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, Integer.parseInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, Long.parseLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, Byte.parseByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, Short.parseShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, Double.parseDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, Float.parseFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, value.charAt(0));
            } else if (type == boolean.class) {
                field.set(t, Boolean.parseBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, Boolean.valueOf(value));
        } else if (type == Date.class) {
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }
	
	private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimWhitespace(cell.getStringCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return StringUtils.trimWhitespace(cell.getCellFormula());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }

    }
	
	public static MultipartFile getMultipartFile(File file){
		FileItem fileItem = null;
		FileInputStream fileInputStream = null;
		try {
			fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false, file.getName(),
					(int) file.length(), file.getParentFile());
			fileInputStream = new FileInputStream(file);
			IOUtils.copy(fileInputStream, fileItem.getOutputStream());
		} catch (IOException ex) {
			log.error("get MultipartFile exception");
		} finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					log.error("get MultipartFile exception");
				}
			}
		}
		return new CommonsMultipartFile(fileItem);
	}
	
}
