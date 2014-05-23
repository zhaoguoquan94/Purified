package com.dagger_studio.purified;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Main_Tab2_Fragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
	{
		view = inflater.inflate(R.layout.activity_main_tab2, container, false);
		listView = (ListView)view.findViewById(R.id.listView_friend);
		for (int i=1;i<5;i++)
		{
			idOfCard_list.add("user"+i);
		}
		adapter = new Card_wide_Adapter(getActivity().getApplicationContext(), idOfCard_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
			}
			});
		return view;
	}

	private View view;
	private ListView listView = null;
	private Card_wide_Adapter adapter;
	private List<String> idOfCard_list = new ArrayList<String>();
}
