package com.ztstech.vgmate.activitys.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.question.adapter.QuestionViewPgerAdapter;
import com.ztstech.vgmate.activitys.question.create_question.CreateQuestionActivity;
import com.ztstech.vgmate.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 * @author smm
 * @date 2017/11/16
 */

public class QuestionFragment extends BaseFragment{

    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_quiz)
    ImageView imgQuiz;

    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        tablayout.setupWithViewPager(viewpager);
        viewpager.setAdapter(new QuestionViewPgerAdapter(getChildFragmentManager()));
        imgQuizvisiorgone();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_question;
    }
    //发布按钮（审核中/未提交审核状态 01/04 隐藏）
    public void imgQuizvisiorgone(){
        if (TextUtils.equals(Constants.USER_ID_CHECKING,
                UserRepository.getInstance().getUser().getUserBean().info.status) ||
                TextUtils.equals(Constants.USER_ID_WILL_CHECK,
                        UserRepository.getInstance().getUser().getUserBean().info.status)){
            imgQuiz.setVisibility(View.GONE);
        }else{
            imgQuiz.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.rl_search,R.id.img_quiz})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.img_quiz:
                Intent intent = new Intent(getActivity(),CreateQuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_search:
                Intent intent1 = new Intent(getActivity(),SearchQuestActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

}
