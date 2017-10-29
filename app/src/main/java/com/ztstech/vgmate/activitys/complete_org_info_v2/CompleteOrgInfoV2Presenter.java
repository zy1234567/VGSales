package com.ztstech.vgmate.activitys.complete_org_info_v2;

import android.text.TextUtils;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.OrgInfoBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.user_case.EditOrgInfo;
import com.ztstech.vgmate.data.user_case.GetOrgInfo;
import com.ztstech.vgmate.data.utils.RetrofitUtils;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.io.File;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/9.
 */

public class CompleteOrgInfoV2Presenter extends PresenterImpl<CompleteOrgInfoV2Contract.View>
        implements CompleteOrgInfoV2Contract.Presenter {

    public CompleteOrgInfoV2Presenter(CompleteOrgInfoV2Contract.View view) {
        super(view);
    }

    @Override
    public void loadOrgInfo(int rbiid) {
        new BasePresenterSubscriber<OrgInfoBean>(mView){

            @Override
            public void childNext(OrgInfoBean orgInfoBean) {
                if (orgInfoBean.isSucceed()) {
                    mView.showOrgInfo(orgInfoBean.info);
                }else {
                    mView.onLoadOrgInfoError(orgInfoBean.getErrmsg());
                }
            }

        }.run(new GetOrgInfo(rbiid).run());
    }

    @Override
    public void editOrgInfo(final int rbiid, final OrgInfoBean.InfoBean bean) {
        File[] logoFiles = null;
        if (!TextUtils.isEmpty(bean.localLogoPath)) {
            //如果更改机构logo
            logoFiles = new File[] {new File(bean.localLogoPath)};
        }
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            protected void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    if (!TextUtils.isEmpty(uploadImageBean.urls)) {
                        bean.logourl = uploadImageBean.urls;
                        bean.logosurl = uploadImageBean.suourls;
                        bean.logo = uploadImageBean.urls;
                    }

                    File[] adsFiles = null;
                    if (!TextUtils.isEmpty(bean.localWallPath)) {
                        String[] paths = bean.localWallPath.split(",");
                        adsFiles = new File[paths.length];
                        for (int i = 0; i < paths.length; i++) {
                            adsFiles[i] = new File(paths[i]);
                        }
                    }

                    new BasePresenterSubscriber<UploadImageBean>(mView) {

                        @Override
                        protected void childNext(UploadImageBean uploadImageBean) {
                            if (uploadImageBean.isSucceed()) {
                                if (!TextUtils.isEmpty(uploadImageBean.urls)) {
                                    bean.advertisingwall = uploadImageBean.urls;
                                    bean.advertisingwallsurl = uploadImageBean.suourls;
                                }

                                new BasePresenterSubscriber<BaseRespBean>(mView) {

                                    @Override
                                    protected void childNext(BaseRespBean baseRespBean) {
                                        mView.editOrgInfoFinish(baseRespBean.getErrmsg());
                                    }

                                }.run(new EditOrgInfo(String.valueOf(rbiid), bean.oname,
                                        bean.advertisingwall, bean.advertisingwallsurl, bean.walldescribe,
                                        bean.introduction, bean.courseintroduction, bean.tollintroduction, bean.tag,
                                        bean.phone, bean.manager, bean.managerphone, bean.logourl, bean.logosurl).run());

                            }else {
                                mView.editOrgInfoFinish("上传图片失败：" + uploadImageBean.errmsg);
                            }
                        }

                    }.run(RetrofitUtils.uploadIfExist(adsFiles));

                }else {
                    mView.editOrgInfoFinish("上传图片失败：" + uploadImageBean.errmsg);
                }
            }
        }.run(RetrofitUtils.uploadIfExist(logoFiles));




    }


}
