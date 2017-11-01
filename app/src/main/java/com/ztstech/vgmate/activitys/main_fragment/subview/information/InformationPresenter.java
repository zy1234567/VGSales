package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.appdomain.repository.MainListRepository;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationPresenter extends PresenterImpl<InformationContract.View> implements
        InformationContract.Presenter{

    /**当前页数*/
    private int currentPage = 1;

    private int maxPage = 1;

    private MainListRepository mainListRepository;

    /**列表数据*/
    private List<MainListBean.ListBean> listData = new ArrayList<>();

    public InformationPresenter(InformationContract.View view) {
        super(view);
        mainListRepository = MainListRepository.getInstance();
    }


    @Override
    public void loadListData() {
        loadDataWithPage(1);
    }

    @Override
    public void appendData() {
        if (currentPage < maxPage) {
            loadDataWithPage(currentPage + 1);
        }else {
            mView.setListData(listData);
        }
    }


    /**
     * 根据页数加载数据
     * @param page
     */
    private void loadDataWithPage(int page) {
        new BasePresenterSubscriber<MainListBean>(mView){

            @Override
            public void childNext(MainListBean mainListBean) {
                if (mainListBean.isSucceed()) {
                    maxPage = mainListBean.pager.totalPages;
                    currentPage = mainListBean.pager.currentPage;

                    if (currentPage == 1) {
                        //如果当前是第一页，清除数据
                        listData.clear();
                    }

                    listData.addAll(mainListBean.list);
                    mView.setListData(listData);

                    mView.showNomoreData(currentPage == maxPage);
                }else {
                    mView.showError(mainListBean.getErrmsg());
                }
            }

            @Override
            protected void childError(Throwable e) {
                mView.showError("网络访问出错".concat(e.getLocalizedMessage()));
            }
        }.run(mainListRepository.queryInformation(page));
    }
}
