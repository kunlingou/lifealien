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
			/**@see java.net.DualStackPlainSocketImpl*/
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
