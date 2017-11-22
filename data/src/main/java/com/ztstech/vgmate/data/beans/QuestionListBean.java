package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/16
 */

public class QuestionListBean extends BaseRespBean {


    /**
     * pager : {"totalRows":3,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"delflg":"00","uid":"0a8822da7cb84ebe9ece458dc909a6b1","createtime":"2017-11-21 15:49:22","queid":"1","uname":"王晨曦","supplement":"补充1","descrption":"问题1","answerCnt":1},{"delflg":"00","uid":"0a8822da7cb84ebe9ece458dc909a6b1","createtime":"2017-11-21 15:49:22","queid":"2","uname":"王晨曦","supplement":"补充2","descrption":"问题2","answerCnt":2},{"delflg":"00","uid":"0a8822da7cb84ebe9ece458dc909a6b1","createtime":"2017-11-22 09:38:15","queid":"0f8018a9949341bfb61960291115925f","uname":"王晨曦","supplement":"问题补充","descrption":"问题描述","answerCnt":5}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 3
         * pageSize : 10
         * currentPage : 1
         * totalPages : 1
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
         * delflg : 00
         * uid : 0a8822da7cb84ebe9ece458dc909a6b1
         * createtime : 2017-11-21 15:49:22
         * queid : 1
         * uname : 王晨曦
         * supplement : 补充1
         * descrption : 问题1
         * answerCnt : 1
         */

        public String delflg;
        public String uid;
        public String createtime;
        public String queid;
        public String uname;
        public String supplement;
        public String descrption;
        public int answerCnt;
    }
}
