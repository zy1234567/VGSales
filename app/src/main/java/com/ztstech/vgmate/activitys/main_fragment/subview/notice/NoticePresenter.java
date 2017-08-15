package com.ztstech.vgmate.activitys.main_fragment.subview.notice;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.model.notice.NoticeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/15.
 */

public class NoticePresenter extends PresenterImpl<NoticeContract.View> implements
        NoticeContract.Presenter {

    public NoticePresenter(NoticeContract.View view) {
        super(view);
    }

    @Override
    public void loadData() {
        List<NoticeModel> result = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            result.add(new NoticeModel());
        }
        mView.setData(result);
    }
}
