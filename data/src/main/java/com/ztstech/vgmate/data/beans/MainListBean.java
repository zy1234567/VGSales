package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/9.
 */

public class MainListBean extends BaseRespBean{


    /**
     * pager : {"totalRows":16,"pageSize":10,"currentPage":1,"totalPages":2,"startRow":0}
     * status : 0
     * list : [{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"1","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"10","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"11","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"12","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"13","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"14","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"15","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"16","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"17","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"},{"createtime":"2017-08-31 13:58:10","title":"销售技巧分享","evacnt":0,"nid":"18","favflg":"00","praflg":"00","url":"http://static.verygrow.com/bucea/html/20170823/518357.html"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 16
         * pageSize : 10
         * currentPage : 1
         * totalPages : 2
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
         * createtime : 2017-08-31 13:58:10
         * title : 销售技巧分享
         * evacnt : 0
         * nid : 1
         * favflg : 00
         * praflg : 00
         * url : http://static.verygrow.com/bucea/html/20170823/518357.html
         */

        public String createtime;
        public String title;
        public int evacnt;
        public String nid;
        public String favflg;
        public String praflg;
        public String url;
        public String picurl;
        public String summary;
        public String updatetime;
    }
}
