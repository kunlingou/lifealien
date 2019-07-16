package com.kunlinr.lifealien.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@SuppressWarnings("unused")
public class Request {
	private InputStream input;
	private String uri;
	
	public Request(InputStream input) {
		this.input = input;
		parse();
	}

	/**
	 * 从inputStream中读取request信息，获取uri值。
	 */
	public void parse() {
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		}catch (Exception e) {
			e.printStackTrace();
			i = -1;
		}
		for(int j=0;j<i;j++) {
			request.append((char)buffer[j]);
		}
		uri = parseUri(request.toString());
	}
	
	/**
	 * requestString形式如下：
     * GET /index.html HTTP/1.1
     * Host: localhost:8080
     * Connection: keep-alive
     * Cache-Control: max-age=0
     * ...
	 * 获取/index.html字符串
	 * @param string
	 * @return
	 */
	private String parseUri(String string) {
		int index1,index2;
		index1 = string.indexOf(' ');
		if (index1 != -1) {
            index2 = string.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return string.substring(index1 + 1, index2);
        }
		return "";
	}

	public String getUri() {
        return uri;
    }
}
