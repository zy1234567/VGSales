package com.ztstech.vgmate.mapper;

import android.text.TextUtils;

import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.SexUtils;

/**
 * Created by zhiyuan on 2017/9/17.
 */

public class FillInfoModelMapper implements Mapper<UserBean, FillInfoModel> {

    @Override
    public FillInfoModel transform(UserBean bean) {
        FillInfoModel model = new FillInfoModel();
        model.birthday = bean.info.birthday;
        model.id = bean.info.did;
        model.sex = bean.info.sex;
        model.cardBank = bean.info.banks;
        model.cardMaster = bean.info.bname;
        model.cardNo = bean.info.cardNo;
        model.cardUrl = bean.info.cardImg;
        model.headUrl = bean.info.picurl;
        model.name = bean.info.uname;
        String didUrls = bean.info.didurl;
        if (!TextUtils.isEmpty(didUrls)) {
            String[] dids = didUrls.split(",");
            if (dids.length == 2) {
                model.idUrl = dids[0];
                model.idBackUrl = dids[1];
            }
        }
        model.locationId = bean.info.wdistrict;
        if (!TextUtils.isEmpty(model.locationId)) {
            model.location = LocationUtils.getLocationNameByCode(model.locationId);
        }
        return model;
    }
}
