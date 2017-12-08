package com.ztstech.vgmate.activitys.search_org;

import com.ztstech.appdomain.user_case.GetSearchOrg;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.constants.NetConstants;
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
    public void LoadDataByKeword(String keyword) {
        currentpage = 1;
        requestData(keyword);
    }

    @Override
    public void appendDada(String keyword) {
        if (currentpage == totalpage){
            mView.setListData(listBeen);
        }else {
            currentpage++;
        }
    }

    private void requestData(String keyword){
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
        }.run(new GetSearchOrg(currentpage,keyword,"110000").run());
    }

}
