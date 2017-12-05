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
         */

        public int confirmNum;
        public int claimNum;
        public int webNum;
        public int auditNum;
    }
}
