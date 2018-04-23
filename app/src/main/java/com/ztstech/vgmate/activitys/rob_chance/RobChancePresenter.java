package com.ztstech.vgmate.activitys.rob_chance;

import android.text.TextUtils;
import android.widget.TextView;

import com.ztstech.appdomain.user_case.LastTime;
import com.ztstech.appdomain.user_case.RobChance;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.data.beans.LastTimeBean;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;
import com.ztstech.vgmate.utils.TimeUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChancePresenter extends PresenterImpl<RobChanceContract.View> implements RobChanceContract.Presenter {
    private int currentpage = 1,totalpage;
    private List<RobChanceBean.ListBean> listBean = new ArrayList<>();
    public RobChancePresenter(RobChanceContract.View view) {
        super(view);
    }

    @Override
    public void loadData() {
        currentpage = 1;
        requestData();
    }

    @Override
    public void appendData() {

    }

    /**
     * 锁定抢单
     * @param rbiid
     */
    @Override
    public void lockOrg(final String rbiid, final TextView textView, final String object, final int i, final String j) {
        new BasePresenterSubscriber<RobChanceBean>(mView,false){

            @Override
            protected void childNext(RobChanceBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    requestlastTime(rbiid,textView,object,i,j);
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new RobChance(0,rbiid).run());
    }

    @Override
    public void lasttime(String rbiid, TextView textView, String object, int i, String j) {
        requestlastTime(rbiid,textView,object,i,j);
    }

    /**
     * 请求可抢单列表数据
     */
    private void requestData(){
        new BasePresenterSubscriber<RobChanceBean>(mView,false){

            @Override
            protected void childNext(RobChanceBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    currentpage = getComRecordBean.pager.currentPage;
                    totalpage = getComRecordBean.pager.totalPages;

                    if (currentpage == 1) {
                        //刷新
                        listBean.clear();
                    }
                    listBean.addAll(getComRecordBean.list);
                    mView.setData(listBean);
                    mView.setDataPage(getComRecordBean.pager);
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new RobChance(currentpage,null).run());
    }
    /**
     * 请求剩余时间
     */
    private void requestlastTime(String rbiid, final TextView textView, final String object, final int i, final String j){
        new BasePresenterSubscriber<LastTimeBean>(mView,false){

            @Override
            protected void childNext(LastTimeBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    if (getComRecordBean.lasttimeMillis > 30) {
                        mView.setLastTime(getComRecordBean.lasttimeMillis);
                        mView.onSubmitFinish(null, textView, object, i, j);
                    }else{
                        textView.setText("抢单");
                        textView.setBackgroundResource(R.drawable.bg_c_1_f_006);
                        return;
                    }
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new LastTime(rbiid).run());
    }
}
