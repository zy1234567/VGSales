package com.ztstech.vgmate.mapper;

import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;

/**
 * Created by zhiyuan on 2017/8/23.
 * 转换
 */

public class UserInfoBeanMapper implements Mapper<FillInfoModel, UpdateUserInfoData> {

    @Override
    public UpdateUserInfoData transform(FillInfoModel bean) {
        UpdateUserInfoData result = new UpdateUserInfoData();
        result.banks = bean.cardBank;
        result.bname = bean.cardMaster;
        result.cardNo = bean.cardNo;
        result.did = bean.id;
        result.sex = bean.sex;
        result.wdistrict = bean.locationId;
        result.birthday = bean.birthday;
        result.uid = UserRepository.getInstance().getUser().getUserBean().info.uid;
        result.uname = bean.name;

        result.picurl = bean.headUrl;
        result.picsurl = bean.headsUrl;
        result.cardUrl = bean.cardUrl;
        result.didurl = bean.idUrl + "," + bean.idBackUrl;

        return result;
    }

}
