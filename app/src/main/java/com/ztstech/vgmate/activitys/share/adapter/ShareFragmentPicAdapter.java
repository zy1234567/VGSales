package com.ztstech.vgmate.activitys.share.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;

import java.lang.ref.WeakReference;

/**
 * 九宫格图片adapter
 * @author zhiyuan
 *
 */
public class ShareFragmentPicAdapter extends BaseAdapter {

	private Context ctx;
	private String[] url;
	private String[] urlBig;//大图url
	private String[] descriptions;
	private String newsId;
	private int commentcnt;// 评论数字
	private boolean isShowBottom;// 图片描述是否有下面一条

	private int modelPosition;// 当前选中的是哪一条

	ShareListBean.ListBean dataBean;
	int beanPosition;

	public ShareFragmentPicAdapter(Context ctx, String[] url, String[] urlBig) {
		this.ctx = ctx;
		this.url = url;
		this.urlBig = urlBig;
	}
	public ShareFragmentPicAdapter(Context ctx, ShareListBean.ListBean dataBean, int beanPosition){
		this.ctx = ctx;
		this.dataBean = dataBean;
		this.beanPosition =beanPosition;
		url = dataBean.contentpicsurl.split(",");
		urlBig = dataBean.contentpicurl.split(",");
	}

	/**
	 * 
	 * @param ctx
	 *            上下文
	 * @param url
	 * @param descriptions
	 * @param newsId
	 *            文章id
	 * @param counmentCount
	 *            文章评论数量
	 *
	 *            当前文章所在model
	 * @param isShowBottom
	 *            是否需要展示底部点赞一行
	 *            当前文章所在的viewHolder
	 */
	public ShareFragmentPicAdapter(Context ctx, String[] url, String[] urlBig, String[] descriptions, String newsId, int counmentCount,
								   boolean isShowBottom, int position) {
		this.ctx = ctx;
		this.url = url;
		this.urlBig = urlBig;
		this.descriptions = descriptions;
		this.newsId = newsId;
		this.commentcnt = counmentCount;
		this.isShowBottom = isShowBottom;
		this.modelPosition = position;
	}

	public void updateData(String[] url, String[] urlBig, String[] descriptions) {
		this.url = url;//缩略图
		this.urlBig = urlBig;//大图
		this.descriptions = descriptions;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (url.length <= 8) {
			return url.length;
		} else {
			return 9;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return url[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		final MyHolder holder;
		if (convertView == null) {
			holder = new MyHolder();
			convertView = LayoutInflater.from(ctx).inflate(R.layout.zts_alter_shareitem_picitem, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.share_fragment_adapter_ceshi_iv);
			holder.tv = (TextView) convertView.findViewById(R.id.share_fragment_adapter_ceshi_img_number);
			convertView.setTag(holder);
		} else {
			holder = (MyHolder) convertView.getTag();
			holder.iv.setImageBitmap(null);
		}
		holder.currentPosition = position;
		if (position <= 8) {
//			holder.listener.loadingPosition = position;		//当前位置
//			holder.listener.currentHolder = holder;	//记录当前holder

			if (position == 8) {
				if (url.length > 9) {
					holder.tv.setVisibility(View.VISIBLE);
					holder.tv.setText("共" + url.length + "张图");
				}
			}
			holder.onclick.setMyHolder(holder);
			holder.iv.setOnClickListener(holder.onclick);
			holder.onclick.position = position;
			Glide.with(ctx)
					.load(url[position])
					.error(R.mipmap.pre_default_image)
					.into(holder.iv);
		}

		return convertView;
	}
	
	/**
	 * 跳转到大图浏览界面
	 * @author ZMJ
	 *
	 */
	private void toShowBigPhoto(int position){
//		Intent intent = new Intent(ctx, ActivityPhotoBrowser.class);
//		intent.putExtra("imgPosition",position);
////        intent.putParcelableArrayListExtra("bitmaps", (ArrayList<? extends Parcelable>) mapList);
//		intent.putExtra("position", beanPosition);
//		intent.putExtra("bean",dataBean);
//		ctx.startActivity(intent);
	}
	
	

	class ListenerOnClick implements OnClickListener {
		
		//位置
		int position;
				
		//holder的弱引用
		WeakReference<MyHolder> wrf;
		
		//缩略图的bitmap
		Bitmap bitmap;
				
		public void setMyHolder(MyHolder holder){
			wrf = new WeakReference<MyHolder>(holder);
		}
				
		@Override
		public void onClick(View v) {
			if(wrf.get() == null){
				return;
			}
			MyHolder holder = wrf.get();
			if(v == holder.iv){
				toShowBigPhoto(position);
			}
		}
		
	}

	class MyHolder {
		
		//控件
		ImageView iv;
		TextView tv;
		
		ListenerOnClick onclick = new ListenerOnClick();


		///viewholder当前位置
		int currentPosition;
		
	}
}
