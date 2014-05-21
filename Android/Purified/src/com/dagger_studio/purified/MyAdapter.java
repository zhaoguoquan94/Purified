package com.dagger_studio.purified;

import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	public MyAdapter(Context context, List<String> array)
	{
		super();
		this.array = array;
		this.mContext = context;
	}
	@Override
	public int getCount() {
		return array.size();
	}

	@Override
	public Object getItem(int position) {
		return array.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view==null)
		{
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.card_layout_wide, null);
			viewHolder.backImageView = (ImageView)view.findViewById(R.id.backimage_card);
			viewHolder.logoImageView = (ImageView)view.findViewById(R.id.logoimage_card);
			viewHolder.text = (TextView)view.findViewById(R.id.text_card_wide);
			view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder)view.getTag();
		}
		
		viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.netease));
		viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo3));
		viewHolder.text.setText("�ҹ�ע�����׿ͻ��ˣ��Ƽ���~");
		return view;
	}
	
	class ViewHolder{
		ImageView backImageView;
		ImageView logoImageView;
		TextView text;
	}
	
	private Context mContext = null;
	List<String> array = null;
	public ViewHolder viewHolder;
}