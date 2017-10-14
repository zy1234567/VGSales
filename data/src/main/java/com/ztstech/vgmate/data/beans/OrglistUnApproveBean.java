package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/14.
 * 机构列表待审批
 */

public class OrglistUnApproveBean extends BaseRespBean {


    /**
     * pager : {"totalRows":1,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"createtime":"2017-09-29 10:37:21","rbiid":279,"rbisaleuid":"402881fb5e0dea5e015e0deb71150001","phone":"13801264231","oname":"蔚来优学教育集团","uname":"暗随流水到天涯","power":"01","rbilogosurl":"http://static.txboss.com/matter/15abroad.png","rbiaddress":"北京市东城区龙潭湖路8号","rbiotype":"1404","comefrom":"自行注册"}]
     */

    public PagerBean pager;
    public List<ListBean> list;


    public static class ListBean {
        /**
         * createtime : 2017-09-29 10:37:21
         * rbiid : 279
         * rbisaleuid : 402881fb5e0dea5e015e0deb71150001
         * phone : 13801264231
         * oname : 蔚来优学教育集团
         * uname : 暗随流水到天涯
         * power : 01
         * rbilogosurl : http://static.txboss.com/matter/15abroad.png
         * rbiaddress : 北京市东城区龙潭湖路8号
         * rbiotype : 1404
         * comefrom : 自行注册
         */

        public String createtime;
        public int rbiid;
        public String rbisaleuid;
        public String phone;
        public String oname;
        public String uname;
        public String power;
        public String rbilogosurl;
        public String rbiaddress;
        public String rbiotype;
        public String comefrom;
    }
}
