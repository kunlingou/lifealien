package com.kunlinr.lifealien.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileReaderBuilder {
	
	private final static Logger log = LoggerFactory.getLogger(FileReaderBuilder.class);
	
	public static <T> ExcelFileReader<T> newExcelFileReader(File file,Class<T> clazz) {
		MultipartFile file0 = (file instanceof MultipartFile)?(MultipartFile) file:getMultipartFile(file);
		return new ExcelFileReader<T>(file0, clazz);
	}
	
	public static <T> CsvFileReader<T> newCsvFileReader(File file,Class<T> clazz) {
		MultipartFile file0 = (file instanceof MultipartFile)?(MultipartFile) file:getMultipartFile(file);
		return new CsvFileReader<T>(file0, clazz);
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
