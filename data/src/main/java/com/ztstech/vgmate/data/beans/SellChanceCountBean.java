package com.ztstech.vgmate.data.beans;

/**
 * Created by zhiyuan on 2017/10/29.
 * 销售机会顶部数据
 */

public class SellChanceCountBean extends BaseRespBean {
    /**
     * info : {"getnum":1,"sumnum":7,"successnum":1,"waitnum":0}
     */

    public InfoBean info;

    public static class InfoBean {

        /**
         * 新推荐机构数
         */
        public int sumnum;

        /**
         * 抢到的机构数
         */
        public int getnum;

        /**
         * 机构成功数
         */
        public int successnum;

        /**
         * 可抢机构数
         */
        public int waitnum;
    }




}
