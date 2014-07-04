package com.dagger_studio.purified;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
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



public class MainActivity extends FragmentActivity {

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
        
        actionBar.setHomeButtonEnabled(true);
        
        
        
        
//        ===================  开始配置侧滑drawer的内容  ====================================
        
        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_layout);
        ImageView avatarImageView = (ImageView)findViewById(R.id.avatar_drawer);
        File file = new File(getApplicationContext().getFilesDir().getAbsolutePath()+"/images/avatar.jpg");
        Bitmap bm = BitmapFactory.decodeFile(file.getPath(),null);
        
        avatarImageView.setImageBitmap(bm);
        
        ListView lv = (ListView)findViewById(R.id.listview_drawer);
		
        
        
        if (collectionList == null){
        	collectionList = new ArrayList<String>();
        	
//        	===========  TODO EREA  =============
        	collectionList = dao.getCollectionList();
        	Adapter adapter = new BaseAdapter() {
				
				@Override
				/**
				 * @author alex
				 * 返回每一个view来适配listView
				 */
				public View getView(int arg0, View arg1, ViewGroup arg2) {
					TextView textView = new TextView(getApplicationContext());
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
						Intent i = new Intent(MainActivity.this, Web_Activity.class);
						Bundle bundle = new Bundle();
						bundle.putString("url", urls[arg2]);
						i.putExtras(bundle);
						startActivity(i);
					}
				}
			});

							
//			==============    为listView添加监听 结束
        	
//        	=============  为 Button 添加监听 开始 ==============
			
			
			Button settingButton = (Button)findViewById(R.id.settingButton_drawer);
			settingButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					makeShortToast("Setting");
				}
			});
			
			Button refreshTipButton = (Button)findViewById(R.id.refreshButton_drawer);
			refreshTipButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					makeShortToast("Refresh!");
				}
			});
			
			Button searchButton = (Button)findViewById(R.id.searchButton_drawer);
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
        
        
        //=================================== 配置中部主题内容部分 content  ===============
        
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, tab1_Fragment).commit();
        
        
        
        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab tab1 = actionBar.newTab();
        tab1.setText("我的便贴");
        tab1.setTabListener(new TabListener() {
			@Override
			public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
				fragmentManager.beginTransaction().replace(R.id.main_content, tab1_Fragment).commit();
			}
			@Override
			public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			}
		});        
        actionBar.addTab(tab1);
      //添加第一个fragment的tab，并设置监听方法

        
        Tab tab2 = actionBar.newTab();
        tab2.setText("好友便贴");
        tab2.setTabListener(new TabListener() {
			@Override
			public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
				fragmentManager.beginTransaction().replace(R.id.main_content, tab2_Fragment).commit();
			}
			@Override
			public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			}
		});
        actionBar.addTab(tab2);
      //添加第二个fragment的tab，并设置监听方法
        
        Tab tab3 = actionBar.newTab();
        tab3.setText("向您推荐");
        tab3.setTabListener(new TabListener() {
			@Override
			public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
				fragmentManager.beginTransaction().replace(R.id.main_content, tab3_Fragment).commit();
			}
			@Override
			public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			}
		});
        actionBar.addTab(tab3);
      //添加第三个fragment的tab，并设置监听方法

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	System.out.println("ID = "+item.getItemId());
    	switch (item.getItemId()) {
    	case android.R.id.home:{
    		if (isContentShow){
    			Log.d("Gravity_Left", Gravity.LEFT+"");
    			mDrawerLayout.openDrawer(Gravity.LEFT);
    			isContentShow = false;
    		}else{
    			Log.d("Gravity_Left", Gravity.LEFT+"");
    			mDrawerLayout.closeDrawer(Gravity.LEFT);
    			isContentShow = true;
    		}
    		break;
    	}
		case R.id.action_new:{
			makeShortToast("add!");
			break;
		}
		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    
    private Main_Tab1_Fragment tab1_Fragment = new Main_Tab1_Fragment();
    private Main_Tab2_Fragment tab2_Fragment = new Main_Tab2_Fragment();
    private Main_Tab3_Fragment tab3_Fragment = new Main_Tab3_Fragment();
    private FragmentManager fragmentManager = null;
    private ActionBar actionBar;
    private boolean isContentShow = true;
    private DAO dao = DAO.getInstance();
    private DrawerLayout mDrawerLayout;
    private List<String> collectionList = null;
	String[] urls = {"", "http://today.hit.edu.cn", "http://today.hit.edu.cn/depart/26.htm", "http://www.international.hit.edu.cn/"};
    
//    ------------------------------   methods  ------------------------
    private void makeShortToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
    
    
    @SuppressLint("NewApi")
    
    public void initValues() {  
        actionBar = getActionBar();
        dao.setAppPath(getApplicationContext().getFilesDir().getAbsolutePath());
        dao.setActivity(this);
        dao.initValues();
        //TODO 在这里添加了一些默认的数据，仅作为调试时使用
    }
    
    
    
//    ------------------------ class------------------------------------
}



