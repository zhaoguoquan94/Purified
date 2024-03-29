package com.dagger_studio.purified;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web_Activity extends Activity {

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		webView = new WebView(this);
		webView.getSettings().setJavaScriptEnabled(true);
		Bundle bundle = getIntent().getExtras();
		webView.loadUrl(bundle.getString("url"));
		webView.setWebViewClient(new WebViewClient());
		setContentView(webView);



	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)//TODO
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
		getMenuInflater().inflate(R.menu.web, menu);//TODO
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)//TODO
	{
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private WebView webView = null;

}
