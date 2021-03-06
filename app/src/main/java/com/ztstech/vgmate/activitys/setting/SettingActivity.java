package com.ztstech.vgmate.activitys.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.login.LoginActivity;
import com.ztstech.vgmate.activitys.setting.change_phone.ChangePhoneDialog;
import com.ztstech.vgmate.activitys.setting.send_code.ChangePhoneSendCodeDialog;
import com.ztstech.vgmate.activitys.user_info.edit_info.EditInfoActivity;
import com.ztstech.vgmate.data.events.LogoutEvent;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 设置界面
 */
public class SettingActivity extends MVPActivity<SettingContract.Presenter> implements
        SettingContract.View, View.OnClickListener {

    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @BindView(R.id.rl_id)
    View rlId;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected SettingContract.Presenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        tvLogout.setOnClickListener(this);
        rlId.setOnClickListener(this);
        tvVersion.setText(CommonUtil.getVersion());
        tvPhone.setText(mPresenter.getPhone());

        tvPhone.setOnClickListener(this);
        String status = UserRepository.getInstance().getUser().getUserBean()
                .info.status;
        if (Constants.USER_ID_CHECKING.equals(status) || Constants.USER_ID_WILL_CHECK.equals(status)) {
            tvStatus.setText("身份审核中");
        }else if (Constants.USER_ID_REFUSE.equals(status)){
            tvStatus.setText("审核被拒绝");
            tvStatus.setTextColor(getResources().getColor(R.color.weilai_color_109));
        }else {
            tvStatus.setText("审核通过");
        }

    }

    @Override
    public void onLogoutFinish(String errorMessage) {
        if (errorMessage != null) {
            ToastUtil.toastCenter(this, "登出失败：" + errorMessage);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            EventBus.getDefault().post(new LogoutEvent());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvLogout) {
            mPresenter.logout();
        } else if (view == rlId) {
            Intent intent = new Intent(this, EditInfoActivity.class);
            intent.putExtra(EditInfoActivity.SHOW_TYPE,EditInfoActivity.FROM_EDIT_SELF);
            startActivity(intent);
        } else if (view == tvPhone) {
            new IOSStyleDialog(this, "您要修改手机号吗？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    showChangePhoneSendCodeDialog();
                }
            }).show();
        }
    }


    /**
     * 显示更改手机发送验证码对话框
     */
    private void showChangePhoneSendCodeDialog() {
        ChangePhoneSendCodeDialog dialog = new ChangePhoneSendCodeDialog(this,
                mPresenter.getPhone());
        dialog.setCallback(new ChangePhoneSendCodeDialog.ChangePhoneCallback() {
            @Override
            public void onCheckSucceed(String code) {
                showChangePhoneDialog(mPresenter.getPhone());
            }
        });
        dialog.show();
    }

    /**
     * 显示更改手机号
     *
     * @param oldPhone
     */
    private void showChangePhoneDialog(final String oldPhone) {
        ChangePhoneDialog dialog = new ChangePhoneDialog(this, oldPhone);
        dialog.setListener(new ChangePhoneDialog.OnChangePhoneListener() {
            @Override
            public void onSucceed(String phone) {
                tvPhone.setText(oldPhone);
            }
        });
        dialog.show();
    }

}
