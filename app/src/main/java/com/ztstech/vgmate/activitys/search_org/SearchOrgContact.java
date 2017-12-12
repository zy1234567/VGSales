package com.ztstech.vgmate.activitys.search_org;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;

import java.util.List;

/**
 * Created by smm on 2017/12/8.
 */

public interface SearchOrgContact {

   interface View extends BaseView{
       void setListData(List<OrgFollowlistBean.ListBean> listData);
       void showError(String msg);
   }

   interface Presenter extends BasePresenter<View>{
       void LoadDataByKeword(String keyword,String district);
       void appendDada(String keyword,String district);
   }

}
