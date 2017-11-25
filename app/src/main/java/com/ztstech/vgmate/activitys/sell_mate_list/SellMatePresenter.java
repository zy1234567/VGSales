package com.ztstech.vgmate.activitys.sell_mate_list;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.user_case.GetMateList;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.base.BaseApplicationLike;
import com.ztstech.vgmate.data.beans.MatelistBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/23
 */

public class SellMatePresenter extends PresenterImpl<SellMateContact.View> implements SellMateContact.Presenter{

    private static final String SELL_MATE_LIST = "sell_mate_list";

    private int currrntPage = 1,totalpage;

    private SharedPreferences preferences;

    private List<MatelistBean.ListBean> listBeen;


    public SellMatePresenter(SellMateContact.View view) {
        super(view);
        listBeen = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(BaseApplicationLike.getApplicationInstance());
    }

    @Override
    public void loadCacheData() {
        String cacheJson = preferences.getString(SELL_MATE_LIST + UserRepository.getInstance().getUser().getUserBean().info.uid,"");
        if (!TextUtils.isEmpty(cacheJson)){
            MatelistBean matelistBean = new Gson().fromJson(cacheJson, MatelistBean.class);
            if (matelistBean != null){
                mView.setListData(matelistBean.list);
            }

        }
    }

    @Override
    public void loadNetData(String myflg,String filtername) {
        currrntPage = 1;
        requestListData(myflg,filtername);
    }

    @Override
    public void appendData(String myflg,String filtername) {
        if (currrntPage == totalpage){
            mView.setListData(listBeen);
        }else {
            currrntPage++;
            requestListData(myflg,filtername);
        }
    }

    private void requestListData(final String myflg, final String filtername){
        new BasePresenterSubscriber<MatelistBean>(mView,false){

            @Override
            protected void childNext(MatelistBean matelistBean) {
                if (matelistBean.isSucceed()){
                    totalpage = matelistBean.pager.totalPages;
                    mView.setNoMoreData(totalpage == currrntPage);
                    if (currrntPage == 1){
                        listBeen.clear();
                        if (GetMateList.FILTER_ALL.equals(myflg) && TextUtils.isEmpty(filtername)) {
                            // 不是搜索界面且不是筛选自己的才缓存
                            preferences.edit().putString(SELL_MATE_LIST +
                                    UserRepository.getInstance().getUser().getUserBean().info.uid, new Gson().toJson(matelistBean)).apply();
                        }
                    }
                    listBeen.addAll(matelistBean.list);
                    mView.setListData(listBeen);
                }else {
                    mView.showError("查询销售伙伴列表出错:".concat(matelistBean.errmsg));
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("查询销售伙伴列表出错:".concat(e.getMessage()));
            }
        }.run(new GetMateList(currrntPage,myflg,filtername).run());
    }

}
