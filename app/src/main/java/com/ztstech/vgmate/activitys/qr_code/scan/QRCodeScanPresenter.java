package com.ztstech.vgmate.activitys.qr_code.scan;

import com.google.gson.Gson;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.user_case.CheckLoginWebUUID;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.utils.LogUtils;
import com.ztstech.vgmate.utils.AESUtil;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhiyuan on 2017/11/2.
 */

public class QRCodeScanPresenter extends PresenterImpl<QRCodeScanContract.View> implements
        QRCodeScanContract.Presenter {

    public QRCodeScanPresenter(QRCodeScanContract.View view) {
        super(view);
    }

    @Override
    public void checkUUID(final String uuid) {
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            protected void childNext(BaseRespBean baseRespBean) {
                mView.checkUUIDFinish(splitUuid(uuid), baseRespBean.getErrmsg());
            }
        }.run(new CheckLoginWebUUID(getRequestParams(uuid)).run());
    }

    /**
     * 截取uuid
     * @param uuid //传入的是这样的格式 需要截取uuid  http://www.verygrow.com/code/phonescan?uuid=4ae0a850027d4ce592747cac1eabcc6d
     *
     * @return
     */
    private String splitUuid(String uuid){
        String[] array = uuid.split("uuid=");
        String mUuid = "";
        if (array != null && array.length >1){
            mUuid = array[1];
        }
        return mUuid;
    }

    /**
     * 拼接、加密上传到服务器的参数
     * phone，authid，uuid,uid拼接成json加密后以参数info传过去
     * @param uuid
     * @return
     */
    private String getRequestParams(String uuid){
        Map map = new HashMap();
        map.put("phone", UserRepository.getInstance().getUser().getUserBean().info.phone);
        map.put("authId",UserRepository.getInstance().getAuthId());
        map.put("uuid",splitUuid(uuid));
        map.put("uid",UserRepository.getInstance().getUser().getUserBean().info.uid);
        String info = "";
        try {
            info = AESUtil.encrypt(new Gson().toJson(map), Constants.AES_KEY);
        } catch (Exception e) {
            LogUtils.log("info加密错误!");
            e.printStackTrace();
        }
        return info;
    }
}

