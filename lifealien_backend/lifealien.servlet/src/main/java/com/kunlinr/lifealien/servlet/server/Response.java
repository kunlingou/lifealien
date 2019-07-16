package com.kunlinr.lifealien.servlet.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Response {
	private static final int BUFFER_SIZE = 1024;
	private Request request;
	private OutputStream output;
	
	public Response(OutputStream output) {
        this.output = output;
    }

	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * 发送静态资源
	 * @throws IOException
	 */
	public void sendStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            //将web文件写入到OutputStream字节流中
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
            	fis = new FileInputStream(file);
            	List<byte[]> contentbytes = new ArrayList<>();
            	int ch = fis.read(bytes, 0, BUFFER_SIZE);
            	int size = 0;
                while (ch != -1) {
                	contentbytes.add(bytes);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                    size += BUFFER_SIZE;
                }
                String head = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n"
                        + "Content-Length: "+size+"\r\n" + "\r\n";
                byte[] headbytes = head.getBytes();
                output.write(headbytes,0,headbytes.length);
                for(byte[] contentbyte:contentbytes) {
                	output.write(contentbyte);
                }
            } else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
                        + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString());
        } finally {
            if (fis != null)
                fis.close();
        }
	}
}
