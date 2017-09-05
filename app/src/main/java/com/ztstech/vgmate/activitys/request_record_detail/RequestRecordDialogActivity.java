package com.ztstech.vgmate.activitys.request_record_detail;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * 机构申请记录对话框
 */
public class RequestRecordDialogActivity extends MVPActivity<RequestRecordDialogContract.Presenter> implements
        RequestRecordDialogContract.View {



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_request_record_dialog;
    }

    @Override
    protected RequestRecordDialogContract.Presenter initPresenter() {
        return new RequestRecordDialogPresenter(this);
    }
}
