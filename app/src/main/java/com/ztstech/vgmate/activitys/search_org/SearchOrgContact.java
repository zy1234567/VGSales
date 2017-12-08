package com.ztstech.vgmate.activitys.search_org;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.SearchOrgListBean;

/**
 * Created by smm on 2017/12/8.
 */

public interface SearchOrgContact {

   interface View extends BaseView{
       void setListData(SearchOrgListBean.ListBean listData);
       void showError(String msg);
   }

   interface Presenter extends BasePresenter<View>{
       void LoadDataByKeword(String keyword);
       void appendDada(String keyword);
   }

}
