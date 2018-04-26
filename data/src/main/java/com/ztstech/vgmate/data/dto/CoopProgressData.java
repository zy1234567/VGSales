package com.ztstech.vgmate.data.dto;

import com.ztstech.vgmate.data.beans.BaseRespBean;

/**
 * Created by dongdong on 2018/4/26.
 */

public class CoopProgressData extends BaseRespBean {
    /**
     * map : {"teamwork":"01","poster":"00","twstatus":"01","picture":"01"}
     * status : 0
     */

    public MapBean map;

    public static class MapBean {
        /**
         * teamwork : 01
         * poster : 00
         * twstatus : 01
         * picture : 01
         */

        public String teamwork;
        public String poster;
        public String twstatus;
        public String picture;
    }
}
