package com.ztstech.vgmate.activitys.rob_chance.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty;
import com.ztstech.vgmate.base.SimpleRecyclerAdapter;
import com.ztstech.vgmate.base.SimpleViewHolder;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.TimeUtils;

import org.w3c.dom.Text;

import butterknife.BindView;

import static com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty.ORG_BEAN_ROB;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceViewHolder extends SimpleViewHolder<RobChanceBean.ListBean> {

    @BindView(R.id.tv_add_type)
    TextView tvAddType;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_rob_type)
    TextView tvRobType;
    @BindView(R.id.img_org)
    ImageView imgOrg;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_linkman)
    TextView tvLinkman;
    @BindView(R.id.tv_service)
    TextView tvService;
    public static final int PASSER_CHECK_IN = 11;
    public static final int ORG_CHECK_IN_OR_CALIM = 12;
    private lockorgCallBack callBack;
    public RobChanceViewHolder(View itemView,lockorgCallBack callBack) {
        super(itemView);
        this.callBack = callBack;
    }

    @Override
    protected void refreshView(final RobChanceBean.ListBean data) {
        super.refreshView(data);
        CommonUtil.orgfFromType(getContext(),tvAddType,data.cstatus,data.nowchancetype,data.chancetype);
        Glide.with(getContext())
                .load(data.rbilogosurl)
                .error(R.mipmap.ic_launcher)
                .into(imgOrg);
        tvTime.setText(TimeUtils.informationTime(data.createtime));
        tvName.setText(data.rbioname);
        tvAddress.setText("地区：".concat(LocationUtils.getPName(data.rbiprovince).concat(LocationUtils.getCName(data.rbicity))).concat(LocationUtils.getAName(data.rbidistrict)));
        if (!TextUtils.isEmpty(data.contractname) && !TextUtils.isEmpty(data.contractphone)) {
            tvLinkman.setText("联系人：".concat(data.contractname).concat(data.contractphone));
        }else{
            tvLinkman.setText("联系人：暂无");
        }
        tvService.setText("客服：".concat(data.marketname));
        if (TextUtils.equals(data.locktype, Constants.LOCK_YES) &&
                !TextUtils.equals(UserRepository.getInstance().getUser().getUserBean().info.uid,data.locksaleuid)) {
            tvRobType.setText("锁定中");
            tvRobType.setBackgroundResource(R.drawable.bg_c_1_f_002);
        }else if (TextUtils.equals(data.locktype, Constants.LOCK_YES) &&
                TextUtils.equals(UserRepository.getInstance().getUser().getUserBean().info.uid,data.locksaleuid)){
            tvRobType.setText("处理中");
            tvRobType.setBackgroundResource(R.drawable.bg_c_1_f_009);
        } else {
            tvRobType.setText("抢单");
            tvRobType.setBackgroundResource(R.drawable.bg_c_1_f_006);
        }
        tvRobType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(data.locktype, Constants.LOCK_YES)){
                    DialogUtils.showdialogknow(getContext(), "该机会正在其他销售的抢单锁定中，请稍后再试。",
                            "提示", new DialogUtils.showdialogCallBack() {
                                @Override
                                public void knowclick() {

                                }
                            });
                }else if (TextUtils.equals(data.locktype, Constants.LOCK_YES) &&
                        TextUtils.equals(UserRepository.getInstance().getUser().getUserBean().info.uid,data.locksaleuid)){

                } else{
                    DialogUtils.showdialogbottomtwobutton(getContext(), "抢单", "取消", "提示",
                            "请在30分钟内完成与机构的沟通、填写沟通记录、完成审核流程等操作。", new DialogUtils.showdialogbottomtwobuttonCallBack() {
                                @Override
                                public void tvRightClick() {
                                    if (identity(data.cstatus,data.nowchancetype,data.chancetype) == PASSER_CHECK_IN ) {
                                        callBack.lockOrgClick(String.valueOf(data.rbiid), new Gson().toJson(data), tvRobType,PASSER_CHECK_IN);
                                    }else{
                                        callBack.lockOrgClick(String.valueOf(data.rbiid), new Gson().toJson(data), tvRobType,ORG_CHECK_IN_OR_CALIM);
                                    }
                                }

                                @Override
                                public void tvLeftClick() {

                                }
                            });
                }
            }
        });
    }
    //判断是路人登记还是机构登记认领的
    private int identity(String cstatus,String nowchancetype, String chancetype){
        //返回路人
        if (TextUtils.equals(cstatus,Constants.CSTATUS_GRAY_UNVERIFIED) &&
                (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_PASSER_CHECK_IN) ||
                TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_PASSER_CHECK_IN)) &&
                TextUtils.isEmpty(nowchancetype)){
            return PASSER_CHECK_IN;
        }
        if (TextUtils.equals(cstatus,Constants.CSTATUS_GRAY_UNVERIFIED) && TextUtils.isEmpty(nowchancetype)
                && (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_REGISTER) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_REGISTER) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_CHECK_IN) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_CHECK_IN) ||
        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_MAP_REGISTER))){
            return ORG_CHECK_IN_OR_CALIM;
        }
        return 0;
    }
    public interface lockorgCallBack{
        void lockOrgClick(String rbiid,String object,TextView textView,int i);
    }
}
