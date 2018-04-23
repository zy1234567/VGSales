package com.ztstech.vgmate.data.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author smm
 * @date 2017/11/14
 */

public class OrgFollowlistBean extends BaseRespBean implements Serializable{


    /**
     * pager : {"totalRows":2,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"rbiid":494071,"rbiaddress":"和平东路205号","rbiprovince":"130000","rbioname":"金乐灵音培训","comefrom":"网络抓取","waitstatus":"01","rbiostatus":"00","orgid":"","rbigps":"114.52885,38.05416","useport":"00","rbicity":"130100","rbiotype":"A0090","rbidistrict":"130102","rbicontphone":"15383833980"},{"rbiid":185310,"rbiaddress":"育才街与谈北路交叉口华药一区5栋","rbiprovince":"130000","rbioname":"速成教育中心","comefrom":"网络抓取","waitstatus":"01","rbiostatus":"00","orgid":"","rbigps":"114.52756,38.051032","useport":"00","rbicity":"130100","rbiotype":"A0002,A0004,A0006","rbidistrict":"130102","rbicontphone":"15100131050"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean implements Serializable{
        /**
         * totalRows : 2
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

    public static class ListBean implements Serializable{
        /**
         * rbiid : 494071
         * rbiaddress : 和平东路205号
         * rbiprovince : 130000
         * rbioname : 金乐灵音培训
         * comefrom : 网络抓取
         * waitstatus : 01
         * rbiostatus : 00
         * orgid :
         * rbigps : 114.52885,38.05416
         * useport : 00
         * rbicity : 130100
         * rbiotype : A0090
         * rbidistrict : 130102
         * rbicontphone : 15383833980
         */

        public int rbiid;
        public String rbiaddress;
        public String rbiprovince;
        public String rbioname;
        public String comefrom;
        public String waitstatus;
        public String rbiostatus;
        public String orgid;
        public String rbigps;
        public String useport;
        public String rbilogo;
        public String rbilogosurl;
        public String rbicity;
        public String rbiotype;
        public String rbidistrict;
        public String rbicontphone;
        public String name;
        public String position;
        public String phone;
        public String aptitudeurl;
        public String rbicreatetime;
        public String calid;
        public String identificationtype;
        public String type;
        public String testorg;
        public String cstatus;
        public String chancetype;
        public String nowchancetype;
        public String createtime;
        public String contractname;
        public String contractphone;
        public int  orgcount;
        public String createrid;
    }
}
