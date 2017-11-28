package com.ztstech.vgmate.activitys.share;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztstech.appdomain.user_case.DeleteMyShare;
import com.ztstech.appdomain.user_case.GetShareList;
import com.ztstech.appdomain.user_case.PriseShare;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.share.adapter.BaseShareViewHolder;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class SharePresenter extends PresenterImpl<ShareContact.View> implements ShareContact.Presenter{

    public static final String SHARE_LIST = "share_list";

    private int currentpage = 1,totalpage;

    private SharedPreferences preferences;

    private List<ShareListBean.ListBean> listBeen;

    public SharePresenter(ShareContact.View view) {
        super(view);
        listBeen = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }

    @Override
    public void loadCacheData() {
        String cachejson = preferences.getString(SHARE_LIST,"");
        if (!TextUtils.isEmpty(cachejson)){
            ShareListBean shareListBean = new Gson().fromJson(cachejson,ShareListBean.class);
            if (shareListBean != null){
                mView.setListData(listBeen);
            }
        }
    }

    @Override
    public void loadNetData() {
        currentpage = 1;
        requestData();
    }

    @Override
    public void appendData() {
        if (currentpage == totalpage){
            mView.setListData(listBeen);
        }else {
            currentpage ++;
            requestData();
        }
    }

    private void requestData(){
        new BasePresenterSubscriber<ShareListBean>(mView,false){

            @Override
            protected void childNext(ShareListBean shareListBean) {
                if (shareListBean.isSucceed()){
                    if (currentpage == 1){
                        totalpage = shareListBean.pager.totalPages;
                        listBeen.clear();
                        preferences.edit().putString(SHARE_LIST,new Gson().toJson(shareListBean)).apply();
                    }
                    listBeen.addAll(shareListBean.list);
                    mView.setListData(listBeen);
                }else {
                    mView.showError("查询分享列表失败:".concat(shareListBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询分享列表失败:".concat(e.getMessage()));
            }
        }.run(new GetShareList(currentpage).run());
    }

    @Override
    public void deleteShare(String sid) {
        new BasePresenterSubscriber<BaseRespBean>(mView){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onDeleteSuccess();
                }else {
                    mView.showError("删除分享出错：".concat(baseRespBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("删除分享出错：".concat(e.getMessage()));
            }
        }.run(new DeleteMyShare(sid).run());
    }

    @Override
    public void priseShare(ShareListBean.ListBean data) {
        String status = TextUtils.equals(data.likestatus, BaseShareViewHolder.STATUS_PRISE)
                ? BaseShareViewHolder.STATUS_UN_PRISE : BaseShareViewHolder.STATUS_PRISE;
        new BasePresenterSubscriber<BaseRespBean>(mView,false){

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){

                }else {
                    mView.showError("点赞出错：".concat(baseRespBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("点赞出错：".concat(e.getMessage()));
            }
        }.run(new PriseShare(data.sid,status,data.sid,data.uid).run());
    }

}
