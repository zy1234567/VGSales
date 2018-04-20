package com.ztstech.vgmate.activitys.add_certification;

import com.ztstech.appdomain.user_case.AddVcertification;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddVData;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by Administrator on 2018/4/20.
 */

public class AddVPresenter  extends PresenterImpl<AddVContract.View> implements
        AddVContract.Presenter{

        public AddVPresenter(AddVContract.View view) {
                super(view);
        }

        @Override
        public void commit(AddVData data) {

                mView.showLoading("请稍等");
                new BasePresenterSubscriber<BaseRespBean>(mView) {
                @Override
                public void childNext(BaseRespBean baseRespBean) {
                        mView.hideLoading(null);
                        if (baseRespBean.isSucceed()) {
                                //防止后台在正确情况下返回errmsg
                                mView.onSubmitFinish(null);
                        }else {
                                mView.onSubmitFinish(baseRespBean.getErrmsg());
                        }

                }
                }.run(new AddVcertification(data).run());
        }
}
