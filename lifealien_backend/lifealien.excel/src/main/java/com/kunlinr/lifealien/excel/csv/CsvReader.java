package com.kunlinr.lifealien.excel.csv;

import java.io.InputStream;
import java.nio.charset.Charset;

public class CsvReader extends com.csvreader.CsvReader implements AutoCloseable{

	public CsvReader(InputStream var1, char var2, Charset var3) {
		super(var1, var2, var3);
	}

}