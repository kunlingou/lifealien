# 实例-实现一个简单的java Web容器

## 技术栈

- java.net.Socket
- java.net.ServerSocket

## 执行流程

1.  创建一个ServerSocket对象；
2.  调用ServerSocket对象的accept方法，等待连接，连接成功会返回一个Socket对象，否则一直阻塞等待；
3.  从Socket对象中获取InputStream和OutputStream字节流，这两个流分别对应request请求和response响应；
4.  处理请求：读取InputStream字节流信息，转成字符串形式，并解析，这里的解析比较简单，仅仅获取uri(统一资源标识符)信息;
5.  处理响应：根据解析出来的uri信息，从WEB_ROOT目录中寻找请求的资源资源文件, 读取资源文件，并将其写入到OutputStream字节流中；
6.  关闭Socket对象；
7.  转到步骤2，继续等待连接请求；

## 代码实现

### 服务器

```java
package com.kunlinr.lifealien.servlet.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	/**WEB_ROOT是HTML和其它文件存放的目录. 这里的WEB_ROOT为工作目录下的WebContent目录*/
	public static final String WEB_ROOT = System.getProperty("user.dir")+File.separator+"WebContent";
	//关闭服务命令
	public static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	/**
	 * 等待连接请求
	 */
	private void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			//服务器套接字对象
			serverSocket = new ServerSocket(port,0,InetAddress.getByName("127.0.0.1"));
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		//循环等待请求
		while(true) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				//创建Request
				Request request = new Request(input);
				//检查是否关闭服务命令
				if(request.getUri().equals(SHUTDOWN_COMMAND)) {
					break;
				}
				//创建Response
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				//关闭socket
				socket.close();
			}catch(Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
```

### Request

```java
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
```

### Response

```
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
```
### 简单页面
```
<html>
	<head>
		<title>Hello,this is your own Page!</title>
	</head>
	<body>
		Everything is Simple!
	</body>
</html>
```

## 效果

1. 启动服务

![1563286711709](https://raw.githubusercontent.com/kunlingou/lifealien/master/pic/1563286711709.png)

2. 发送请求

![1563286971745](https://raw.githubusercontent.com/kunlingou/lifealien/master/pic/1563286971745.png)

3. 返回正文

```
<html>
	<head>
		<title>Hello,this is your own Page!</title>
	</head>
	<body>
		Everything is Simple!
	</body>
</html>
```



## 参考文档

- [一个简单的Java web服务器实现](https://www.cnblogs.com/chenpi/p/5602171.html)