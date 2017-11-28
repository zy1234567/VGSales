package com.ztstech.vgmate.activitys.share.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.ztstech.vgmate.utils.ViewUtils;

import java.util.List;

public class PriseHeadAdapter extends BaseAdapter {
	
	private Context ctx;
	private List<String> urls;

    /** 点赞图片边长 */
    private static int length;

	public PriseHeadAdapter(Context ctx, List<String> urls){
		this.ctx = ctx;
		this.urls = urls;
        length = (ViewUtils.getPhoneWidth(ctx) - ViewUtils.dp2px(ctx, 129)) / 9;
	}
	@Override
	public int getCount() {
		return urls.size();
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
//		if (convertView == null) {
//			holder = new ViewHolder();
//			convertView = LayoutInflater.from(ctx).inflate(R.layout.item_gv_prise_head, parent,false);
//			holder.img = (ImageView) convertView.findViewById(R.id.img_head);
//			convertView.setTag(holder);
//		}else{
//			holder = (ViewHolder) convertView.getTag();
//		}
//		LayoutParams lp = (LayoutParams) holder.img.getLayoutParams();
//		lp.width = length;
//		lp.height = length;
//		holder.img.setLayoutParams(lp);
//        String uid = "";
//		String orgid = "";
//		String[] array = urls.get(position).split("!@");
//		//后台拼接的是picsurl + uid + orgid
//		String url = array[0];//点赞人头像地址
//		if (array.length > 1) {
//			uid = array[1];//点赞人uid;
//		}
//		if (array.length > 2){
//			orgid = array[2];//点赞人orgid
//		}
//        PicassoUtil.showImage(ctx,url,holder.img);
//
//        final String finalUid = uid;
//		final String finalUrl = url;
//		final String finalOrgid = orgid;
//		holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //TODO:进入空间
//				/*Intent intent = new Intent(ctx, BaseSpaceActivity.class);
//				intent.putExtra("uid",finalUid);
//				intent.putExtra("uname","");
//				intent.putExtra("picurl",finalUrl);
//				ctx.startActivity(intent);*/
//				Go2SpaceUtil.goToSapce(ctx,finalUid, StringUtils.isEmptyString(finalOrgid) ? "01" : "02", finalOrgid);
//            }
//        });
		return convertView;
	}
	
	class ViewHolder{
		ImageView img;
	}
}
