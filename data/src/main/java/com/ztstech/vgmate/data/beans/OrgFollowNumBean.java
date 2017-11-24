package com.ztstech.vgmate.data.beans;

/**
 * Created by smm on 2017/11/24.
 */

public class OrgFollowNumBean extends BaseRespBean{


    /**
     * info : {"confirmNum":27,"claimNum":0,"webNum":0}
     */

    public InfoBean info;

    public static class InfoBean {
        /**
         * confirmNum : 27
         * claimNum : 0
         * webNum : 0
         */

        public String confirmNum;
        public String claimNum;
        public String webNum;
    }
}
