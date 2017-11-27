package com.ztstech.vgmate.activitys.share.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.ShareListBean;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class BaseShareViewHolder extends SimpleViewHolder<ShareListBean.ListBean>{

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_relation)
    TextView tvRelation;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_right_one)
    TextView tvRightOne;
    @BindView(R.id.tv_right_two)
    TextView tvRightTwo;
    @BindView(R.id.share_fragment_adapter_head_rl)
    RelativeLayout shareFragmentAdapterHeadRl;
    @BindView(R.id.middle_layout)
    LinearLayout middleLayout;
    @BindView(R.id.tv_trans_num)
    TextView tvTransNum;
    @BindView(R.id.img_write_comment)
    ImageView imgWriteComment;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.tv_prise_num)
    TextView tvPriseNum;
    @BindView(R.id.img_prise)
    ImageView imgPrise;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.bottom_bar_layout)
    RelativeLayout bottomBarLayout;
    @BindView(R.id.space_item_margin)
    View spaceItemMargin;
    @BindView(R.id.lt_commemt_share)
    LinearLayout ltCommemtShare;

    public BaseShareViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void refreshView(ShareListBean.ListBean data) {
        super.refreshView(data);
    }
}
