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

public class Card_wide_Adapter extends BaseAdapter {

	public Card_wide_Adapter(Context context, List<String> array)
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
		switch (position) {
		case 0:
			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.netease));
			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo3));
			viewHolder.text.setText("我精选的咱们学业领域的新闻\n专门的设计者趣味新闻，希望喜欢~");
			break;
		case 1:
			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.zhihu));
			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo2));
			viewHolder.text.setText("你知道知乎有那么多逗比回答吗？\n我找了很久，精选了一些（持续更新）");
			break;
		case 2:
			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.firstback));
			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo1));
			viewHolder.text.setText("Something about photography, wish you like!");
			break;
		default:
			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.fengjing));
			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo2));
			viewHolder.text.setText("Take a journey around the world! Favorite PLACES!");
			break;
		}
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
