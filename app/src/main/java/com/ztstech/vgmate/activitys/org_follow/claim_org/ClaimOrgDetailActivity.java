package com.ztstech.vgmate.activitys.org_follow.claim_org;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.user_case.ApproveClaimOrg;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_follow.adapter.OrgProveImgAdapter;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.events.ApproveOrgEvent;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;
import com.ztstech.vgmate.weigets.IOSStyleDialog;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2017/12/5
 * 机构认领详情界面
 */

public class ClaimOrgDetailActivity extends MVPActivity<ClaimOrgDetailContact.Presenter> implements ClaimOrgDetailContact.View{

    /**
     * 传入显示信息的实体类
     */
    public static final String KEY_BEAN = "bean";

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.img_org)
    ImageView imgOrg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_otype)
    TextView tvOtype;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_real_name)
    TextView tvRealName;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_claim_phone)
    TextView tvClaimPhone;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;

    /**
     * 相关证明适配器
     */
    private OrgProveImgAdapter adapter;

    /**
     * 显示信息的实体类
     */
    private OrgFollowlistBean.ListBean bean;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        bean = (OrgFollowlistBean.ListBean) getIntent().getSerializableExtra(KEY_BEAN);
        if (bean == null) {
            throw new NullPointerException();
        }
        showInfo();
        showProveImg();
    }

    private void showInfo() {
        tvName.setText(bean.rbioname);
        Glide.with(this)
                .load(bean.rbilogosurl)
                .error(R.mipmap.ic_launcher)
                .into(imgOrg);
        tvOtype.setText(CategoryUtil.findCategoryByOtype(bean.rbiotype));
        tvAddress.setText(LocationUtils.getPName(bean.rbiprovince).concat(LocationUtils.getCName(bean.rbicity)).
                concat(LocationUtils.getAName(bean.rbidistrict))
                .concat(bean.rbiaddress));
        String[] strs = new String[]{"咨询电话：", bean.rbicontphone};
        int[] colors = new int[]{ContextCompat.getColor(this, R.color.color_102),
                ContextCompat.getColor(this, R.color.color_004)};
        SpannableStringBuilder spannableStringBuilder =
                ViewUtils.getDiffColorSpan(null, strs, colors);
        tvPhone.setText(spannableStringBuilder);
        tvRealName.setText("真实姓名：".concat(bean.name));
        tvJob.setText("担任职位：".concat(bean.position));
        tvClaimPhone.setText(bean.phone);
    }

    /**
     * 展示资质文件
     */
    private void showProveImg() {
        adapter = new OrgProveImgAdapter();
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        recycler.setAdapter(adapter);
        final List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(bean.aptitudeurl)) {
            recycler.setVisibility(View.GONE);
            return;
        }
        list.addAll(CommonUtil.imgUrlsToList(bean.aptitudeurl));
        adapter.setListData(list);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_claim_org_detail;
    }

    @OnClick({R.id.tv_phone, R.id.tv_claim_phone, R.id.tv_pass, R.id.tv_refuse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+bean.rbicontphone));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.tv_claim_phone:
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+bean.phone));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
            case R.id.tv_pass:
                new IOSStyleDialog(this, "您确定要通过吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.approveOrg(String.valueOf(bean.rbiid),bean.calid,ApproveClaimOrg.STATUS_PASS);
                    }
                }).show();
                break;
            case R.id.tv_refuse:
                new IOSStyleDialog(this, "您确定要拒绝吗？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.approveOrg(String.valueOf(bean.rbiid),bean.calid, ApproveClaimOrg.STATUS_REFUSE);
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected ClaimOrgDetailContact.Presenter initPresenter() {
        return new ClaimDetailPresenter(this);
    }

    @Override
    public void onApproveSuccess() {
        EventBus.getDefault().post(new ApproveOrgEvent());
        ToastUtil.toastCenter(this,"审批成功");
        finish();
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(this,msg);
    }
}
