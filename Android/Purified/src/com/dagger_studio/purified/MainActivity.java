package com.dagger_studio.purified;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MainActivity extends FragmentActivity {

    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();
        layout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				createVelocityTracker(event);  
		        switch (event.getAction()) {
		        case MotionEvent.ACTION_DOWN:  
		            // 手指按下时，记录按下时的横坐标  
		            xDown = event.getRawX();  
		            break;  
		        case MotionEvent.ACTION_MOVE:  
		            // 手指移动时，对比按下时的横坐标，计算出移动的距离，来调整menu的leftMargin值，从而显示和隐藏menu  
		            xMove = event.getRawX();  
		            int distanceX = (int) (xMove - xDown);  
		            if (isMenuVisible) {  
		                menuParams.leftMargin = distanceX;  
		            } else {  
		                menuParams.leftMargin = leftEdge + distanceX;  
		            }  
		            if (menuParams.leftMargin < leftEdge) {  
		                menuParams.leftMargin = leftEdge;  
		            } else if (menuParams.leftMargin > rightEdge) {  
		                menuParams.leftMargin = rightEdge;  
		            }  
		            menu.setLayoutParams(menuParams);  
		            break;  
		        case MotionEvent.ACTION_UP:  
		            // 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面  
		            xUp = event.getRawX();  
		            if (wantToShowMenu()) {  
		                if (shouldScrollToMenu()) {  
		                    scrollToMenu();  
		                } else {  
		                    scrollToContent();  
		                }  
		            } else if (wantToShowContent()) {  
		                if (shouldScrollToContent()) {  
		                    scrollToContent();  
		                } else {  
		                    scrollToMenu();  
		                }  
		            }  
		            recycleVelocityTracker();  
		            break;  
		        }  
		        return true;  
			}
			
			
		});
        
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, tab1_Fragment).commit();
        
        ActionBar actionBar = getActionBar();
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
		case R.id.action_new:
			makeShortToast("add!");
			break;
		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
    public static final int SNAP_VELOCITY = 200;
    
    private Main_Tab1_Fragment tab1_Fragment = new Main_Tab1_Fragment();
    private Main_Tab2_Fragment tab2_Fragment = new Main_Tab2_Fragment();
    private Main_Tab3_Fragment tab3_Fragment = new Main_Tab3_Fragment();
    private FragmentManager fragmentManager = null;
    private int screenWidth;
    private int leftEdge;
    private int rightEdge=0;
    private int menuPadding=80;//全部划出时，content露出来的部分
    private LinearLayout.LayoutParams menuParams;
    private float xDown, xMove,xUp;
    private boolean isMenuVisible;
    private VelocityTracker mVelocityTracker;
    private View content, menu, layout;
    
//    ------------------------------   methods  ------------------------
    private void makeShortToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
    private void initValues() {  
        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);  
        screenWidth = window.getDefaultDisplay().getWidth(); 
        layout = findViewById(R.id.main_layout);
        content = findViewById(R.id.main_content);  
        menu = findViewById(R.id.main_menu);  
        menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();  
        // 将menu的宽度设置为屏幕宽度减去menuPadding  
        menuParams.width = screenWidth - menuPadding;  
        // 左边缘的值赋值为menu宽度的负数  
        leftEdge = -menuParams.width;  
        // menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见  
        menuParams.leftMargin = leftEdge;  
        // 将content的宽度设置为屏幕宽度  
        content.getLayoutParams().width = screenWidth;  
    }
    private boolean wantToShowContent(){
		return xUp - xDown<0 && isMenuVisible;
	}
	private boolean wantToShowMenu(){
		return xUp-xDown>0&&!isMenuVisible;
	}
	
	private boolean shouldScrollToMenu() {
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}
	
	private boolean shouldScrollToContent() {  
        return (xDown - xUp + menuPadding) > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;  
    }
	
	
	private void createVelocityTracker(MotionEvent event) {  
        if (mVelocityTracker == null) {  
            mVelocityTracker = VelocityTracker.obtain();  
        }  
        mVelocityTracker.addMovement(event);  
    }
	
	private int getScrollVelocity() {  
        mVelocityTracker.computeCurrentVelocity(1000);  
        int velocity = (int) mVelocityTracker.getXVelocity();  
        return Math.abs(velocity);  
    }
	
	private void recycleVelocityTracker() {  
        mVelocityTracker.recycle();  
        mVelocityTracker = null;  
    }
	private void scrollToMenu() {  
        new ScrollTask().execute(30);  
    }
	private void scrollToContent() {  
        new ScrollTask().execute(-30);  
    } 


//    ------------------------ class------------------------------------
    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {  
  	  
        @Override  
        protected Integer doInBackground(Integer... speed) {  
            int leftMargin = menuParams.leftMargin;  
            // 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。  
            while (true) {  
                leftMargin = leftMargin + speed[0];  
                if (leftMargin > rightEdge) {  
                    leftMargin = rightEdge;  
                    break;  
                }  
                if (leftMargin < leftEdge) {  
                    leftMargin = leftEdge;  
                    break;  
                }  
                publishProgress(leftMargin);  
                // 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。  
                sleep(20);  
            }  
            if (speed[0] > 0) {  
                isMenuVisible = true;  
            } else {  
                isMenuVisible = false;  
            }  
            return leftMargin;  
        }  

        @Override  
        protected void onProgressUpdate(Integer... leftMargin) {  
            menuParams.leftMargin = leftMargin[0];  
            menu.setLayoutParams(menuParams);  
        }  

        @Override  
        protected void onPostExecute(Integer leftMargin) {  
            menuParams.leftMargin = leftMargin;  
            menu.setLayoutParams(menuParams);  
        }  
    }  

    /** 
     * 使当前线程睡眠指定的毫秒数。 
     *  
     * @param millis 
     *            指定当前线程睡眠多久，以毫秒为单位 
     */  
    private void sleep(long millis) {  
        try {  
            Thread.sleep(millis);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}



