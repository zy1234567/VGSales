package com.ztstech.vgmate.mapper;

import android.text.TextUtils;

import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.LocationUtils;

/**
 *
 * @author smm
 * @date 2017/11/17
 */

public class MateInfoModelMapper implements Mapper<UnApproveMateBean,FillInfoModel> {
    @Override
    public FillInfoModel transform(UnApproveMateBean bean) {
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
