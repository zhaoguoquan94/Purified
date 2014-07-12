package com.dagger_studio.purified;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dagger_studio.purified.greenDAOGenerated.MyCollectionList;

import java.io.File;
import java.util.List;

public class MyCollectionList_Card_wide_Adapter extends BaseAdapter {

	/**
	 *
	 * @param context
	 * @param array 用户ID的列，来提取对应卡片
	 *
	 * @return 根据传入的参数array，提取array里面的userid，构造Adapter并返回
	 *
	 * @author alex
	 */
	public MyCollectionList_Card_wide_Adapter(Context context, List<MyCollectionList> array)
//	public MyCollectionList_Card_wide_Adapter(Context context, List<String> array)
	{
		super();
        backgroundImageResourcePack[0] = R.drawable.colorback_cherry;
        backgroundImageResourcePack[1] = R.drawable.colorback_forest;
        backgroundImageResourcePack[2] = R.drawable.colorback_grape;
        backgroundImageResourcePack[3] = R.drawable.colorback_ocean;
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
			view = LayoutInflater.from(mContext).inflate(R.layout.outer_card_layout_wide, null);
			viewHolder.backImageView = (ImageView)view.findViewById(R.id.backimage_inner_card);
			viewHolder.text = (TextView)view.findViewById(R.id.text_inner_card);
            viewHolder.frameLayout = (FrameLayout)view.findViewById(R.id.framelayout_inner_card);
            viewHolder.newImageView = (ImageView)view.findViewById(R.id.newImage_inner_card);
			view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder)view.getTag();
		}

        File file = new File(array.get(position).getImgPath());
        if (file.exists()){
            viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeFile(array.get(position).getImgPath()));
            viewHolder.frameLayout.setMinimumHeight((int)viewHolder.text.getWidth()/4);
            viewHolder.text.setHeight((int)viewHolder.text.getWidth()/4);

        }else{
            viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(view.getResources(),backgroundImageResourcePack[(int)Math.random()*4]));
            viewHolder.frameLayout.setMinimumHeight((int)viewHolder.text.getWidth()*5/8);
            viewHolder.text.setHeight((int)viewHolder.text.getWidth()*5/8);    //背景图的长宽比为8：5
            viewHolder.frameLayout.setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }

        viewHolder.text.setText(array.get(position).getTitle());
        if(!array.get(position).getIsRead()){
            viewHolder.newImageView.setVisibility(View.VISIBLE);
        }else{
            viewHolder.newImageView.setVisibility(View.INVISIBLE);
        }

//        switch (position) {
//		case 0:
//			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.netease));
//			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo3));
//			viewHolder.text.setText("我精选的咱们学业领域的新闻\n专门的设计者趣味新闻，希望喜欢~");
//			break;
//		case 1:
//			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.zhihu));
//			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo2));
//			viewHolder.text.setText("你知道知乎有那么多逗比回答吗？\n我找了很久，精选了一些（持续更新）");
//			break;
//		case 2:
//			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.firstback));
//			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo1));
//			viewHolder.text.setText("Something about photography, wish you like!");
//			break;
//		default:
//			viewHolder.backImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.fengjing));
//			viewHolder.logoImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo2));
//			viewHolder.text.setText("Take a journey around the world! Favorite PLACES!");
//			break;
//		}
		return view;
	}

	class ViewHolder{
		ImageView backImageView;
		ImageView newImageView;
		TextView text;
        FrameLayout frameLayout;
	}

	private Context mContext = null;
	private List<MyCollectionList> array = null;
//	private List<String> array = null;
    private int[] backgroundImageResourcePack = new int[4];
	public ViewHolder viewHolder;

}
