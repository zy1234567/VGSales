package com.ztstech.vgmate.data.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author smm
 * @date 2017/11/17
 */

public class UnApproveMateBean extends BaseRespBean implements Serializable{


    /**
     * info : {"birthday":"1983-04-30","salelev":3,"uname":"张红容成都","banks":"中信银行","sex":"02","topsalelev":2,"didurl":"http://static.verygrow.com/bucea/null/20171108/1_0_093605939821null,http://static.verygrow.com/bucea/null/20171108/1_0_093605775451null","wdistrict":"510107","cardNo":"6217681001588741","picurl":"http://static.verygrow.com/bucea/null/20171108/1_0_093604836641null","psalelev":2,"puname":"张梅","clerkcnt":0,"puid":"ff8080815f7577ff015f75ab97790007","phone":"18011590841","bname":"张红蓉","salelevel":"1,12,1","cardImg":"http://static.verygrow.com/bucea/null/20171108/1_0_093605248973null","topuid":"ff8080815f7577ff015f75ab97790007","topname":"张梅","did":"513521198304300407","status":"04"}
     */

    public InfoBean info;

    public static class InfoBean implements Serializable{
        /**
         * birthday : 1983-04-30
         * salelev : 3
         * uname : 张红容成都
         * banks : 中信银行
         * sex : 02
         * topsalelev : 2
         * didurl : http://static.verygrow.com/bucea/null/20171108/1_0_093605939821null,http://static.verygrow.com/bucea/null/20171108/1_0_093605775451null
         * wdistrict : 510107
         * cardNo : 6217681001588741
         * picurl : http://static.verygrow.com/bucea/null/20171108/1_0_093604836641null
         * psalelev : 2
         * puname : 张梅
         * clerkcnt : 0
         * puid : ff8080815f7577ff015f75ab97790007
         * phone : 18011590841
         * bname : 张红蓉
         * salelevel : 1,12,1
         * cardImg : http://static.verygrow.com/bucea/null/20171108/1_0_093605248973null
         * topuid : ff8080815f7577ff015f75ab97790007
         * topname : 张梅
         * did : 513521198304300407
         * status : 04
         */

        public String birthday;
        public int salelev;
        public String uname;
        public String banks;
        public String sex;
        public int topsalelev;
        public String didurl;
        public String wdistrict;
        public String cardNo;
        public String picurl;
        public int psalelev;
        public String puname;
        public int clerkcnt;
        public String puid;
        public String phone;
        public String bname;
        public String salelevel;
        public String cardImg;
        public String topuid;
        public String topname;
        public String did;
    }
}
