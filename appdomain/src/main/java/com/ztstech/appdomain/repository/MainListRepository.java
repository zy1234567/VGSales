package com.ztstech.appdomain.repository;

import com.ztstech.vgmate.data.api.MainListApi;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class MainListRepository {

    private MainListApi api;

    private static MainListRepository instance;

    private MainListRepository() {
        api = RetrofitUtils.createService(MainListApi.class);
    }

    public static MainListRepository getInstance() {
        if (instance == null) {
            synchronized (MainListRepository.class) {
                if (instance == null) {
                    instance = new MainListRepository();
                }
            }
        }
        return instance;
    }

    /**
     * 查询通告
     * @return
     */
    public Observable<MainListBean> queryNotice(int currentPage) {
        return api.queryList(UserRepository.getInstance().getAuthId(), "01", currentPage);
    }

    /**
     * 查询资讯
     * @return
     */
    public Observable<MainListBean> queryInformation(int currentPage) {
        return api.queryList(UserRepository.getInstance().getAuthId(), "00", currentPage);
    }
}
