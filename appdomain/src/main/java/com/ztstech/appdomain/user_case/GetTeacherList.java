package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.TeacherApi;
import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/19.
 * 查询医师列表
 */

public class GetTeacherList implements UserCase<Observable<TeacherListBean>> {

    private int rbiid;
    /**
     * 这个接口返回了分页信息，传递页数1返回超出页数
     */
    private int pageNo;

    private TeacherApi api;

    public GetTeacherList(int rbiid, int pageNo) {
        this.rbiid = rbiid;
        this.pageNo = pageNo;
        api = RetrofitUtils.createService(TeacherApi.class);
    }

    @Override
    public Observable<TeacherListBean> run() {
        return api.getTeacherList(rbiid, UserRepository.getInstance().getAuthId());
    }
}
