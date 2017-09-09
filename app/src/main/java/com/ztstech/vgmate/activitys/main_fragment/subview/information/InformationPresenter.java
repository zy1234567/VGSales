package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.model.information.InformationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyuan on 2017/8/11.
 */

public class InformationPresenter extends PresenterImpl<InformationContract.View> implements
        InformationContract.Presenter{

    /**当前页数*/
    private int currentPage = 1;

    public InformationPresenter(InformationContract.View view) {
        super(view);
    }


    @Override
    public void loadListData() {
        List<InformationModel> models = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            InformationModel model = new InformationModel();
            model.title = "titleasasa" + i;
            model.date = "4月1日";
            model.comment = 12;
            model.picurl = "";
            models.add(model);
        }
        mView.setListData(models);
    }

    @Override
    public void appendData() {

    }


    /**
     * 根据页数加载数据
     * @param page
     */
    private void loadDataWithPage(int page) {

    }
}
