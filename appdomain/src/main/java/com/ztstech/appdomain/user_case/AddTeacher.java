package com.ztstech.appdomain.user_case;

import com.ztstech.vgmate.data.api.TeacherApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddTeacherData;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by zhiyuan on 2017/10/20.
 * 添加教师
 */

public class AddTeacher implements UserCase<Observable<BaseRespBean>> {


    private AddTeacherData data;

    private TeacherApi teacherApi;

    public AddTeacher(AddTeacherData data) {
        this.data = data;
        this.teacherApi = RetrofitUtils.createService(TeacherApi.class);
    }


    @Override
    public Observable<BaseRespBean> run() {
        return teacherApi.addTeacher(data.rbiid,
                data.name,
                data.age,
                data.tecphone,
                data.sex,
                data.position,
                data.introduction,
                data.logourl,
                data.logosurl,
                UserRepository.getInstance().getAuthId());
    }
}
