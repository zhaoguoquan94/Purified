package com.dagger_studio.purified;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainDrawerFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		activity = this.getActivity();
		view = inflater.inflate(R.layout.activity_main_drawer, container, false);
		
//		==============    为listView添加内容开始
		ImageView avatarImageView = (ImageView)view.findViewById(R.id.avatar_drawer);
//        Log.d("avatarFilePath", activity.getApplicationContext().getFilesDir().getAbsolutePath()+"/images/avatar.jpg");
        File file = new File(activity.getApplicationContext().getFilesDir().getAbsolutePath()+"/images/avatar.jpg");
        Bitmap bm = BitmapFactory.decodeFile(file.getPath(),null);
        
        avatarImageView.setImageBitmap(bm);
        
        ListView lv = (ListView)view.findViewById(R.id.listview_drawer);
		
        
        
        if (collectionList == null){
        	collectionList = new ArrayList<String>();
        	
//        	===========  TODO EREA  =============
        	collectionList.add("我的收藏");
        	collectionList.add(h1+"哈工大新闻");
        	collectionList.add(h2+"本科生院新闻模块");
        	collectionList.add(h2+"国际合作处信息板");
        	collectionList.add(h1+"IT相关");
        	collectionList.add(h2+"Google I/O大会");
        	collectionList.add(h2+"云计算");
        	collectionList.add(h1+"网易新闻");
        	collectionList.add(h2+"世界杯赛程板块");
        	Adapter adapter = new BaseAdapter() {
				
				@Override
				/**
				 * @author alex
				 * 返回每一个view来适配listView
				 */
				public View getView(int arg0, View arg1, ViewGroup arg2) {
					TextView textView = new TextView(activity.getApplicationContext());
					textView.setText(collectionList.get(arg0));
					textView.setTextColor(Color.WHITE);
					textView.setHeight(90);
					textView.setTextSize(20F);
					return textView;
				}
				
				@Override
				public long getItemId(int arg0) {
					return arg0;
				}
				
				@Override
				public Object getItem(int arg0) {
					return collectionList.get(arg0);
				}
				
				@Override
				public int getCount() {
					return collectionList.size();
				}
			};
			
			lv.setAdapter((ListAdapter)adapter);
			
//			==============    为listView添加内容结束
			
//			==============    为listView添加监听 开始
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if (arg2>0 && arg2<4){
						Intent i = new Intent(activity, Web_Activity.class);
						Bundle bundle = new Bundle();
						bundle.putString("url", urls[arg2]);
						i.putExtras(bundle);
						startActivity(i);
					}
				}
			});
			
			


							
//			==============    为listView添加监听 结束
        	
//        	=============  为 Button 添加监听 开始 ==============
			
			
			Button settingButton = (Button)view.findViewById(R.id.settingButton_drawer);
			settingButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					makeShortToast("Setting");
				}
			});
			
			Button refreshTipButton = (Button)view.findViewById(R.id.refreshButton_drawer);
			refreshTipButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					makeShortToast("Refresh!");
				}
			});
			
			Button searchButton = (Button)view.findViewById(R.id.searchButton_drawer);
			searchButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					makeShortToast("Search!");
				}
			});
			
//			===========================   设置按钮结束    ================
			
			
			
//        	=============  为 Button 添加监听 结束 ==============
			
//        	============ END OF TODO EREA  =============
        }
        
        
        return view;
	}
	
	
//	=======================   methods    ================================
	
	public void makeShortToast(String str){
		Toast.makeText(this.activity, str, Toast.LENGTH_SHORT).show();
	}
	
	private Activity activity;
	private View view;
	private String h1="  ·";
	private String h2= "    ·";
	List<String> collectionList = null;
	String[] urls= {"", "http://today.hit.edu.cn", "http://today.hit.edu.cn/depart/26.htm", "http://www.international.hit.edu.cn/"};
}
