package com.ztstech.vgmate.mapper;

import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.LocationUtils;

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
        result.uid = UserRepository.getInstance().getUser().info.uid;
        result.uname = bean.name;

        result.picurl = bean.headUrl;
        result.cardUrl = bean.cardUrl;
        result.didurl = bean.idUrl + "," + bean.idBackUrl;

        return result;
    }

}
