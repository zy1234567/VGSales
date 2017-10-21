package com.ztstech.vgmate.data.dto;

import com.ztstech.vgmate.data.beans.BaseRespBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/20.
 */

public class AddTeacherData {

    /**头像地址路径*/
    public String headerImagePath;

    public int rbiid;

    public String name;

    public String age;

    public String tecphone;

    public String sex;

    public String position;

    public String introduction;

    public String logourl;

    public String logosurl;

}
