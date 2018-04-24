package com.ztstech.vgmate.data.beans;

/**
 * Created by smm on 2017/11/24.
 */

public class OrgFollowNumBean extends BaseRespBean{

    public InfoBean info;

    public static class InfoBean {
        /**
         * confirmNum : 77
         * claimNum : 2
         * webNum : 0
         *  * appointNum 开拓数
         * appointingNum 开拓待处理数
         * introNum 商家介绍数
         * introingNum 商家介绍数待处理数
         * rushNum 机会抢单数
         */

        public int confirmNum;
        public int claimNum;
        public int webNum;
        public int auditNum;

        public int appointNum;
        public int appointingNum;
        public int introNum;
        public int introingNum;
        public int rushNum;
    }
}
