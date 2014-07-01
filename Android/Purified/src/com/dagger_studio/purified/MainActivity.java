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
import android.view.View.OnClickListener;
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
        
        actionBar.setHomeButtonEnabled(true);
        
        layout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			/**
			 * 
			 * 监测手指的按压动作，通过记录手指的移动方向及距离判定相应切换菜单等动作
			 * 
			 */
				createVelocityTracker(event);
		        switch (event.getAction()) {
		        case MotionEvent.ACTION_DOWN:  
		            // 手指按下时，记录按下时的横坐标  
		            xDown = event.getRawX();
		            System.out.println("push!");
		            break;
		        case MotionEvent.ACTION_MOVE:  
		            // 手指移动时，对比按下时的横坐标，计算出移动的距离，来调整menu的leftMargin值，从而显示和隐藏menu  
		            xMove = event.getRawX();  
		            int distanceX = (int) (xMove - xDown);
		            if (isMenuVisible) {//抽屉菜单已弹出
		                menuParams.leftMargin = distanceX;//将抽屉菜单向左移动
		            } else {  //抽屉菜单并未弹出
		                menuParams.leftMargin = leftEdge + distanceX;//移动菜单，由于当前菜单左侧隐藏在外边，所以需要减去width，leftEdg=-width
		            } 
		            if (menuParams.leftMargin < leftEdge) {  
		                menuParams.leftMargin = leftEdge;	//防止view移动到屏幕外侧，强制将view移动回来
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
        //===================  侧滑菜单部分结束================================================
        
        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_menu, drawerFragment).commit();
        
        
        //=================================== 结束设置侧面menu =================
        
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
    			scrollToMenu();
    			isContentShow = false;
    		}else{
    			scrollToContent();
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
    
    public static final int SNAP_VELOCITY = 200;
    
    private Main_Tab1_Fragment tab1_Fragment = new Main_Tab1_Fragment();
    private MainDrawerFragment drawerFragment = new MainDrawerFragment();
    private Main_Tab2_Fragment tab2_Fragment = new Main_Tab2_Fragment();
    private Main_Tab3_Fragment tab3_Fragment = new Main_Tab3_Fragment();
    private FragmentManager fragmentManager = null;
    private int screenWidth;
    private int leftEdge;
    private int rightEdge=0;
    private int menuPadding=0;//全部划出时，content露出来的部分
    private LinearLayout.LayoutParams menuParams;
    private float xDown, xMove,xUp;
    private boolean isMenuVisible;
    private VelocityTracker mVelocityTracker;
    private View content, menu, layout;
    private ActionBar actionBar;
    private boolean isContentShow = true;
    
//    ------------------------------   methods  ------------------------
    private void makeShortToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
    @SuppressLint("NewApi")
    public void initValues() {  
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
        
        actionBar = getActionBar();
        
    }
    public boolean wantToShowContent(){
    	/**
    	 * 判定是否是想要向主页面滚动，当向左滑（xUp<xDown)且当前是显示drawer的时候
    	 */
		return xUp - xDown<0 && isMenuVisible;
	}
    public boolean wantToShowMenu(){
		/**
    	 * 判定是否是想要向drawer滚动，当向左滑（xUp<xDown)且当前是显示主界面的时候
    	 */
		return xUp-xDown>0&&!isMenuVisible;
	}
	
	public boolean shouldScrollToMenu() {
		return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
	}
	
	public boolean shouldScrollToContent() {  
        return (xDown - xUp + menuPadding) > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;  
    }
	
	
	public void createVelocityTracker(MotionEvent event) {  
        if (mVelocityTracker == null) {  
            mVelocityTracker = VelocityTracker.obtain();  
        }  
        mVelocityTracker.addMovement(event);  
    }
	
	public int getScrollVelocity() {  
        mVelocityTracker.computeCurrentVelocity(1000);  
        int velocity = (int) mVelocityTracker.getXVelocity();  
        return Math.abs(velocity);  
    }
	
	public void recycleVelocityTracker() {  
        mVelocityTracker.recycle();  
        mVelocityTracker = null;  
    }
	public void scrollToMenu() {  
        new ScrollTask().execute(30);  
    }
	public void scrollToContent() {  
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



