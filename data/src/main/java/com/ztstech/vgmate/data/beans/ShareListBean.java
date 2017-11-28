package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/27
 */

public class ShareListBean extends BaseRespBean{


    /**
     * pager : {"totalRows":4,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"commentList":[],"ntype":"03","createtime":"2017-11-28 11:50:12","userpicsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","likeList":[],"uname":"王晨曦","contentpicsurl":"","title":"哦www","content":"民工","url":"http://zts-user-pc:8082/static/html/20171128/115008111939.html","sid":"f58442ce84204ad694b8990ef8d4edf0","delflg":"00","userpicurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","uid":"0a8822da7cb84ebe9ece458dc909a6b1","contentpicurl":"","linkurl":""},{"picdescribe":"","commentList":[],"createtime":"2017-11-27 17:45:51","userpicsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","likeList":[],"uname":"王晨曦","contentpicsurl":"","title":"","content":"00000","url":"http://zts-user-pc:8082/static/html/20171127/174551715003.html","sid":"078fe08633504c56a346231d3f5c2d80","delflg":"00","userpicurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","uid":"0a8822da7cb84ebe9ece458dc909a6b1","contentpicurl":""},{"picdescribe":"","commentList":[],"createtime":"2017-11-27 17:45:30","userpicsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","likeList":[],"uname":"王晨曦","contentpicsurl":"","title":"","content":"00000","url":"http://zts-user-pc:8082/static/html/20171127/174520707301.html","sid":"cf02f97ba3e540e1a416fe6e4de92971","delflg":"00","userpicurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","uid":"0a8822da7cb84ebe9ece458dc909a6b1","contentpicurl":""},{"delflg":"00","userpicurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","commentList":[{"comuid":"27677c94e420451c914c7fe2c5d84fba","delflg":"00","uid":"2c907e825e41abc4015e41af9d900002","uname":"BellKate","comid":"1","type":"03","content":"不错+1","cid":"asdad122312e"},{"comuid":"27677c94e420451c914c7fe2c5d84fba","delflg":"00","picurl":"http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg","uid":"2c907e825e17044a015e1723fc2d0004","uname":"1312312","picsurl":"http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg","comid":"1","type":"03","content":"不错","cid":"12312312312312"}],"uid":"0a8822da7cb84ebe9ece458dc909a6b1","userpicsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","likeList":[{"delflg":"00","picurl":"http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg","likeduid":"27677c94e420451c914c7fe2c5d84fba","uid":"2c907e825e17044a015e1723fc2d0004","uname":"1312312","likestatus":"00","liketype":"02","likedid":"1","picsurl":"http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg","likid":"1021245dc23123usdas3123uiiosajo1"},{"delflg":"00","likeduid":"27677c94e420451c914c7fe2c5d84fba","uid":"2c907e825e41abc4015e41af9d900002","uname":"BellKate","likestatus":"00","liketype":"02","likedid":"1","likid":"102935fc0ad940d64214affdb994879e"},{"delflg":"00","picurl":"http://static.verygrow.com/bucea/image/20171010/1_0_210744207818.jpg","likeduid":"27677c94e420451c914c7fe2c5d84fba","uid":"2c907e825eac850f015eac9041d50006","uname":"Wang","likestatus":"00","liketype":"02","likedid":"1","picsurl":"http://static.verygrow.com/bucea/image/20171010/0_210744207818.jpg","likid":"123123123214dsssd1231231dasd123"}],"uname":"王晨曦","title":"题目1","content":"内容1","sid":"1"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 4
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
         * commentList : []
         * ntype : 03
         * createtime : 2017-11-28 11:50:12
         * userpicsurl : http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg
         * likeList : []
         * uname : 王晨曦
         * contentpicsurl :
         * title : 哦www
         * content : 民工
         * url : http://zts-user-pc:8082/static/html/20171128/115008111939.html
         * sid : f58442ce84204ad694b8990ef8d4edf0
         * delflg : 00
         * userpicurl : http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg
         * uid : 0a8822da7cb84ebe9ece458dc909a6b1
         * contentpicurl :
         * linkurl :
         * picdescribe :
         */

        public String ntype;
        public String createtime;
        public String userpicsurl;
        public String uname;
        public String contentpicsurl;
        public String title;
        public String content;
        public String url;
        public String sid;
        public String delflg;
        public String userpicurl;
        public String uid;
        public String contentpicurl;
        public String linkurl;
        public String picdescribe;
        public List<CommentListBean> commentList;
        public List<LikeListBean> likeList;
        public String likestatus;
        public String picurl;
    }

    public static class LikeListBean{

        /**
         * delflg : 00
         * picurl : http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg
         * likeduid : 27677c94e420451c914c7fe2c5d84fba
         * uid : 2c907e825e17044a015e1723fc2d0004
         * uname : 1312312
         * likestatus : 00
         * liketype : 02
         * likedid : 1
         * picsurl : http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg
         * likid : 1021245dc23123usdas3123uiiosajo1
         */

        public String delflg;
        public String picurl;
        public String likeduid;
        public String uid;
        public String uname;
        public String likestatus;
        public String liketype;
        public String likedid;
        public String picsurl;
        public String likid;
    }

    public static class CommentListBean{

        /**
         * comuid : 27677c94e420451c914c7fe2c5d84fba
         * delflg : 00
         * uid : 2c907e825e41abc4015e41af9d900002
         * uname : BellKate
         * comid : 1
         * type : 03
         * content : 不错+1
         * cid : asdad122312e
         */

        public String comuid;
        public String delflg;
        public String uid;
        public String uname;
        public String comid;
        public String type;
        public String content;
        public String cid;
    }
}
