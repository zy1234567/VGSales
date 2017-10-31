package com.ztstech.vgmate.activitys.sell_mate_list;

import android.webkit.WebView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.constants.Constants;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.appdomain.repository.UserRepository;

import butterknife.BindView;

/**
 * 销售伙伴列表
 */
public class SellMateListActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sell_mate_list;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(NetConstants.BASE_URL + Constants.MATE_LIST_URL + "?type=01&authId=" +
                UserRepository.getInstance().getAuthId());
    }
}
