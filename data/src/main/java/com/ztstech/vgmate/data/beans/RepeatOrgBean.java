package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/25.
 */

public class RepeatOrgBean extends BaseRespBean {


    /**
     * pager : {"totalRows":0,"pageSize":10,"currentPage":1,"totalPages":0,"startRow":0}
     * list : []
     */

    public PagerBean pager;
    public List<ListBean> list;



    public static class ListBean {

        public int rbiid;

        public String rbioname;
        public String rbicreatetime;
        public String rbiaddress;
        public String rbidistrict;
        public String rbiotype;
        public String rbiphone;
        public String comefrom;
        public String rbilogosurl;
        /**机构状态 00待认领，01无效机构，02重复机构，03待审核打点，04以认领*/
        public String rbiostatus;
    }
}
