package com.ztstech.vgmate.mapper;

import com.ztstech.vgmate.data.dto.UpdateUserInfoData;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;

/**
 * Created by zhiyuan on 2017/8/23.
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

//        result.picurl = FileUtils.getBytes(bean.headerFile);
//        result.didurl = new String(FileUtils.getBytes(bean.idFile)) + "," + new String(FileUtils.getBytes(bean.idBackFile));
//        result.cardUrl = FileUtils.getBytes(bean.cardFile);

        return result;
    }

}
