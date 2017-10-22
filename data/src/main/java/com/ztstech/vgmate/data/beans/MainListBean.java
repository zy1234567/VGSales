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


    public static class ListBean {
        /**
         * createtime : 2017-08-31 13:58:10
         * title : 销售技巧分享
         * evacnt : 0
         * nid : 1
         * favflg : 00
         * praflg : 00
         */

        public String createtime;
        public String title;
        public int evacnt;
        public String nid;
        public String favflg;
        public String praflg;
        public String picurl;
        public String picsurl;
        public String summary;
        public String updatetime;
        public String type;
        public String contentpicsurl;
        public String contentpicurl;
        public String url;
        public String picdescribe;
        /**资讯类型（01纯文字；02纯图；03字加图；04纯网址；05字加网址）*/
        public String ntype;

    }
}
