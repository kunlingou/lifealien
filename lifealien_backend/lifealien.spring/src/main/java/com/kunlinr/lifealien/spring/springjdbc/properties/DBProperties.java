package com.kunlinr.lifealien.spring.springjdbc.properties;

import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.KEYSLIST;

import java.util.Properties;

public class DBProperties extends Properties{
	private static final long serialVersionUID = -7347262819733518004L;

	public DBProperties(Properties newProps) {
		for(String key:KEYSLIST) {
			setProperty(key,newProps.getProperty(key));
		}
	}
	public boolean equals(Object obj) {
		if(obj instanceof Properties) {
			Properties newProps = (Properties) obj;
			boolean isEquals = true;
			for(String key:KEYSLIST) {
				isEquals = isEquals && getProperty(key).equals((newProps.get(key)));
			}
			return isEquals;
		}else {
			return super.equals(obj);
		}
    }
}
