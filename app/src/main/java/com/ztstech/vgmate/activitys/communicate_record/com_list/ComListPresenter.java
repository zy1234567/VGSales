package com.ztstech.vgmate.activitys.communicate_record.com_list;

import com.ztstech.appdomain.user_case.GetComRecord;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.utils.BasePresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smm on 2018/1/11.
 */

public class ComListPresenter extends PresenterImpl<ComListContact.View> implements ComListContact.Presenter {
    private int currentpage = 1,totalpage;
    private List<GetComRecordBean.ListBean> listBean = new ArrayList<>();
    public ComListPresenter(ComListContact.View view) {
        super(view);
    }

    @Override
    public void loadData(String rbiid) {
        currentpage = 1;
        requestData(rbiid);
    }

    @Override
    public void appendData(String rbiid) {
        if (currentpage == totalpage){
            mView.setData(listBean);
        }else {
            currentpage ++;
            requestData(rbiid);
        }
    }
    private void requestData(String rbiid){
        new BasePresenterSubscriber<GetComRecordBean>(mView,false){

            @Override
            protected void childNext(GetComRecordBean getComRecordBean) {
                if (getComRecordBean.isSucceed()) {
                    currentpage = getComRecordBean.pager.currentPage;
                    totalpage = getComRecordBean.pager.totalPages;

                    if (currentpage == 1) {
                        //刷新
                        listBean.clear();
                    }
                    listBean.addAll(getComRecordBean.list);
                    mView.setData(listBean);
                }else {
                    //如果失败
                    mView.showError(getComRecordBean.getErrmsg());
                }
            }
        }.run(new GetComRecord(rbiid,currentpage).run());
    }
}
