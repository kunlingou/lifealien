package com.kunlinr.lifealien.excel.reader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.kunlinr.lifealien.excel.FieldInfo;
import com.kunlinr.lifealien.excel.annotation.ExcelColumn;

public class ExcelFileReader<T> implements IFileReader<T>{
	
	private final static Logger log = LoggerFactory.getLogger(ExcelFileReader.class);
	
	private final static String EXCEL2003 = "xls";
	private final static String EXCEL2007 = "xlsx";
	
	MultipartFile file;
	
	Class<T> entityClass;
	
	Workbook workbook;
	
	Map<Integer,FieldInfo> fieldInfos;
	
	public ExcelFileReader() {
	}
	
	public ExcelFileReader(MultipartFile file,Class<T> entityClass){
		this.file = file;
		this.entityClass = entityClass;
		this.fieldInfos = new HashMap<Integer, FieldInfo>();
	}
	

	public void getFieldInfos() throws IOException {
		Sheet sheet = workbook.getSheetAt(0);//默认读取第一个sheet
		Row row = sheet.getRow(0);
		fieldInfos.clear();
		Map<String, FieldInfo> temp = new HashMap<>();
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            String cellValue = getCellValue(row.getCell(j));
            if(!StringUtils.isEmpty(cellValue) && !temp.containsKey(cellValue)) {
            	FieldInfo fieldInfo = new FieldInfo();
            	fieldInfo.setTitle(cellValue);
            	fieldInfo.setIndex(j);
            	fieldInfos.put(j, fieldInfo);
            	temp.put(cellValue, fieldInfo);
            }
        }
		
		List<Field> fields = Stream.of(entityClass.getDeclaredFields()).collect(Collectors.toList());
		fields.forEach( field -> {
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                String value = annotation.value();
                if (temp.containsKey(value)) {
                	field.setAccessible(true);
                	temp.get(value).getFields().add(field);
                }
            }
        });
		temp.clear();
	}


	@Override
	public List<T> read() {
		List<T> dataList = new ArrayList<>();
		try (Workbook workbook = getWorkbook()){
			Assert.notNull(workbook,"workbook must not be null");
			getFieldInfos();
            Sheet sheet = workbook.getSheetAt(0);//默认读取第一个sheet
            for (int i = sheet.getFirstRowNum()+1,len = sheet.getLastRowNum(); i <= len; i++) {
                Row row;T t;
                if ((row=sheet.getRow(i)) != null && (t = entityClass.newInstance())!=null
                		&& handleField(t, row)) {
                	dataList.add(t);
                }else {
                	log.warn(String.format("row:%s is blank ignore!", i));
                }
            }
		} catch (Exception e) {
			log.error(String.format("parse excel exception!"), e);
		}
		return dataList;
	}
	
	protected boolean handleField(T t, Row row) {
		boolean allBlank = true;
		for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
            if (fieldInfos.containsKey(j)) {
                Cell cell = row.getCell(j);
                String cellValue = getCellValue(cell);
                if (StringUtils.hasText(cellValue)) allBlank = false;
                Set<Field> fieldList = fieldInfos.get(j).getFields();
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
	
	public Workbook getWorkbook() {
		fileNameCheck(file.getOriginalFilename());
		String fileName = file.getOriginalFilename();
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
	
	private void fileNameCheck(String fileName){
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			throw new IllegalArgumentException("上传文件格式不正确");
		}
	}
	
	protected String getCellValue(Cell cell) {
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
}
