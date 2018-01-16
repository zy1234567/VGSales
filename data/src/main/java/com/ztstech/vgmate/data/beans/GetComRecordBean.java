package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by lenovo on 2018/1/16.
 */

public class GetComRecordBean extends BaseRespBean {

    /**
     * pager : {"totalRows":5,"pageSize":20,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"msg":"啊咯呃呃呃","createtime":"2018-01-16 16:04:12","uname":"单明明","callon":"01","spotgps":"116.31465911865234,39.69002466069576","followtype":"04","rbicontphone":"13834673831","communicationtype":"00","backstage":"01","contactsname":"明明前","makecall":"12345678906"},{"msg":"阿里啦咯了","createtime":"2018-01-16 16:01:48","uname":"单明明","callon":"01","spotgps":"116.31465911865234,39.689975124949605","followtype":"02","rbicontphone":"13834673831","communicationtype":"00","backstage":"01","contactsname":"阿里啦咯了","makecall":"23665547896"},{"msg":"","createtime":"2018-01-16 16:00:23","uname":"单明明","callon":"01","rbicontphone":"13834673831","communicationtype":"00","backstage":"01","contactsname":"被","makecall":"23655888888"},{"msg":"","createtime":"2018-01-16 15:59:21","uname":"单明明","callon":"01","rbicontphone":"13834673831","communicationtype":"00","backstage":"01","contactsname":"本来里","makecall":"25633336666"},{"msg":"","createtime":"2018-01-16 15:48:47","uname":"单明明","callon":"01","rbicontphone":"13834673831","communicationtype":"00","backstage":"01","contactsname":"本机","makecall":"12345678901"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 5
         * pageSize : 20
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
         * msg : 啊咯呃呃呃
         * createtime : 2018-01-16 16:04:12
         * uname : 单明明
         * callon : 01
         * spotgps : 116.31465911865234,39.69002466069576
         * followtype : 04
         * rbicontphone : 13834673831
         * communicationtype : 00
         * backstage : 01
         * contactsname : 明明前
         * makecall : 12345678906
         */

        public String msg;
        public String createtime;
        public String uname;
        public String callon;
        public String spotgps;
        public String followtype;
        public String rbicontphone;
        public String communicationtype;
        public String backstage;
        public String contactsname;
        public String makecall;
        public String roletype;
    }
}
