package com.dagger_studio.purified;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Main_Tab3_Fragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
	{
		
		listView = (ListView)container.findViewById(R.id.listView_recommand);
		
		
		return inflater.inflate(R.layout.activity_main__tab3, container, false);
	}
	
	private ListView listView = null;
	

}

