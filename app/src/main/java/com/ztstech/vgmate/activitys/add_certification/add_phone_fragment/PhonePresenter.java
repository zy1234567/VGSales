package com.ztstech.vgmate.activitys.add_certification.add_phone_fragment;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.add_certification.add_home_fragment.HomeContract;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhonePresenter  extends PresenterImpl<PhoneContract.View>
        implements PhoneContract.Presenter {

    public PhonePresenter(PhoneContract.View view) {
        super(view);
    }

    @Override
    public void commite() {

    }
}
