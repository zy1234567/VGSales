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
    /**
     * 1级销售
     */
    int LEV_1 = 2;

    /**
     * AES加密用的key
     */
    String AES_KEY = "v7l27ffb9xamti4igqa308ju";

    /**
     * 分享的ntype
     */
    /** 只有文字 */
    String SHARE_ONLY_WORD = "01";
    /** 只有图片 */
    String SHARE_ONLY_IMAGE = "02";
    /** 文字加图片 */
    String SHARE_WORD_IMAGE = "03";
    /** 只有链接 */
    String SHARE_ONLY_LINK = "04";
    /** 连接加文字 */
    String SHARE_WORD_LINK = "05";
    /**审核状态00通过，01拒绝，02待审核**/
    String PASS_TYPE = "00";
    String REFUSE_TYPE = "01";
    String CHECK_PEND_TYPE = "02";
    //是否可以添加销售 01 可以 00 不可以
    String ADDTYPE_YES = "01";
    String ADDTYPE_NO = "00";

    //机构可抢机会 是否锁定 00：未锁定，01：已锁定
    String LOCK_YES = "01";
    String LOCK_NO = "00";

    // cstatus：机会状态,11:灰点未认证,14:机构认领中,15:已认领
    String CSTATUS_GRAY_UNVERIFIED = "11";
    String CSTATUS_ORG_CLAIM_ING = "14";
    String CSTATUS_ALREADY_CLAIM = "15";

    //nowchancetype：当前机会类型，03：web机构认领，04：app机构认领
    String NOW_CHANCE_TYPE_WEB_CLAIM = "03";
    String NOW_CHANCE_TYPE_APP_CLAIM = "04";

    /**
     * chancetype：机会来源,01:web机构注册,02:app机构注册,03:web机构认领,04:app机构认领,05:web机构登记,
     * 06:app机构登记,07:web路人登记,08:app路人登记,09:web销售登记,10:app销售登记,11:app地图注册
     */
    String CHANCE_TYPE_WEB_ORG_REGISTER = "01";
    String CHANCE_TYPE_APP_ORG_REGISTER = "02";
    String CHANCE_TYPE_WEB_ORG_CLAIM = "03";
    String CHANCE_TYPE_APP_ORG_CLAIM = "04";
    String CHANCE_TYPE_WEB_ORG_CHECK_IN = "05";
    String CHANCE_TYPE_APP_ORG_CHECK_IN = "06";
    String CHANCE_TYPE_WEB_PASSER_CHECK_IN = "07";
    String CHANCE_TYPE_APP_PASSER_CHECK_IN = "08";
    String CHANCE_TYPE_WEB_SALE_CHECK_IN = "09";
    String CHANCE_TYPE_APP_SALE_CHECK_IN = "10";
    String CHANCE_TYPE_APP_MAP_REGISTER = "11";

    /**
     * 担任职位，05机构一般管理人员，09机构法人/老板/店长,其他 02
     */
    String ORG_ADMIN = "05";
    String ORG_BOSS = "09";
    String ELSE_NOMAL = "02";
    //机构认领 01 机构登记 02 路人登记 03
    int ORG_CALIM = 01;
    int ORG_REGISTER = 02;
    int NORMAL_REGISTER = 03;
    //沟通方式 00:电话沟通,01:上门拜访,02:远程审核
    String PHONE_CATION_TYPE = "00";
    String HOME_VISI_TYPE = "01";
    String LONGR_ANGE_AUDIT = "02";
    String LONG_RANGE = "03";

    // 01:定位认证,02:加V认证
    String IDENTIFICATION_TYPE_REGISTER_ADD_V = "02";
    String IDENTIFICATION_TYPE_REGISTER_LOCATION = "01";
    //审核通过
    String PASS_ORG = "00";
    //沟通机会类型00机构沟通记录，01机会沟通记录
    String COMMUNICATION_TYPE_ORG = "00";
    String COMMUNICATION_TYPE_CHANCE = "01";

    //已上传 00 未上传 01
    String PASS = "00";
    String NO_PASS = "01";
}
