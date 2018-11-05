package cn.e3mall.fast;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.util.FastDFSClient;

public class FastDfsTest {
	@Test
	public void testUpload() throws Exception{
		//创建配置文件 内容为tracker服务器的地址
		ClientGlobal.init("D:/workspace/e3-manager-web/src/main/resources/conf/client.conf");
		//使用全局对象加载配置文件
		TrackerClient trackerClient =new TrackerClient();
		//创建TrackerClient对象
		TrackerServer trackerServer=trackerClient.getConnection();
		//通过TrackerClient获得TrackerServer对象
		StorageServer storageServer =null;
		//创建StorageServer的引用 可以为null
		StorageClient storageClient =new StorageClient(trackerServer,storageServer);
		//创建StorageClient上传文件
		String[] strings = storageClient.upload_file("E:/monkey.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
		
	}
	
	@Test
	public void testFastDfsClient() throws Exception{
		//配置文件路径
		FastDFSClient fastDFSClient=new FastDFSClient("D:/workspace/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("G:/Picture/2015103157654285.jpg");
		System.out.println(string);
	}
}
