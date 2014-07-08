package com.dagger_studio.purified;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main_Tab2_Fragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
	{
		activity = this.getActivity();
		view = inflater.inflate(R.layout.activity_main_tab2, container, false);
		listView = (ListView)view.findViewById(R.id.listView_friend);
		if (idOfCard_list==null)
		{
			 idOfCard_list = new ArrayList<String>();
			 for (int i=0;i<5;i++)
				{
					idOfCard_list.add("user"+i);
				}
		}
		//TODO 这里的idOfCard是需要进一步修改的

		adapter = new Card_wide_Adapter(getActivity().getApplicationContext(), idOfCard_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(activity, Web_Activity.class);
				Bundle bundle = new Bundle();
				bundle.putString("url", urls[arg2]);
				i.putExtras(bundle);
				startActivity(i);
			}
			});
		return view;
	}

	private View view;
	private Activity activity;
	private ListView listView = null;
	private Card_wide_Adapter adapter;
	private List<String> idOfCard_list = null;
	private String[] urls={"http://www.news.163.com","http://zhihu.com","http://today.hit.edu.cn","http://news.163.com/14/0522/13/9SRPNPSG0001124J.html","http://news.163.com/14/0522/18/9SSBI50R00014JB6.html"};//TODO
}
