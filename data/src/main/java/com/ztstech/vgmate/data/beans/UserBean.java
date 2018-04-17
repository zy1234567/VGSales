package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/8/29.
 */

public class UserBean extends BaseRespBean{


    /**
     * messageCode : 0
     * area : []
     * rlist : [{"rrid":"22","rname":"销售","rtid":"1"}]
     * status : 0
     * authId : USERff8080815e2d9eb9015e2e3e47550002
     * info : {"finalmoney":0,"uid":"402881fb5e0dea5e015e0deb71150001","birthday":"2017-08-24","sex":"01","phone":"13111111111","otherscnt":0,"salelev":2,"fifthbudgets":0,"loginnum":512,"secondcnt":0,"password":"111111","cardNo":"1333333333333333333333","createtime":"2017-08-23 15:08:07","topuid":"402881fb5e0dea5e015e0deb71150001","fifthcnt":0,"lastlogintime":"2017-08-29 19:00:53","realmoney":0,"wdistrict":"210681","thirdbudgets":0,"wprovince":"210000","budgets":0,"fourthcnt":0,"fname":"王晨曦","picsurl":"http://static.verygrow.com/bucea/image/20170826/0_200022866420.jpg","status":"04","clerkcnt":2,"uname":"张某某","picurl":"http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg","did":"130421199901016012","clerkbudgets":0,"firstcnt":2,"maxmoney":0,"firstbudgets":4,"bname":"132123213","didurl":"http://static.verygrow.com/bucea/image/20170824/1_0_150726177899.jpg,http://static.verygrow.com/bucea/image/20170824/1_0_150727986242.jpg","wcity":"210600","salelevel":"1,1","othersbudgets":0,"thirdcnt":0,"secondbudgets":0,"fuid":"0a8822da7cb84ebe9ece458dc909a6b1","cardImg":"http://static.verygrow.com/bucea/image/20170824/1_0_150727233061.jpg","banks":"123123","fourthbudgets":0}
     */

    public String authId;
    public InfoBean info;
    public List<?> area;
    public List<RlistBean> rlist;

    public static class InfoBean {
        /**
         * finalmoney : 0
         * uid : 402881fb5e0dea5e015e0deb71150001
         * birthday : 2017-08-24
         * sex : 01
         * phone : 13111111111
         * otherscnt : 0
         * salelev : 2
         * fifthbudgets : 0
         * loginnum : 512
         * secondcnt : 0
         * password : 111111
         * cardNo : 1333333333333333333333
         * createtime : 2017-08-23 15:08:07
         * topuid : 402881fb5e0dea5e015e0deb71150001
         * fifthcnt : 0
         * lastlogintime : 2017-08-29 19:00:53
         * realmoney : 0
         * wdistrict : 210681
         * thirdbudgets : 0
         * wprovince : 210000
         * budgets : 0
         * fourthcnt : 0
         * fname : 王晨曦
         * picsurl : http://static.verygrow.com/bucea/image/20170826/0_200022866420.jpg
         * status : 04
         * clerkcnt : 2
         * uname : 张某某
         * picurl : http://static.verygrow.com/bucea/image/20170826/1_0_200022866420.jpg
         * did : 130421199901016012
         * clerkbudgets : 0
         * firstcnt : 2
         * maxmoney : 0
         * firstbudgets : 4
         * bname : 132123213
         * didurl : http://static.verygrow.com/bucea/image/20170824/1_0_150726177899.jpg,http://static.verygrow.com/bucea/image/20170824/1_0_150727986242.jpg
         * wcity : 210600
         * salelevel : 1,1
         * othersbudgets : 0
         * thirdcnt : 0
         * secondbudgets : 0
         * fuid : 0a8822da7cb84ebe9ece458dc909a6b1
         * cardImg : http://static.verygrow.com/bucea/image/20170824/1_0_150727233061.jpg
         * banks : 123123
         * fourthbudgets : 0
         */

        public int finalmoney;
        public String uid;
        public String birthday;
        public String sex;
        public String phone;
        public int otherscnt;
        /**
         * 为1表示为超级管理员
         */
        public int salelev;
        public int fifthbudgets;
        public int loginnum;
        public int secondcnt;
        public String password;
        public String cardNo;
        public String createtime;
        public String topuid;
        public int fifthcnt;
        public String lastlogintime;
        public int realmoney;
        public String wdistrict;
        public int thirdbudgets;
        public String wprovince;
        public int budgets;
        public int fourthcnt;
        public String fname;
        public String picsurl;
        public String status;
        public int clerkcnt;
        public String uname;
        public String picurl;
        public String did;
        public int clerkbudgets;
        public int firstcnt;
        public int maxmoney;
        public int firstbudgets;
        public String bname;
        public String didurl;
        public String wcity;
        public String salelevel;
        public int othersbudgets;
        public int thirdcnt;
        public int secondbudgets;
        public String fuid;
        public String cardImg;
        public String banks;
        public int fourthbudgets;
        public String addtype;//是否可以添加伙伴
    }

    public static class RlistBean {
        /**
         * rrid : 22
         * rname : 销售
         * rtid : 1
         */

        public String rrid;
        public String rname;
        public String rtid;
    }
}
