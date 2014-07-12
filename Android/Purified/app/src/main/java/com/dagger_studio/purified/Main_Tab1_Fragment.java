package com.dagger_studio.purified;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.dagger_studio.purified.greenDAOGenerated.DaoMaster;
import com.dagger_studio.purified.greenDAOGenerated.DaoSession;
import com.dagger_studio.purified.greenDAOGenerated.MyCollectionList;
import com.dagger_studio.purified.greenDAOGenerated.MyCollectionListDao;

import java.util.ArrayList;
import java.util.List;

public class Main_Tab1_Fragment extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState)
	{
		activity = this.getActivity();
		view = inflater.inflate(R.layout.activity_main_tab1, container, false);
		listView = (ListView)view.findViewById(R.id.listView_mine);
//		if (idOfCard_list==null)
//		{
//
//            idOfCard_list = new ArrayList<String>();
//            for (int i=0;i<4;i++)
//            {
//                idOfCard_list.add(urls[i]);
//            }
//		}

        if (myCollectionList == null){
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getActivity(), "MyCollectionList", null);
            SQLiteDatabase db = devOpenHelper.getReadableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            DaoSession daoSession = daoMaster.newSession();
            MyCollectionListDao myCollectionListDao = daoSession.getMyCollectionListDao();
            myCollectionList = myCollectionListDao.loadAll();
        }
        if (myCollectionList.size()==0){
            return inflater.inflate(R.layout.empty_tab, container, false);

        }else{
            adapter = new MyCollectionList_Card_wide_Adapter(getActivity().getApplicationContext(), myCollectionList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                Intent i = new Intent(activity, Web_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", myCollectionList.get(position).getUrl());
                i.putExtras(bundle);
                startActivity(i);
                }
            });
        }

		return view;
	}

	private void makeShortToast(String str)
	{
		Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
	}
	private View view;
	private Activity activity;
	private ListView listView = null;
	private MyCollectionList_Card_wide_Adapter adapter;
	private List<MyCollectionList> myCollectionList=null;

}
