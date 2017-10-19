package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/19.
 * 编辑资料教师列表界面
 */

public class TeacherListBean extends BaseRespBean {


    /**
     * pager : {"totalRows":4,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"position":"Engli","tecphone":"13145678987","name":"Cecececece"},{"position":"Math","tecphone":"13162383108","name":"Zsss"},{"position":"Math","tecphone":"13162384109","name":"Zhangxiaoming"},{"position":"管理员","tecphone":"13691496869","name":"俊鹏宝宝222","logosurl":"http://static.txboss.com/matter/teachers.png"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * position : Engli
         * tecphone : 13145678987
         * name : Cecececece
         * logosurl : http://static.txboss.com/matter/teachers.png
         */

        public String position;
        public String tecphone;
        public String name;
        public String logosurl;
    }
}
