package com.example.drawer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));
		
		actionBar = this.getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		Tab tab1 = actionBar.newTab();
		tab1.setText("Tab1");
		tab1.setTabListener(new TabListener() {
			@Override
			public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			}
		});        
		actionBar.addTab(tab1);
		
		Tab tab2 = actionBar.newTab();
		tab1.setText("Tab2");
		tab2.setTabListener(new TabListener() {
			@Override
			public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			}
			@Override
			public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			}
		});        
		actionBar.addTab(tab2);
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
    {
    	System.out.println("ID = "+item.getItemId());
    	switch (item.getItemId()) {
    	case android.R.id.home:{
    		if (isDwawerOpen){
    			mDrawerLayout.closeDrawer(mDrawerList);
    			isDwawerOpen = false;
    			Toast.makeText(MainActivity.this, "close!", Toast.LENGTH_SHORT).show();
    		}
    		else {
    			mDrawerLayout.openDrawer(mDrawerList);
    			isDwawerOpen = true;
    			Toast.makeText(MainActivity.this, "open!", Toast.LENGTH_SHORT).show();
			}
    		break;
    	}
		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
	
	private String[] mPlanetTitles={"1","2","3","4","5","6","7","8"};
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBar actionBar;
    private boolean isDwawerOpen = false;
}
