package com.dagger_studio.purified;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.MediaMuxer.OutputFormat;

public class DAO{
	private DAO(){
	}
	
	/**
	 * 
	 * @return 返回Dao的单例，使用时须调用setActivity和setAppPath方法
	 */
	public static DAO getInstance(){
		return dao;
	}
	
	/**
	 * 初始化了drawer的头像文件，并写入目录文件
	 */

	public void initValues(){
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
	
	/**
	 * 
	 * @return 返回列表中对应的url，传递给WebActivity
	 * 
	 * @author alex
	 */
	public String[] getUrls(){
		String[] strs = {"", "http://today.hit.edu.cn", "http://today.hit.edu.cn/depart/26.htm", "http://www.international.hit.edu.cn/"};
		return strs;
	}
	
	/**
	 * 
	 * @return 返回侧滑边栏的目录内容
	 */
	public List<String> getCollectionList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("我的收藏");
    	list.add(h1+"哈工大新闻");
    	list.add(h2+"本科生院新闻模块");
    	list.add(h2+"国际合作处信息板");
    	list.add(h1+"IT相关");
    	list.add(h2+"Google I/O大会");
    	list.add(h2+"云计算");
    	list.add(h1+"网易新闻");
    	list.add(h2+"世界杯赛程板块");
    	return list;
		
	}
	
	public String getAvatarImagePath(Activity activity){
		return activity.getApplicationContext().getFilesDir().getAbsolutePath()+"/images/avatar.jpg";
	}
	
	
	public String getAppPath() {
		return appPath;
	}
	
	public void setActivity(Activity activity){
		this.activity = activity;
	}
	
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	
	private static DAO dao = new DAO();
	private String appPath = "";
	
	
	
	private Activity activity;

	
	
	private String h1="  ·";
	private String h2= "    ·";
	
}
