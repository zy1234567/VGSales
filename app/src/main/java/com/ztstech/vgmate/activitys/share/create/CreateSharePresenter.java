package com.ztstech.vgmate.activitys.share.create;

import android.text.TextUtils;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.user_case.CreateMyShare;
import com.ztstech.appdomain.utils.RetrofitUtils;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import rx.Observable;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class CreateSharePresenter extends PresenterImpl<CreateShareContact.View> implements CreateShareContact.Presenter{


    public CreateSharePresenter(CreateShareContact.View view) {
        super(view);
    }


    @Override
    public void commit() {
        if (mView.getImgaeFiles() != null && mView.getImgaeFiles().length > 0){
            uploadContentPic();
        }else {
            uploadData("","");
        }
    }

    /**
     * 上传内容图片
     */
    private void uploadContentPic() {
        //上传内容图片
        new BasePresenterSubscriber<UploadImageBean>(mView) {

            @Override
            public void childNext(UploadImageBean uploadImageBean) {
                if (uploadImageBean.isSucceed()) {
                    String contentpicurl = uploadImageBean.urls;
                    String contentspicurl = uploadImageBean.suourls;
                    if (!contentspicurl.contains(",")){
                        // 如果是单图 拼接单图的长宽
                        contentspicurl = contentspicurl.concat("!@").
                                concat(String.valueOf(uploadImageBean.width)).concat(":;")
                                .concat(String.valueOf(uploadImageBean.height));
                    }
                    uploadData(contentpicurl,contentspicurl);
                }else {
                    mView.hideLoading(uploadImageBean.getErrmsg());
                }
            }

        }.run(RetrofitUtils.uploadFile(mView.getImgaeFiles()));

    }


    /**
     * 上传数据
     */
    private void uploadData(String contentpicurl,String contentspicurl) {
        //上传数据
        new BasePresenterSubscriber<BaseRespBean>(mView) {

            @Override
            public void childNext(BaseRespBean baseRespBean) {
                if (baseRespBean.isSucceed()){
                    mView.onCommitFinsih();
                }else {
                    mView.showError(baseRespBean.errmsg);
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.hideLoading(null);
            }

        }.run(new CreateMyShare(mView.getShareTitle(),mView.getShareContent(),
                getNtype(),contentspicurl,contentpicurl,mView.getLinkUrl(),mView.getPicdescribe()).run());
    }

    /**
     * 判断判断所要显示的分享类型的ntype
     * @return
     */
    private String getNtype(){
        String linkurl = mView.getLinkUrl();
        if (!TextUtils.isEmpty(linkurl) && (linkurl.startsWith("http://")
                || linkurl.startsWith("https://"))){
            // 如果含有合法连接
            if (mView.getShareContent().isEmpty()){
                // 没有正文
                return Constants.SHARE_ONLY_LINK;
            }else {
                // 有正文
                return Constants.SHARE_WORD_LINK;
            }
        }else {
            if (mView.getImgaeFiles() != null && mView.getImgaeFiles().length > 0){
                if (mView.getShareContent().isEmpty()){
                    // 没有正文
                    return Constants.SHARE_ONLY_IMAGE;
                }else {
                    // 有正文
                    return Constants.SHARE_WORD_IMAGE;
                }
            }else {
                return Constants.SHARE_ONLY_WORD;
            }
        }

    }

}
