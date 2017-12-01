package com.ztstech.vgmate.activitys.share.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.comment.CommentActivity;
import com.ztstech.vgmate.activitys.share.detail.ShareDetailActivity;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ViewUtils;
import com.ztstech.vgmate.weigets.MySearchGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    protected final int MAX_LINE_COUNT = 6;

    protected final int STATE_UNKNOW = -1;

    protected final int STATE_NOT_OVERFLOW = 1; //文本行数不超过限定行数

    protected final int STATE_COLLAPSED = 2; //文本行数超过限定行数,处于折叠状态

    protected final int STATE_COLLAPSED_30 = 4; //文本行数超过限定行数,处于折叠状态 且展开后行数大于30行

    protected final int STATE_EXPANDED = 3; //文本行数超过限定行数,被点击全文展开

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
    @BindView(R.id.img_arrow)
    ImageView imgArrow;

    /**
     * 四张图片时gridview宽度
     */
    private static int FOUR_IMGS_WIDTH;

    /**
     * 不是四张图片时的gridview宽度
     */
    private static int NO_FOUR_IMGS_WIDTH;

    private ClickCallback clickCallback;

    /**
     * 存储全文状态的集合
     */
    protected SparseArray<Integer> mTextStateList;

    public BaseShareViewHolder(View itemView,ClickCallback callback) {
        super(itemView);
        this.clickCallback = callback;
        FOUR_IMGS_WIDTH = ViewUtils.messureFourImgWidth(getContext());
        NO_FOUR_IMGS_WIDTH = ViewUtils.messureNoFourImgWidth(getContext());
        mTextStateList = new SparseArray<>();
    }

    @Override
    protected void refreshView(final ShareListBean.ListBean data) {
        Log.e("position",getPosition() + "--" +getAdapterPosition() + "--" + getOldPosition() + "--" + getLayoutPosition());
        super.refreshView(data);
        Glide.with(getContext()).load(data.userpicsurl).error(R.mipmap.pre_default_image).into(imgHead);
        tvName.setText(data.uname);
        tvTime.setText(TimeUtils.informationTime(data.createtime));
        showPriseAndCommentList(data);
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
                    data.likeList.remove(findPriseBean(data));
                }else {
                    data.likestatus = STATUS_PRISE;
                    imgPrise.setImageResource(R.mipmap.ico_zan_y);
                    ShareListBean.ListBean.LikeListBean likeBean = new ShareListBean.ListBean.LikeListBean();
                    likeBean.picsurl = UserRepository.getInstance().getUser().getUserBean().info.picsurl;
                    likeBean.uid = UserRepository.getInstance().getUser().getUserBean().info.uid;
                    data.likeList.add(likeBean);
                }
            }
        });

        shareFragmentAdapterHeadRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShareDetailActivity.class);
                intent.putExtra(ShareDetailActivity.KEY_BEAN, data);
                getContext().startActivity(intent);
            }
        });

        middleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShareDetailActivity.class);
                intent.putExtra(ShareDetailActivity.KEY_BEAN, data);
                getContext().startActivity(intent);
            }
        });

        imgComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getContext(), CommentActivity.class);
                it.putExtra(CommentActivity.FLG_COMMENT_TYPE, CommentActivity.FLG_SHARE);
                it.putExtra(CommentActivity.ARG_NEWSID, data.sid);
                getContext().startActivity(it);
            }
        });

        imgWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toShowCommentDialog(data);
            }
        });
    }

    /**
     * 查询自己所点赞的实体类在第几项
     * @return
     */
    private int findPriseBean(ShareListBean.ListBean data){
        if (data.likeList != null){
            for (int i = 0; i < data.likeList.size(); i++){
                if (TextUtils.equals(data.likeList.get(i).uid,
                        UserRepository.getInstance().getUser().getUserBean().info.uid)){
                    return i;
                }
            }
        }
        return 0;
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
        void comment(ShareListBean.ListBean data,String content);
    }

    /**
     * 显示点赞墙和评论列表
     */
    public void showPriseAndCommentList(final ShareListBean.ListBean bean) {
        ltCommemtShare.removeAllViews();
        List<ShareListBean.ListBean.LikeListBean> heads = bean.likeList;
        final List<ShareListBean.ListBean.CommentListBean> commentBeanList = bean.commentList;

        if ((heads != null && heads.size() > 0) ||
                (commentBeanList != null && commentBeanList.size() > 0)) {
            //
            ltCommemtShare.setVisibility(View.VISIBLE);
            imgArrow.setVisibility(View.VISIBLE);
        } else {
            ltCommemtShare.setVisibility(View.GONE);
            imgArrow.setVisibility(View.GONE);
        }
        if (heads != null && heads.size() > 0) {
            //点赞头像
            View priseview = LayoutInflater.from(getContext()).inflate(R.layout.view_prise_head_gv, null);
            MySearchGridView gv = priseview.findViewById(R.id.gv_head);
            gv.setAdapter(new PriseHeadAdapter(getContext(), heads));
            ltCommemtShare.addView(priseview);
        }

        //评论和点赞都有才显示分割线
        if (heads != null && heads.size() > 0 &&
                commentBeanList != null && commentBeanList.size() > 0) {
            View line = LayoutInflater.from(getContext()).inflate(R.layout.line_1px_color_black, null);
            ltCommemtShare.addView(line);
        }

        if (commentBeanList != null) {
            //评论列表
            for (int i = 0; i < commentBeanList.size(); i++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_share_comment, null);
                final TextView content = view.findViewById(R.id.second_message_name);
                ShareListBean.ListBean.CommentListBean commentBean = commentBeanList.get(i);
                String[] strs = null;
                int[] colors = null;
                if (TextUtils.isEmpty(commentBean.touid)){
                    // 一级评论
                    strs = new String[] {commentBean.name.concat("："),commentBean.comment};
                }else {
                    // 二级评论
                    strs = new String[] {commentBean.name.concat("@").
                            concat(commentBean.touname).concat("："),commentBean.comment};
                }
                colors = new int[] {ContextCompat.getColor(getContext(), R.color.color_004),
                        ContextCompat.getColor(getContext(), R.color.color_100)};
                SpannableStringBuilder spannableStringBuilder =
                        ViewUtils.getDiffColorSpan(null, strs, colors);
                content.setText(spannableStringBuilder);
                if (commentBeanList.size() == 1 && (heads == null ||
                        heads.isEmpty() || "null".equals(heads))) {
                    ltCommemtShare.setPadding(ViewUtils.dp2px(getContext(), 5), ViewUtils.dp2px(getContext(), 8),
                            ViewUtils.dp2px(getContext(), 5), ViewUtils.dp2px(getContext(), 5));
                }
                ltCommemtShare.addView(view);
            }

        }
    }

    @SuppressLint("NewApi")
    public void toShowCommentDialog(final ShareListBean.ListBean bean) {

        //创建Dialog并关联布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.news_list_fragment_comment_dialog, null);
        final Dialog dialog = new Dialog(getContext(), R.style.transdialog);
        dialog.setContentView(view);

        //设置宽度铺满屏幕
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;// 设置Dialog的宽度为屏幕宽度
        dialog.getWindow().setAttributes(layoutParams);

        //弹出键盘
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        //dialog的显示
        dialog.show();

        //关联内部的控件
        final EditText et = view.findViewById(R.id.comment_footer_edittext);
        final Button btn = view.findViewById(R.id.comment_footer_cancel_button);
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et.getText().toString().isEmpty()) {
                    btn.setEnabled(false);
                } else {
                    btn.setEnabled(true);
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (et.getText().toString().isEmpty()) {
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    clickCallback.comment(bean,et.getText().toString());
                }
            }
        });
    }

    /**
     * 初始化全文/收起的显示
     */
    protected void initQuanWen(final ShareListBean.ListBean data, final TextView tvQuanwen, final TextView tvContent, final int position, final String content) {
        int state = mTextStateList.get(position, STATE_UNKNOW);
        //如果该item是第一次初始化，则去获取文本的行数
        if (state == STATE_UNKNOW) {
            tvQuanwen.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回调会调用多次，获取完行数记得注销监听
                    tvContent.getViewTreeObserver().removeOnPreDrawListener(this);
                    //记录文本的状态
                    if (tvContent.getLineCount() > MAX_LINE_COUNT) {
                        tvContent.setMaxLines(MAX_LINE_COUNT);
                        tvQuanwen.setVisibility(View.VISIBLE);
                        tvQuanwen.setText("全文");
                        mTextStateList.put(position, tvContent.getLineCount() < 30 ? STATE_COLLAPSED : STATE_COLLAPSED_30);
                    } else {
                        tvQuanwen.setVisibility(View.GONE);
                        mTextStateList.put(position, STATE_NOT_OVERFLOW);
                    }
                    return true;
                }
            });
            tvContent.setMaxLines(Integer.MAX_VALUE);
            tvContent.setText(content);
        } else {
            //如果之前已经初始化过了，则使用保存的状态，无需再获取一次
            switch (state) {
                case STATE_NOT_OVERFLOW:
                    tvQuanwen.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    tvContent.setMaxLines(MAX_LINE_COUNT);
                    tvQuanwen.setVisibility(View.VISIBLE);
                    tvQuanwen.setText("全文");
                    break;
                case STATE_EXPANDED:
                    tvContent.setMaxLines(Integer.MAX_VALUE);
                    tvQuanwen.setVisibility(View.VISIBLE);
                    tvQuanwen.setText("收起");
                    break;
                default:
                    break;
            }
            tvContent.setText(content);
        }

        tvQuanwen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* 全文 */
                /** 全文展开： （1）1~6行，直接显示；
                 * （2）7~30行，显示六行，“全文”，点击直接在圈子页面展开全文；
                 * （3）30行以上，显示六行，“全文”，点击全文进详情。*/
                int state = mTextStateList.get(getPosition(), STATE_UNKNOW);
                if (state == STATE_COLLAPSED) {
                    mTextStateList.put(getPosition(), STATE_EXPANDED);
                    tvContent.setMaxLines(Integer.MAX_VALUE);
                    tvQuanwen.setText("收起");
                } else if (state == STATE_COLLAPSED_30){
                    Intent intent = new Intent(getContext(), ShareDetailActivity.class);
                    intent.putExtra(ShareDetailActivity.KEY_BEAN, data);
                    getContext().startActivity(intent);
                }else if (state == STATE_EXPANDED) {
                    if (tvContent.getLineCount() < 30) {
                        mTextStateList.put(getPosition(), STATE_COLLAPSED);
                    }else {
                        mTextStateList.put(getPosition(), STATE_COLLAPSED_30);
                    }
                    tvContent.setMaxLines(MAX_LINE_COUNT);
                    tvQuanwen.setText("全文");

                }
            }
        });

    }


}
