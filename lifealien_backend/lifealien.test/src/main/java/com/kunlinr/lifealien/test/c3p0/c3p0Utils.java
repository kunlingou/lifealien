package com.kunlinr.lifealien.test.c3p0;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.mchange.v2.c3p0.cfg.C3P0Config;
import com.mchange.v2.c3p0.impl.C3P0Defaults;
import com.mchange.v2.cfg.DelayedLogItem;
import com.mchange.v2.cfg.DelayedLogItem.Level;
import com.mchange.v2.cfg.MultiPropertiesConfig;
import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

public class c3p0Utils {
//	static ComboPooledDataSource  dataSource = new ComboPooledDataSource();
	private String dataSourceName;
	
	
	public void getConnection() throws SQLException {
		dataSourceName = C3P0Config.initializeStringPropertyVar("dataSourceName", C3P0Defaults.dataSourceName());
		System.out.println(dataSourceName);
	}
	public void propertiesFromSource() throws FileNotFoundException, Exception
    {
	String identifier = "/person.txt";
	InputStream rawStream = null;
//	InputStream rawStream = MultiPropertiesConfig.class.getResourceAsStream( identifier );
//	InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(identifier);
	this.getClass().getClassLoader().getResource("");
	if ( rawStream != null )
	{
	    InputStream pis = new BufferedInputStream( rawStream );
	    Properties p = new Properties();
	    List<DelayedLogItem> messages = new LinkedList<DelayedLogItem>();
	    try
	    { p.load( pis ); }
	    finally
	    {
		try { if ( pis != null ) pis.close(); } //ensures closuer of nested rawStream as well
		catch (IOException e) 
		    { messages.add( new DelayedLogItem( Level.WARNING, "An IOException occurred while closing InputStream from resource path '" + identifier + "'.", e ) ); }
	    }
	    new Parse(p, messages);
	}
	else
	    throw new FileNotFoundException( String.format("Resource not found at path '%s'.", identifier) );
    }
}
