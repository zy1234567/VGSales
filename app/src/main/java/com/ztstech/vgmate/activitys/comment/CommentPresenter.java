package com.ztstech.vgmate.activitys.comment;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.data.user_case.GetCommentList;
import com.ztstech.vgmate.utils.PresenterSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public class CommentPresenter extends PresenterImpl<CommentContract.View> implements
        CommentContract.Presenter {

    private int currentPage;
    private int totalPage;

    private List<CommentBean.ListBean> listBeanList = new ArrayList<>();

    public CommentPresenter(CommentContract.View view) {
        super(view);
    }


    @Override
    public void getCommentList(String newsid) {
        currentPage = 1;
        requestData(newsid);
    }

    @Override
    public void appendCommentList(String newsid) {
        if (currentPage == totalPage) {
            mView.onAppendFinish(listBeanList, null);
        }else {
            currentPage++;
            requestData(newsid);
        }
    }

    private void requestData(String newsid) {
        new PresenterSubscriber<CommentBean>() {

            @Override
            public void onNext(CommentBean commentBean) {
                if (commentBean.isSucceed()) {
                    currentPage = commentBean.pager.currentPage;
                    totalPage = commentBean.pager.totalPages;

                    if (currentPage == 1) {
                        //刷新
                        listBeanList.clear();
                        listBeanList.addAll(commentBean.list);
                        mView.onLoadFinish(listBeanList, null);
                    }else {
                        listBeanList.addAll(commentBean.list);
                        mView.onLoadFinish(listBeanList, null);
                    }

                }else {
                    //如果失败
                    if (currentPage == 1) {
                        mView.onLoadFinish(listBeanList, commentBean.getErrmsg());
                    }else {
                        mView.onAppendFinish(listBeanList, commentBean.getErrmsg());
                    }
                }

            }
        }.run(new GetCommentList(newsid, currentPage).run());
    }
}
