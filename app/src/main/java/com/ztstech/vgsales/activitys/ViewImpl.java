package com.ztstech.vgsales.activitys;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class ViewImpl<P extends BasePresenter> {

    private P presenter;

    private Context context;
    private KProgressHUD hud;

    public ViewImpl(Context context) {
        this.context = context;
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    public void showLoading(String message) {
        hud.setLabel(message);
        hud.show();
    }

    public void hideLoading(@Nullable String errorMessage) {
        if (hud.isShowing()) {
            hud.dismiss();
        }
        if (!TextUtils.isEmpty(errorMessage)) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }


}
