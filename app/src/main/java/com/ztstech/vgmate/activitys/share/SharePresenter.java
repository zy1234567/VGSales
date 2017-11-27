package com.ztstech.vgmate.activitys.share;

import com.ztstech.appdomain.user_case.GetShareList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by smm on 2017/11/27.
 */

public class SharePresenter extends PresenterImpl<ShareContact.View> implements ShareContact.Presenter{

    public static final String SHARE_LIST = "share_list";

    private int currentpage = 1,totalpage;

    public SharePresenter(ShareContact.View view) {
        super(view);
    }

    @Override
    public void loadCacheData() {

    }

    @Override
    public void loadNetData() {
        new BasePresenterSubscriber<ShareListBean>(mView,false){

            @Override
            protected void childNext(ShareListBean shareListBean) {

            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询分享列表失败:".concat(e.getMessage()));
            }
        }.run(new GetShareList(currentpage).run());
    }

    @Override
    public void appendData() {

    }

    @Override
    public void deleteShare(String sid) {

    }

    @Override
    public void priseShare(String sid) {

    }

}
