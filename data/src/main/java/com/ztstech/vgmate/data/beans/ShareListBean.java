package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class ShareListBean {


    /**
     * pager : {"totalRows":1,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * status : 0
     * ntype : null
     * list : [{"uid":"0a8822da7cb84ebe9ece458dc909a6b1","content":"内容1","sid":"1","title":"题目1","delflg":"00","uname":"王晨曦","likeList":[],"userpicsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","userpicurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","commentList":[{"uid":"2c907e825e41abc4015e41af9d900002","content":"不错+1","comid":"1","delflg":"00","comuid":"27677c94e420451c914c7fe2c5d84fba","type":"03","cid":"asdad122312e"},{"uid":"2c907e825e17044a015e1723fc2d0004","content":"不错","comid":"1","delflg":"00","comuid":"27677c94e420451c914c7fe2c5d84fba","type":"03","cid":"12312312312312"}]}]
     */

    public PagerBean pager;
    public int status;
    public Object ntype;
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
         * uid : 0a8822da7cb84ebe9ece458dc909a6b1
         * content : 内容1
         * sid : 1
         * title : 题目1
         * delflg : 00
         * uname : 王晨曦
         * likeList : []
         * userpicsurl : http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg
         * userpicurl : http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg
         * commentList : [{"uid":"2c907e825e41abc4015e41af9d900002","content":"不错+1","comid":"1","delflg":"00","comuid":"27677c94e420451c914c7fe2c5d84fba","type":"03","cid":"asdad122312e"},{"uid":"2c907e825e17044a015e1723fc2d0004","content":"不错","comid":"1","delflg":"00","comuid":"27677c94e420451c914c7fe2c5d84fba","type":"03","cid":"12312312312312"}]
         */

        public String uid;
        public String content;
        public String sid;
        public String title;
        public String delflg;
        public String uname;
        public String userpicsurl;
        public String userpicurl;
        public List<?> likeList;
        public List<CommentListBean> commentList;

        public static class CommentListBean {
            /**
             * uid : 2c907e825e41abc4015e41af9d900002
             * content : 不错+1
             * comid : 1
             * delflg : 00
             * comuid : 27677c94e420451c914c7fe2c5d84fba
             * type : 03
             * cid : asdad122312e
             */

            public String uid;
            public String content;
            public String comid;
            public String delflg;
            public String comuid;
            public String type;
            public String cid;
        }
    }
}
