package com.ztstech.vgmate.activitys.share.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.ViewUtils;

import java.util.List;

public class PriseHeadAdapter extends BaseAdapter {
	
	private Context ctx;
	private List<ShareListBean.ListBean.LikeListBean> likeListBeanList;

    /** 点赞图片边长 */
    private static int length;

	public PriseHeadAdapter(Context ctx, List<ShareListBean.ListBean.LikeListBean> likeListBeanList){
		this.ctx = ctx;
		this.likeListBeanList = likeListBeanList;
        length = (ViewUtils.getPhoneWidth(ctx) - ViewUtils.dp2px(ctx, 129)) / 9;
	}
	@Override
	public int getCount() {
		return likeListBeanList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_gv_prise_head, parent,false);
			holder.img = convertView.findViewById(R.id.img_head);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		ShareListBean.ListBean.LikeListBean listBean = likeListBeanList.get(position);
		LayoutParams lp = (LayoutParams) holder.img.getLayoutParams();
		lp.width = length;
		lp.height = length;
		holder.img.setLayoutParams(lp);

		Glide.with(ctx).load(listBean.picsurl).error(R.mipmap.pre_default_image).into(holder.img);

		return convertView;
	}
	
	class ViewHolder{
		ImageView img;
	}
}
