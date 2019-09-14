package com.kunlinr.lifealien.excel.reader;

import java.util.List;

public interface IFileReader<T>{
	
	List<T> read();
	
}
