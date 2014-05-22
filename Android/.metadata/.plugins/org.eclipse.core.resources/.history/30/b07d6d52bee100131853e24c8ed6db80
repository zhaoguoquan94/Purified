package com.dagger_studio.purified;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Main_Tab3_Fragment extends Fragment{
	public Main_Tab3_Fragment()
	{
		System.out.println("constructor!");
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
	{
		System.out.println("createView!");
		view = inflater.inflate(R.layout.activity_main__tab3, container, false);
		if (listView==null)
		{
			listView = (ListView)view.findViewById(R.id.listView_recommand);
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
		}		
		
		return view;
	}
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		System.out.println("Create!");
	}
	
	
	private void makeShortToast(String str)
	{
		Toast.makeText(null, str, Toast.LENGTH_SHORT).show();
	}
	
	private View view;
	private ListView listView = null;
	private Card_wide_Adapter adapter;
	private List<String> idOfCard_list = new ArrayList<String>();
	

}


