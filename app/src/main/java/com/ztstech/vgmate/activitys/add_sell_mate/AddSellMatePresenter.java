package com.ztstech.vgmate.activitys.add_sell_mate;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.AddSellMateData;
import com.ztstech.appdomain.user_case.AddSellMate;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class AddSellMatePresenter extends PresenterImpl<AddSellMateContract.View> implements
        AddSellMateContract.Presenter {

    public AddSellMatePresenter(AddSellMateContract.View view) {
        super(view);
    }

    @Override
    public void submit(AddSellMateData addSellMateData) {
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
        }.run(new AddSellMate(addSellMateData).run());
    }
}
