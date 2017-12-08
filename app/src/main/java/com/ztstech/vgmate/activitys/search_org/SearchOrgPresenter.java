package com.ztstech.vgmate.activitys.search_org;

import com.ztstech.appdomain.user_case.GetSearchOrg;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.SearchOrgListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by smm on 2017/12/8.
 */

public class SearchOrgPresenter extends PresenterImpl<SearchOrgContact.View> implements SearchOrgContact.Presenter{

    private int currentpage = 1;

    private int totalpage;

    public SearchOrgPresenter(SearchOrgContact.View view) {
        super(view);
    }

    @Override
    public void LoadDataByKeword(String keyword) {

    }

    @Override
    public void appendDada(String keyword) {

    }

    private void requestData(String keyword){
        new BasePresenterSubscriber<SearchOrgListBean.ListBean>(mView){

            @Override
            protected void childNext(SearchOrgListBean.ListBean listBean) {

            }

            @Override
            protected void childError(Throwable e) {

            }
        }.run(new GetSearchOrg(currentpage,keyword,"110000").run());
    }

}
