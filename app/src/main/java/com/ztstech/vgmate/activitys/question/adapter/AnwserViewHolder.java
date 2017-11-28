package com.ztstech.vgmate.activitys.question.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.AnwserListBean;
import com.ztstech.vgmate.utils.TimeUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by smm on 2017/11/22.
 */

public class AnwserViewHolder extends SimpleViewHolder<AnwserListBean.ListBean> {

    public static final String STATUS_PRISE = "01";
    public static final String STATUS_UN_PRISE = "00";

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_prise_num)
    TextView tvPriseNum;
    @BindView(R.id.img_prise)
    ImageView imgPrise;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_body)
    RelativeLayout rlBody;
    @BindView(R.id.prise_view)
    View priseView;

    ClickCallBack longClickCallBack;

    public AnwserViewHolder(View itemView,ClickCallBack longClickCallBack) {
        super(itemView);
        this.longClickCallBack = longClickCallBack;
    }

    @Override
    protected void refreshView(final AnwserListBean.ListBean data) {
        super.refreshView(data);
        tvName.setText(data.uname);
        tvPriseNum.setText(String.valueOf(data.likedCnt));
        tvTime.setText(TimeUtils.informationTime(data.ansCreatetime));
        Glide.with(getContext())
                .load(data.picsurl)
                .error(R.mipmap.ic_launcher)
                .into(imgHead);
        tvContent.setText(data.content);
        if (STATUS_PRISE.equals(data.likeStatus)){
            imgPrise.setImageResource(R.mipmap.zan_y);
        }else {
            imgPrise.setImageResource(R.mipmap.zan_g);
        }
        rlBody.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (UserRepository.getInstance().getUser().enableDeleteArticle()
                        || TextUtils.equals(data.ansPublishUid,
                            UserRepository.getInstance().getUser().getUserBean().info.uid)){
                    longClickCallBack.onLongClick(data);
                }
                return false;
            }
        });
        priseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prisecnt = data.likedCnt;
                if (STATUS_PRISE.equals(data.likeStatus)){
                    imgPrise.setImageResource(R.mipmap.zan_g);
                    data.likedCnt = prisecnt - 1;
                    data.likeStatus = STATUS_UN_PRISE;
                }else {
                    imgPrise.setImageResource(R.mipmap.zan_y);
                    data.likedCnt = prisecnt + 1;
                    data.likeStatus = STATUS_PRISE;
                }
                tvPriseNum.setText(String.valueOf(data.likedCnt));
                longClickCallBack.onClickPrise(data);
            }
        });
    }

    public interface ClickCallBack{
        void onLongClick(AnwserListBean.ListBean data);
        void onClickPrise(AnwserListBean.ListBean data);
    }
}
