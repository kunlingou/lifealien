package com.kunlinr.lifealien.excel.reader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kunlinr.lifealien.excel.FieldInfo;
import com.kunlinr.lifealien.excel.annotation.ExcelColumn;
import com.kunlinr.lifealien.excel.csv.CsvReader;

public class CsvFileReader<T> extends ExcelFileReader<T> implements IFileReader<T>{
	
	private final static Logger log = LoggerFactory.getLogger(CsvFileReader.class);
	
	CsvReader csvReader;
	
	public CsvFileReader(MultipartFile file,Class<T> entityClass){
		super(file, entityClass);
	}

	@Override
	public List<T> read() {
		List<T> dataList = new ArrayList<>();
		try (CsvReader csvReader = getCsvReader()){
			Assert.notNull(csvReader,"workbook must not be null");
			getFieldInfos();
            for (int i = 1; csvReader.readRecord(); i++) {
                T t;
                if ((t = entityClass.newInstance())!=null
                		&& handleField(t, csvReader)) {
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
	
	public CsvReader getCsvReader() throws IOException {
		csvReader = new CsvReader(file.getInputStream(), ',', Charset.forName("UTF-8"));
		return csvReader;
	}
	
	public void getFieldInfos() throws IOException {
		fieldInfos.clear();
		if (csvReader.readRecord()) {
			String[] headers = csvReader.getValues();
			Map<String, FieldInfo> temp = new HashMap<>();
			for (int j = 1; j <= headers.length; j++) {
	            String cellValue = headers[j-1];
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
	}
	
	protected boolean handleField(T t, CsvReader row) throws IOException {
		boolean allBlank = true;
		for (int j = 1,len = row.getColumnCount(); j <= len; j++) {
            if (fieldInfos.containsKey(j)) {
                String cellValue = row.get(j);
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
}
