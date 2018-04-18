package com.ztstech.vgmate.data.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dongdong on 2018/4/18.
 */

public class RobChanceBean extends BaseRespBean implements Serializable {
    /**
     * pager : {"totalRows":126,"pageSize":10,"currentPage":1,"totalPages":13,"startRow":0}
     * list : [{"rbiid":524347,"rbiaddress":"北京市大兴区中关村国家自主创新示范区大兴生物医药产业基地北京星伟体育用品有限公司","createtime":"2018-01-22 15:01:55","rbiprovince":"110000","cstatus":"11","rbioname":"敬秋菊地图app路人登记机构九二五","marketname":"周峰","chancetype":"08","rbilogosurl":"http://static.txboss.com/matter/01language.png   ","rbicity":"110100","rbiotype":"A0045,A0046,A0047","rbidistrict":"110115","contractphone":"","rbilogo":"http://static.txboss.com/matter/01language.png   "},{"rbiid":524342,"rbiaddress":"大法师的","createtime":"2018-01-19 16:50:04","rbiprovince":"110000","cstatus":"11","rbioname":"敬秋菊网页机构登记九二二","marketname":"周峰","chancetype":"05","contractname":"敬九二二","rbilogosurl":"http://static.txboss.com/matter/17life.png   ","rbicity":"110100","rbiotype":"A0178,A0179,A0180","rbidistrict":"110115","contractphone":"15522406922","rbilogo":"http://static.txboss.com/matter/17life.png   "},{"rbiid":25171,"rbiaddress":"永兴路七号院龙湖大兴天街购物中心3层","createtime":"2018-01-06 17:21:19","rbiprovince":"110000","cstatus":"14","rbioname":"天天艺术·魔法音乐舞蹈教室(龙...","marketname":"周峰","chancetype":"04","contractname":"范娇荣","rbilogosurl":"http://static.txboss.com/matter/04dance.png   ","nowchancetype":"04","rbicity":"110100","rbiotype":"A0101","rbidistrict":"110115","contractphone":"18810679533","rbilogo":"http://static.txboss.com/matter/04dance.png   "},{"rbiid":524680,"rbiaddress":"打手动发","createtime":"2018-03-01 18:33:59","rbiprovince":"110000","cstatus":"14","rbioname":"敬秋菊销售网页登记一九","marketname":"周峰","chancetype":"09","rbilogosurl":"http://static.txboss.com/matter/english.png   ","rbicity":"110100","rbiotype":"A0025,A0026,A0027","rbidistrict":"110115","contractphone":"15522456819","rbilogo":"http://static.txboss.com/matter/english.png   "}]
     * status : 0
     */

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 126
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
         * rbiid : 524347
         * rbiaddress : 北京市大兴区中关村国家自主创新示范区大兴生物医药产业基地北京星伟体育用品有限公司
         * createtime : 2018-01-22 15:01:55
         * rbiprovince : 110000
         * cstatus : 11
         * rbioname : 敬秋菊地图app路人登记机构九二五
         * marketname : 周峰
         * chancetype : 08
         * rbilogosurl : http://static.txboss.com/matter/01language.png
         * rbicity : 110100
         * rbiotype : A0045,A0046,A0047
         * rbidistrict : 110115
         * contractphone :
         * rbilogo : http://static.txboss.com/matter/01language.png
         * contractname : 敬九二二
         * nowchancetype : 04
         */

        public int rbiid;
        public String rbiaddress;
        public String createtime;
        public String rbiprovince;
        public String cstatus;
        public String rbioname;
        public String marketname;
        public String chancetype;
        public String rbilogosurl;
        public String rbicity;
        public String rbiotype;
        public String rbidistrict;
        public String contractphone;
        public String rbilogo;
        public String contractname;
        public String nowchancetype;
        public String locktype; //是否锁定，00：未锁定，01：已锁定
    }

}
