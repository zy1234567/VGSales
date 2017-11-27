package com.ztstech.vgmate.activitys.share.create;

import android.text.TextUtils;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.activitys.PresenterImpl;

/**
 * Created by smm on 2017/11/27.
 */

public class CreateSharePresenter extends PresenterImpl<CreateShareContact.View> implements CreateShareContact.Presenter{


    public CreateSharePresenter(CreateShareContact.View view) {
        super(view);
    }


    @Override
    public void commit() {

    }

    private void uoloadFiles(){

    }

    /**
     * 判断判断所要显示的分享类型的ntype
     * @return
     */
    private String getNtype(){
        String linkurl = mView.getLinkUrl();
        if (!TextUtils.isEmpty(linkurl) && linkurl.indexOf("http://") != -1 && linkurl.indexOf("https://") != -1){
            // 如果还有合法连接
            if (mView.getShareContent().isEmpty()){
                // 没有正文
                return Constants.SHARE_ONLY_LINK;
            }else {
                // 有正文
                return Constants.SHARE_WORD_LINK;
            }
        }else {
            if (mView.getImgaeFiles() == null || mView.getImgaeFiles().length == 0){
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
