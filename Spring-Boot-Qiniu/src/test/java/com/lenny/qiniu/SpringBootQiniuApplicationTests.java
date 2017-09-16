package com.lenny.qiniu;

import com.lenny.qiniu.config.QiniuServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootQiniuApplicationTests {

	// 上传文件测试
	@Test
	public void testUploadImage() {
		String filePath = "/home/zly/project/gitwk/Spring-Boot-Learn/Spring-Boot-Qiniu/src/main/resources/pic1.jpg";
		File file = new File(filePath);
		if(!file.exists()){
			System.out.println("file is not exists ....");
		}else {
			System.out.println("start upload image ....");
			String fileName = file.getName();
			String fileURL = QiniuServer.getInstance().uploadImage(filePath,fileName);
			System.out.println("finished upload image , fileUrl is " + fileURL );
		}
	}




}
