package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.GetUnApproveMateApi;
import com.ztstech.vgmate.data.beans.WaitApproveMateListBean;

import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/16
 */

public class GetUnApproveMateList  implements UserCase<Observable<WaitApproveMateListBean>>{

    public static final String FILTER_ALL = "00";

    public static final String FILTER_MINE = "01";

    private int page;

    private GetUnApproveMateApi api;

    /** 是否是查看我的 */
    private String myflg;

    public GetUnApproveMateList(int page,String myflg){
        this.page = page;
        this.myflg = myflg;
        api = RetrofitUtils.createService(GetUnApproveMateApi.class);
    }

    @Override
    public Observable<WaitApproveMateListBean> run() {
        return api.queryList(UserRepository.getInstance().getAuthId()
                ,UserRepository.getInstance().getUser().getUserBean().info.uid,myflg,page);
    }
}
