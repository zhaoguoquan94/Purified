package com.dagger_studio.purified.com.dagger_studio.purified.DAO;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasementDAO{
	private BasementDAO(){
	}

	/**
	 *
	 * @return 返回Dao的单例，使用时须调用setActivity和setAppPath方法
	 */
	public static BasementDAO getInstance(){
		return basementDAO;
	}

	/**
	 * 初始化了drawer的头像文件，并写入目录文件
	 */

	public void initValues(){
        sharedPreferences = activity.getSharedPreferences(BasementDAO.LOGIN_INFO_SHAREDPREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String avatarImagePath = sharedPreferences.getString("avatar_path", null);
        if (avatarImagePath==null){
            avatarImagePath = activity.getApplicationContext().getFilesDir().getAbsolutePath()+"/images/avatar.jpg";
        }
        else{
            File file = new File(avatarImagePath);
            int count;
            if (!file.exists()){
                Log.d("DAO里面的头像图片加载", "头像文件加载过，但丢失");
            }
        }



//		int count;
//		if (!file.exists()){
//			System.out.println("创建路径:"+appPath+"/images");
//			file.mkdirs();
//		}else{
//			System.out.println("路径已存在");
//		}
//		file = new File(avatarImagePath);
//		if (!file.exists()){
//
//			try {
//				InputStream is = activity.getResources().openRawResource(R.drawable.icon_user_default);
//				FileOutputStream fos = new FileOutputStream(file);
//				byte[] buffer = new byte[8192];
//				int flag = 0;
//				while((count = is.read(buffer)) > 0){
//					fos.write(buffer, 0, count);
//					flag++;
//				}
//				System.out.println("复制资源图片到"+avatarImagePath);
//				fos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("复制资源图片到"+avatarImagePath+" 失败!");
//			}
//		}else{
//			System.out.println("图片已存在:"+avatarImagePath);
//		}TODO waiting for delete above codes

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

    public static final String LOGIN_INFO_SHAREDPREFERENCE_NAME = "LoginInfoSharedPreferencd";

	public void setActivity(Activity activity){
		this.activity = activity;
	}


	private static BasementDAO basementDAO = new BasementDAO();
	private String appPath = "";



	private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


	private String h1="  ·";
	private String h2= "    ·";
}
