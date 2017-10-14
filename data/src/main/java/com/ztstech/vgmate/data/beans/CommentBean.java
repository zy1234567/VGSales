package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/12.
 */

public class CommentBean extends BaseRespBean {


    /**
     * pager : {"totalRows":1,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"uid":"0a8822da7cb84ebe9ece458dc909a6b1","createtime":"2017-09-28 21:55:05","lid":45,"flid":0,"picsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","name":"王晨曦","comment":"呵呵，试试看。"}]
     */

    public PagerBean pager;
    public List<ListBean> list;


    public static class ListBean {

        /**
         * uid : 402881fb5e0dea5e015e0deb71150001
         * createtime : 2017-10-14 10:19:00
         * lid : 57
         * flid : 45
         * picsurl : http://static.verygrow.com/bucea/image/20170911/0_213731125821.jpg
         * touid : 0a8822da7cb84ebe9ece458dc909a6b1
         * name : 暗随流水到天涯
         * touname : 王晨曦
         * topicsurl : http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg
         * lastcomment : 呵呵，试试看。
         * comment : 11111
         */

        public String uid;
        public String createtime;
        public int lid;
        public int flid;
        public String picsurl;
        public String touid;
        public String name;
        public String touname;
        public String topicsurl;
        public String lastcomment;
        public String comment;
    }
}
