package com.dagger_studio.purified;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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

import com.dagger_studio.purified.Beans.UserInfo;
import com.dagger_studio.purified.DAO.BasementDAO;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends FragmentActivity {

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
        urls = basementDao.getUrls();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


//        ===================  开始配置侧滑drawer的内容  ====================================

        mDrawerLayout = (DrawerLayout)findViewById(R.id.main_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                isContentShow = true;
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isContentShow = false;
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        ImageView avatarImageView = (ImageView)findViewById(R.id.avatar_drawer);
//        File file = new File(dao.getAvatarImagePath(this));
//        Bitmap bm = BitmapFactory.decodeFile(file.getPath(),null);
//
//        avatarImageView.setImageBitmap(bm);

        UserInfo userInfo = UserInfo.getInstance(this);
        avatarImageView.setImageBitmap(userInfo.getAvatarBitmap());
        ListView lv = (ListView)findViewById(R.id.listview_drawer);



        if (collectionList == null){
        	collectionList = new ArrayList<String>();

//        	===========  TODO EREA  =============
        	collectionList = basementDao.getCollectionList();

        	//==========      为drawer的listView适配一个adapter
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

//			==============    为listView添加监听 开始  TODO 这里的需要更改，因为目前listView里面的只是测试用的
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



//        	=============  为 Button 添加监听 开始 ==============


			Button settingButton = (Button)findViewById(R.id.settingButton_drawer);
			settingButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					makeShortToast("Setting");//TODO 为设置按钮添加真实的响应事件
				}
			});

			Button refreshTipButton = (Button)findViewById(R.id.refreshButton_drawer);
			refreshTipButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					makeShortToast("Refresh!");//TODO 为刷新按钮添加真实的响应事件
				}
			});

			Button searchButton = (Button)findViewById(R.id.searchButton_drawer);
			searchButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					makeShortToast("Search!");//TODO 为搜索按钮添加真实的响应事件
				}
			});





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
    	switch (item.getItemId()) {
    	case android.R.id.home:{
    		if (isContentShow){
    			mDrawerLayout.openDrawer(GravityCompat.START);
    			isContentShow = false;
    		}else{
    			mDrawerLayout.closeDrawer(GravityCompat.START);
    			isContentShow = true;
    		}
    		break;
    	}
		case R.id.action_new:{
			if (!sharedPreferences.getBoolean("initdataload", false)){

                makeShortToast("正在初始化基本测试数据");
            }else{
                makeShortToast("您已经初始化基本数据");
            }
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
    private BasementDAO basementDao = BasementDAO.getInstance();
    private DrawerLayout mDrawerLayout;
    private List<String> collectionList = null;
	private String[] urls;
    private ActionBarDrawerToggle mDrawerToggle;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

//    ------------------------------   methods  ------------------------
    private void makeShortToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}



    @SuppressLint("NewApi")

    public void initValues() {
        sharedPreferences = getSharedPreferences("data_info", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        actionBar = getActionBar();
        basementDao.setActivity(this);
        basementDao.initValues();
    }



//    ------------------------ class------------------------------------
}



