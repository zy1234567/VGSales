package com.ztstech.vgmate.data.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by smm on 2017/11/23.
 */

public class MatelistBean extends BaseRespBean{


    /**
     * pager : {"totalRows":127,"pageSize":10,"currentPage":1,"totalPages":13,"startRow":0}
     * list : [{"birthday":"1980-08-23","firstcnt":35,"uname":"王晨曦","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg","maxmoney":0,"weeklogdays":85,"loginnum":1492,"lastlogintime":"2017-11-23 16:14:45","todays":163,"picurl":"http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg","uid":"0a8822da7cb84ebe9ece458dc909a6b1","phone":"18611999116","budgets":0,"firstbudgets":0,"salelevel":"1","district":"110102","zerenrenuid":"0a8822da7cb84ebe9ece458dc909a6b1","zerenren":"王晨曦","status":"00"},{"birthday":"1994-07-21","firstcnt":5,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"汪玲玉","sex":"02","picsurl":"http://static.verygrow.com/bucea/null/20171122/0_002123800607null","weeklogdays":60,"loginnum":213,"lastlogintime":"2017-11-23 14:48:27","todays":24,"picurl":"http://static.verygrow.com/bucea/null/20171122/1_0_002123800607null","principal":"汪玲玉","uid":"ff8080815f6bcb11015f6bdab3eb0001","phone":"18898570721","budgets":0,"firstbudgets":0,"salelevel":"1,1","district":"440105","zerenrenuid":"0a8822da7cb84ebe9ece458dc909a6b1","zerenren":"王晨曦","status":"00"},{"birthday":"1987-09-13","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"蒋小科","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171108/0_101248796528.jpg","weeklogdays":60,"loginnum":145,"lastlogintime":"2017-11-23 14:03:57","todays":16,"picurl":"http://static.verygrow.com/bucea/image/20171108/1_0_101248796528.jpg","principal":"汪玲玉","uid":"ff8080815f960e5e015f97000f26008b","phone":"15111125894","budgets":0,"firstbudgets":0,"salelevel":"1,1,1","district":"430104","zerenrenuid":"ff8080815f6bcb11015f6bdab3eb0001","zerenren":"汪玲玉","status":"00"},{"birthday":"1991-03-04","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"吴勋信","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171108/0_093203319444.jpg","weeklogdays":31,"loginnum":75,"lastlogintime":"2017-11-23 13:19:55","todays":16,"picurl":"http://static.verygrow.com/bucea/image/20171108/1_0_093203319444.jpg","principal":"汪玲玉","uid":"ff8080815f960e5e015f97010168008c","phone":"18076332301","budgets":0,"firstbudgets":0,"salelevel":"1,1,2","district":"450103","zerenrenuid":"ff8080815f6bcb11015f6bdab3eb0001","zerenren":"汪玲玉","status":"00"},{"birthday":"1993-08-12","firstcnt":1,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"金献君","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171110/0_093947113184.jpg","weeklogdays":42,"loginnum":83,"lastlogintime":"2017-11-23 09:16:39","todays":13,"picurl":"http://static.verygrow.com/bucea/image/20171110/1_0_093947113184.jpg","principal":"汪玲玉","uid":"ff8080815fa37e1c015fa380a3f30001","phone":"18390688566","budgets":0,"firstbudgets":0,"salelevel":"1,1,3","district":"440105","zerenrenuid":"ff8080815f6bcb11015f6bdab3eb0001","zerenren":"汪玲玉","status":"00"},{"birthday":"1992-03-02","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"刘伟盛","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171123/0_092212591379.jpg","weeklogdays":105,"loginnum":15,"lastlogintime":"2017-11-23 15:33:43","todays":0,"picurl":"http://static.verygrow.com/bucea/image/20171123/1_0_092212591379.jpg","principal":"汪玲玉","uid":"ff8080815fbedc89015fe66f77aa002e","phone":"13640744313","budgets":0,"firstbudgets":0,"salelevel":"1,1,3,1","district":"440105","zerenrenuid":"ff8080815fa37e1c015fa380a3f30001","zerenren":"金献君","status":"00"},{"birthday":"1994-09-09","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"黄春语","sex":"01","weeklogdays":56,"loginnum":8,"lastlogintime":"2017-11-23 14:52:22","todays":0,"picurl":"http://static.verygrow.com/bucea/null/20171123/1_0_092538534919null","principal":"汪玲玉","uid":"ff8080815fbedc89015fe66ed13d002d","phone":"18273148628","budgets":0,"firstbudgets":0,"salelevel":"1,1,4","district":"440105","zerenrenuid":"ff8080815f6bcb11015f6bdab3eb0001","zerenren":"汪玲玉","status":"00"},{"birthday":"1992-08-13","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bdab3eb0001","uname":"邓丽芬","sex":"02","weeklogdays":42,"loginnum":6,"lastlogintime":"2017-11-23 12:28:20","todays":0,"picurl":"http://static.verygrow.com/bucea/null/20171123/1_0_092427458898null","principal":"汪玲玉","uid":"ff8080815fbedc89015fe670e436002f","phone":"15800255311","budgets":0,"firstbudgets":0,"salelevel":"1,1,5","district":"440105","zerenrenuid":"ff8080815f6bcb11015f6bdab3eb0001","zerenren":"汪玲玉","status":"00"},{"birthday":"1981-12-08","firstcnt":0,"principaluid":"ff8080815f6bcb11015f6bfd606d0006","uname":"昆仑石","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171108/0_194936183369.jpg","weeklogdays":23,"loginnum":82,"lastlogintime":"2017-11-23 13:13:10","todays":24,"picurl":"http://static.verygrow.com/bucea/image/20171108/1_0_194936183369.jpg","principal":"昆仑石","uid":"ff8080815f6bcb11015f6bfd606d0006","phone":"13718525256","budgets":0,"firstbudgets":0,"salelevel":"1,10","district":"110115","zerenrenuid":"0a8822da7cb84ebe9ece458dc909a6b1","zerenren":"王晨曦","status":"01"},{"birthday":"1997-10-31","firstcnt":0,"principaluid":"2c907e8e5f71b70f015f71bce0610002","uname":"李铁柱测试","sex":"01","picsurl":"http://static.verygrow.com/bucea/image/20171031/0_172306886789.jpg","weeklogdays":122,"loginnum":418,"lastlogintime":"2017-11-23 09:35:30","todays":23,"picurl":"http://static.verygrow.com/bucea/image/20171031/1_0_172306886789.jpg","principal":"李铁柱测试","uid":"2c907e8e5f71b70f015f71bce0610002","phone":"13051154840","budgets":0,"firstbudgets":0,"salelevel":"1,11","district":"110101","zerenrenuid":"0a8822da7cb84ebe9ece458dc909a6b1","zerenren":"王晨曦","status":"04"}]
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 127
         * pageSize : 10
         * currentPage : 1
         * totalPages : 13
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
         * birthday : 1980-08-23
         * firstcnt : 35
         * uname : 王晨曦
         * sex : 01
         * picsurl : http://static.verygrow.com/bucea/image/20170831/0_193958598544.jpg
         * maxmoney : 0
         * weeklogdays : 85
         * loginnum : 1492
         * lastlogintime : 2017-11-23 16:14:45
         * todays : 163
         * picurl : http://static.verygrow.com/bucea/image/20170831/1_0_193958598544.jpg
         * uid : 0a8822da7cb84ebe9ece458dc909a6b1
         * phone : 18611999116
         * budgets : 0
         * firstbudgets : 0
         * salelevel : 1
         * district : 110102
         * zerenrenuid : 0a8822da7cb84ebe9ece458dc909a6b1
         * zerenren : 王晨曦
         * status : 00
         * principaluid : ff8080815f6bcb11015f6bdab3eb0001
         * principal : 汪玲玉
         */

        public String birthday;
        public int firstcnt;
        public String uname;
        public String sex;
        public String picsurl;
        public int maxmoney;
        public int weeklogdays;
        public int loginnum;
        public String lastlogintime;
        public int todays;
        public String picurl;
        public String uid;
        public String phone;
        public int budgets;
        public int firstbudgets;
        public String salelevel;
        public String district;
        public String zerenrenuid;
        public String zerenren;
        public String status;
        public String principaluid;
        public String principal;
        public String oname;
    }
}
