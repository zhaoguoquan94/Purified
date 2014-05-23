package com.dagger_studio.purified;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web_Activity extends Activity {

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_web);
//		Bundle bundle = getIntent().getExtras();
//		ActionBar actionBar = getActionBar();
//      actionBar.setDisplayHomeAsUpEnabled(true);
//        
//        webView = (WebView)findViewById(R.id.webView);
//        webView.loadUrl(bundle.getString("url"));
//        webView.getSettings().setJavaScriptEnabled(true);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		Bundle bundle = getIntent().getExtras();
		webView.loadUrl(bundle.getString("url"));
		webView.setWebViewClient(new WebViewClient());
		setContentView(webView);
		
		
        
	}

	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK )
		{
			if (webView.canGoBack())
			{
				webView.goBack();
			}
			else {
				webView.destroy();
				this.finish();
			}
		}
		
		return false;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}
	
	private WebView webView = null;

}