package com.ztstech.appdomain.user_case;

import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.data.api.GetMateDetailApi;
import com.ztstech.vgmate.data.api.GetMateListApi;
import com.ztstech.vgmate.data.beans.MatelistBean;

import io.reactivex.Observable;

/**
 *
 * @author smm
 * @date 2017/11/23
 * 获取销售伙伴列表
 */

public class GetMateList implements UserCase<Observable<MatelistBean>> {

    public static final String FILTER_ALL = "00";

    public static final String FILTER_MINE = "01";

    private int pageNo;

    private String type;

    /** 搜索的伙伴姓名 */
    private String filtername;

    private GetMateListApi api;

    public GetMateList(int pageNo,String type,String filtername){
        api = RetrofitUtils.createService(GetMateListApi.class);
        this.pageNo = pageNo;
        this.type = type;
        this.filtername = filtername;
    }

    @Override
    public Observable<MatelistBean> run() {
        return api.getMateList(UserRepository.getInstance().getAuthId(),pageNo,type,filtername);
    }
}
