package com.kunlinr.lifealien.filesystem;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			ClientGlobal.initByProperties("config/fastdfs-client.properties");
			System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
			System.out.println("charset=" + ClientGlobal.g_charset);

			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			StorageClient1 client = new StorageClient1(trackerServer, storageServer);

			NameValuePair[] metaList = new NameValuePair[1];
			metaList[0] = new NameValuePair("fileName", "1.png");
			String fileId = client.upload_file1("C:\\Users\\kunlingou\\Desktop\\1.png", "png", metaList);
			System.out.println("upload success. file id is: " + fileId);

//			int i = 0;
//			while (i++ < 10) {
//				byte[] result = client.download_file1(fileId);
//				System.out.println(i + ", download result is: " + result.length);
//			}

			trackerServer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
