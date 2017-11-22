package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by smm on 2017/11/22.
 */

public class AnwserListBean extends BaseRespBean {


    /**
     * pager : {"totalRows":1,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"content":"你好","picsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","uname":"王晨曦","picurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","likedCnt":0,"ansCreatetime":"2017-11-22 20:18","ansPublishUid":"0a8822da7cb84ebe9ece458dc909a6b1","likeStatus":"00","ansid":"795eecb269aa4e44abdef75b9a1634cb"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 1
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
         * content : 你好
         * picsurl : http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg
         * uname : 王晨曦
         * picurl : http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg
         * likedCnt : 0
         * ansCreatetime : 2017-11-22 20:18
         * ansPublishUid : 0a8822da7cb84ebe9ece458dc909a6b1
         * likeStatus : 00
         * ansid : 795eecb269aa4e44abdef75b9a1634cb
         */

        public String content;
        public String picsurl;
        public String uname;
        public String picurl;
        public int likedCnt;
        public String ansCreatetime;
        public String ansPublishUid;
        public String likeStatus;
        public String ansid;
    }
}
