package com.dagger_studio.purified;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainDrawerFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		activity = this.getActivity();
		view = inflater.inflate(R.layout.activity_main_drawer, container, false);
		
		return view;
		
		
	
	}
	
	private Activity activity;
	private View view;
}