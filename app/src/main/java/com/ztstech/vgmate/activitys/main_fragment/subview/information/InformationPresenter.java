package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.repository.MainListRepository;
import com.ztstech.vgmate.model.information.InformationModel;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationPresenter extends PresenterImpl<InformationContract.View> implements
        InformationContract.Presenter{

    /**当前页数*/
    private int currentPage = 1;

    private MainListRepository mainListRepository;

    public InformationPresenter(InformationContract.View view) {
        super(view);
        mainListRepository = MainListRepository.getInstance();
    }


    @Override
    public void loadListData() {

        new PresenterSubscriber<MainListBean>(mView){

            @Override
            public void onNext(MainListBean mainListBean) {
                if (mainListBean.isSucceed()) {
                    mView.setListData(mainListBean);
                }else {
                    mView.showError(mainListBean.getErrmsg());
                }
            }
        }.run(mainListRepository.queryInformation());

    }

    @Override
    public void appendData() {

    }


    /**
     * 根据页数加载数据
     * @param page
     */
    private void loadDataWithPage(int page) {

    }
}
