package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/13
 */

public class WaitApproveMateListBean extends BaseRespBean {


    /**
     * pager : {"totalRows":108,"pageSize":10,"currentPage":1,"totalPages":11,"startRow":0}
     * list : [{"uid":"ff8080815fbeef18015fbfe4175b0019","phone":"13405122050","responsible":"祁燕","salelevel":"1,15,1","uname":"高杰","salelev":3,"fname":"祁燕"},{"uid":"ff8080815fb9f74d015fbe78cb3700fb","phone":"18362923828","responsible":"李娜","salelevel":"1,21,23","uname":"张颖","salelev":3,"age":24,"fname":"李娜"},{"uid":"ff8080815fb9f74d015fbe77814a00f9","phone":"15951839762","responsible":"李娜","salelevel":"1,21,22","uname":"汤蒙蒙","salelev":3,"age":25,"fname":"李娜"},{"uid":"ff8080815fb9f74d015fbe762c3e00f6","phone":"18351802709","responsible":"李娜","salelevel":"1,21,21","uname":"杨娜","salelev":3,"fname":"李娜"},{"uid":"ff8080815fb9f74d015fbe74776100f4","phone":"13813089918","responsible":"李娜","salelevel":"1,21,20","uname":"刘燕","salelev":3,"age":35,"fname":"李娜"},{"uid":"ff8080815fb94a3a015fb95e6d47001b","phone":"18519382278","responsible":"周峰","salelevel":"1,20,3","uname":"张育琦","salelev":3,"age":26,"fname":"周峰"},{"uid":"ff8080815fb9451d015fb946bfef0003","phone":"15894629020","responsible":"黄家豪","salelevel":"1,3,4","uname":"李新泉","salelev":3,"age":48,"fname":"黄家豪"},{"uid":"ff8080815fb84926015fb9213b050099","phone":"18140670807","responsible":"王晨曦","salelevel":"1,35","uname":"陈兰","salelev":2,"age":33,"fname":"王晨曦"},{"uid":"ff8080815fb84926015fb8ec21cb0075","phone":"13699380517","responsible":"黄家豪","salelevel":"1,3,3","uname":"陈亮","salelev":3,"age":0,"fname":"黄家豪"},{"uid":"ff8080815fb84926015fb8e6d3020072","phone":"18999118285","responsible":"黄家豪","salelevel":"1,3,2","uname":"夏菱江","salelev":3,"age":52,"fname":"黄家豪"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 108
         * pageSize : 10
         * currentPage : 1
         * totalPages : 11
         * startRow : 0
         */

        public int totalRows;
        public int pageSize;
        public int currentPage;
        public int totalPages;
        public int startRow;
    }

    public static class ListBean {
        /**
         * uid : ff8080815fbeef18015fbfe4175b0019
         * phone : 13405122050
         * responsible : 祁燕
         * salelevel : 1,15,1
         * uname : 高杰
         * salelev : 3
         * fname : 祁燕
         * age : 24
         */

        public String uid;
        public String phone;
        public String responsible;
        public String salelevel;
        public String uname;
        public int salelev;
        public String fname;
        public int age;
        public String picurl;
        public String sex;
        public String lastuptime;
    }
}
