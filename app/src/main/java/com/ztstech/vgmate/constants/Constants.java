package com.ztstech.vgmate.constants;

/**
 * Created by zhiyuan on 2017/8/29.
 */

public interface Constants {

    /**销售伙伴*/
    String MATE_LIST_URL = "jsp/webh5/salePartner.jsp";

    /**列表数据类型公告*/
    String DATA_TYPE_NOTICE = "01";

    /**列表数据类型资讯*/
    String DATA_TYPE_INFORMATION = "00";


//     00以生效打点(待认领)，01无效打点，02待审核打点(待确定),03以认领机构（即是以生效打点并且有机构id）,04管理端
    /**待确认*/
    String ORG_STATUS_UN_CONFIRM = "02";
    /**已确定*/
    String ORG_STATUS_LOCATED = "00";
    /**网页端*/
    String ORG_STATUS_WEB = "04";
    /**待审批*/
    String ORG_STATUS_UN_APPROVE = "03";

}
