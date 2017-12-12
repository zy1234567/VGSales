package com.ztstech.vgmate.activitys.search_org;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.ztstech.appdomain.user_case.GetSearchOrg;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.manager.GpsManager;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smm on 2017/12/8.
 */

public class SearchOrgPresenter extends PresenterImpl<SearchOrgContact.View> implements SearchOrgContact.Presenter{

    private int currentpage = 1;

    private int totalpage;

    private List<OrgFollowlistBean.ListBean> listBeen = new ArrayList<>();

    public SearchOrgPresenter(SearchOrgContact.View view) {
        super(view);
    }

    @Override
    public void LoadDataByKeword(String keyword,String district) {
        currentpage = 1;
        requestData(keyword,district);
    }

    @Override
    public void appendDada(String keyword,String district) {
        if (currentpage == totalpage){
            mView.setListData(listBeen);
        }else {
            currentpage++;
            requestData(keyword,district);
        }
    }

    private void requestData(String keyword,String district){
//        String rbidistrict = new GpsManager(BaseApplicationLike.getApplicationInstance()).getSaveDistrict();
        if (TextUtils.isEmpty(district)){
            // 之前没有定位成功 先默认搜索北京
            district = "110115";
            new GpsManager(BaseApplicationLike.getApplicationInstance()).getGpsInfo();
        }
//        else {
//            // 之前定位成功存的是三级码 变为二级码
//            rbidistrict = rbidistrict.substring(0,4) + "00";
//        }
        new BasePresenterSubscriber<OrgFollowlistBean>(mView){

            @Override
            protected void childNext(OrgFollowlistBean bean) {
                if (bean.isSucceed()){
                    totalpage = bean.pager.totalPages;
                    if (currentpage == 1){
                        listBeen.clear();
                    }
                    listBeen.addAll(bean.list);
                    mView.setListData(listBeen);
                }else {
                    mView.showError(bean.errmsg);
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError(NetConstants.INTERNET_ERROR_HINT);
            }
        }.run(new GetSearchOrg(currentpage,keyword,district).run());
    }

}
