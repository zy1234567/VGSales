package com.ztstech.vgmate.mapper;

import com.ztstech.vgmate.data.beans.UpdateUserInfoBean;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zhiyuan on 2017/8/23.
 */

public class UserInfoBeanMapper implements Mapper<FillInfoModel, UpdateUserInfoBean> {

    @Override
    public UpdateUserInfoBean transform(FillInfoModel bean) {
        UpdateUserInfoBean result = new UpdateUserInfoBean();
        result.banks = bean.cardBank;
        result.bname = bean.cardMaster;
        result.cardNo = bean.cardNo;
        result.did = bean.id;
        result.sex = bean.sex;

//        result.picurl = FileUtils.getBytes(bean.headerFile);
//        result.didurl = new String(FileUtils.getBytes(bean.idFile)) + "," + new String(FileUtils.getBytes(bean.idBackFile));
//        result.cardUrl = FileUtils.getBytes(bean.cardFile);

        return result;
    }

}
