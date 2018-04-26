package com.ztstech.vgmate.activitys.org_detail_v2;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.activitys.communicate_record.com_list.ComListContact;
import com.ztstech.vgmate.data.beans.GetComRecordBean;
import com.ztstech.vgmate.data.dto.CoopProgressData;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import java.util.List;

/**
 * Created by dongdong on 2018/3/27.
 */

public class OrgDetailV2Contract {

    interface View extends BaseView {
        void setData(UploadProtocolData uploadProtocolData);

        void showError(String errorMessage);

        void setCoopData(CoopProgressData coopProgressData);
//        void setListData(List<GetComRecordBean.ListBean> listData);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 请求协议数据
         * @param orgid
         */
        void loadData(String orgid );
        /**
         * 请求合作进度数据
         */
        void loadcoopData(String rbiid,String orgid);
    }
}
