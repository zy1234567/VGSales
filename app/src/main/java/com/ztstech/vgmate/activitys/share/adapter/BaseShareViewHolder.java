package com.ztstech.vgmate.activitys.share.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class BaseShareViewHolder extends SimpleViewHolder<ShareListBean.ListBean>{

    public static final String STATUS_PRISE = "01";
    public static final String STATUS_UN_PRISE = "00";

    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
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

    /**
     * 四张图片时gridview宽度
     */
    private static int FOUR_IMGS_WIDTH;

    /**
     * 不是四张图片时的gridview宽度
     */
    private static int NO_FOUR_IMGS_WIDTH;

    private ClickCallback clickCallback;

    public BaseShareViewHolder(View itemView,ClickCallback callback) {
        super(itemView);
        this.clickCallback = callback;
        FOUR_IMGS_WIDTH = ViewUtils.messureFourImgWidth(getContext());
        NO_FOUR_IMGS_WIDTH = ViewUtils.messureNoFourImgWidth(getContext());
    }

    @Override
    protected void refreshView(final ShareListBean.ListBean data) {
        super.refreshView(data);
        Glide.with(getContext()).load(data.userpicsurl).error(R.mipmap.pre_default_image).into(imgHead);
        tvName.setText(data.uname);
        tvTime.setText(TimeUtils.informationTime(data.createtime));
        if (TextUtils.equals(data.likestatus,STATUS_PRISE)){
            imgPrise.setImageResource(R.mipmap.ico_zan_y);
        }else {
            imgPrise.setImageResource(R.mipmap.ico_zan);
        }
        if (TextUtils.equals(data.uid, UserRepository.getInstance().getUser().getUserBean().info.uid)
                || UserRepository.getInstance().getUser().enableDeleteComment()){
            tvDelete.setVisibility(View.VISIBLE);
            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickCallback.onClickDelete(data);
                }
            });
        }
        imgPrise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallback.onClickPrise(data);
                if (TextUtils.equals(data.likestatus,STATUS_PRISE)){
                    data.likestatus = STATUS_UN_PRISE;
                    imgPrise.setImageResource(R.mipmap.ico_zan);
                }else {
                    data.likestatus = STATUS_PRISE;
                    imgPrise.setImageResource(R.mipmap.ico_zan_y);
                }
            }
        });
    }

    /**
     * 四张图片时需要设置成四格样式gridview
     */
    protected void setGridViewWidth(String[] imgs, GridView gridView) {
        if (imgs == null) {
            return;
        }
        //四张图时的布局
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) gridView.getLayoutParams();
        if (imgs.length == 4) {
            gridView.setNumColumns(2);
            lp.width = FOUR_IMGS_WIDTH;
            lp.height = FOUR_IMGS_WIDTH;
        } else {
            gridView.setNumColumns(3);
            lp.width = NO_FOUR_IMGS_WIDTH;
            lp.height = NO_FOUR_IMGS_WIDTH;
        }
        gridView.setLayoutParams(lp);
    }

    public interface ClickCallback{
        void onClickPrise(ShareListBean.ListBean data);
        void onClickDelete(ShareListBean.ListBean data);
    }
}
