package com.ztstech.appdomain.constants;

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


    /**待确认*/
    String ORG_STATUS_UN_CONFIRM = "02";
    /**已确定*/
    String ORG_STATUS_LOCATED = "00";
    /**网页端*/
    String ORG_STATUS_WEB = "04";
    /**待审批*/
    String ORG_STATUS_UN_APPROVE = "03";


    /**
     * 用户身份通过
     */
    String USER_ID_PASS = "00";
    /**
     * 用户身份审核中
     */
    String USER_ID_CHECKING = "01";
    /**
     * 用户身份拒绝
     */
    String USER_ID_REFUSE = "02";
    /**
     * 用户身份冻结
     */
    String USER_ID_FREEZE = "03";
    /**
     * 用户身份将要审核
     */
    String USER_ID_WILL_CHECK = "04";


    /**
     * 分享类型，纯文字
     */
    String SHARE_TYPE_TEXT = "01";
    /**
     * 分享类型，纯图片s
     */
    String SHARE_TYPE_IMG = "02";
    /**
     * 分享类型，文字加图片
     */
    String SHARE_TYPE_TEXT_IMG = "03";
    /**
     * 分享类型，纯网址
     */
    String SHARE_TYPE_URL = "04";
    /**
     * 文字加网址
     */
    String SHARE_TYPE_TEXT_URL = "05";

    /**
     * 管理员等级
     */
    int LEV_ADMIN = 1;

}
