package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/11.
 */

public class SellChanceBean extends BaseRespBean {


    /**
     * pager : {"totalRows":1,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"createtime":"2017-10-20 13:48:01","cstatus":"00","comid":"20180d6236ee4cbaaeb94a6081694c58","createuid":"5ce126e4e78b46d580fbd07bd8047962","marketname":"北京客服一","oname":"opfan","marketuid":"2c907e855e51727c015e51736fb50001","province":"110000","district":"110115","contractphone":"15840350313","city":"110100"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * createtime : 2017-10-20 13:48:01
         * cstatus : 00
         * comid : 20180d6236ee4cbaaeb94a6081694c58
         * createuid : 5ce126e4e78b46d580fbd07bd8047962
         * marketname : 北京客服一
         * oname : opfan
         * marketuid : 2c907e855e51727c015e51736fb50001
         * province : 110000
         * district : 110115
         * contractphone : 15840350313
         * city : 110100
         */

        public String createtime;
        public String cstatus;
        public String comid;
        public String createuid;
        public String marketname;
        public String oname;
        public String marketuid;
        public String province;
        public String district;
        public String contractphone;
        public String city;
        public String picurl;
        public String contractname;
        public String salename;

    }
}
