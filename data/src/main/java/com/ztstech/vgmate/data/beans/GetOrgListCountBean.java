package com.ztstech.vgmate.data.beans;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public class GetOrgListCountBean extends BaseRespBean {


    public Info info;


    public static class Info {
        /**
         * 待确认
         */
        public int waitConfirmcount;

        /**
         * 待认领
         */
        public int waitClaimcount;

        /**
         * 已认领
         */
        public int alreadayClaimcount;

        /**
         * 网页端
         */
        public int webcount;
    }
}
