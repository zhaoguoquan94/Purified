package com.dagger_studio.purified;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaMuxer.OutputFormat;

public class DAO{
	private DAO(){
		
	}

	public static DAO getInstance(){
		return dao;
	}
	
	public void initValues(){
		/**
		 * 初始化了drawer的头像文件，并写入目录文件
		 */
		File file = new File(appPath+"/images");
		int count;
		if (!file.exists()){
			System.out.println("创建路径:"+appPath+"/images");
			file.mkdirs();
		}else{
			System.out.println("路径已存在");
		}
		file = new File(appPath+"/images/avatar.jpg");
		if (!file.exists()){
			
			try {
				InputStream is = activity.getResources().openRawResource(R.drawable.logo1);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[8192];
				int flag = 0;
				while((count = is.read(buffer)) > 0){
					fos.write(buffer, 0, count);
					flag++;
				}
				System.out.println("复制资源图片到"+appPath+"/images/avatar.jpg");
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("复制资源图片到"+appPath+"images/avatar.jpg 失败!");
			}
		}else{
			System.out.println("图片已存在:"+appPath+"/images/avatar.jpg");
		}
	}
	
	public void setActivity(Activity activity){
		this.activity = activity;
	}
	
	private static DAO dao = new DAO();
	private String appPath = "";
	public String getAppPath() {
		return appPath;
	}
	private Activity activity;



	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	
}
