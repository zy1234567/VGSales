package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/9/23.
 */

public class GetOrgListItemsBean extends BaseRespBean {


    /**
     * pager : {"totalRows":7,"pageSize":10,"currentPage":1,"totalPages":1,"startRow":0}
     * list : [{"rbiid":27,"rbicreatetime":"2017-08-31 12:46:11","multiplenum":0,"suspectednum":0,"rbiaddress":"北京市朝阳区大郊亭南街","rbioname":"牛宝宝","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0105","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":31,"rbicreatetime":"2017-07-28 21:06:29","multiplenum":0,"suspectednum":0,"rbiaddress":"北京市海淀区莲花桥京都信苑饭店西南102米","rbioname":"光晨艺术","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0104,0107,0108","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":32,"rbicreatetime":"2017-08-23 09:30:30","multiplenum":0,"rbibackmsg":"该机构唯一并真实存在，审核通过","suspectednum":1,"rbiaddress":"Fasfasfsdaf","rbioname":"Dad","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0101","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":38,"rbicreatetime":"2017-08-14 15:19:56","multiplenum":0,"suspectednum":0,"rbiaddress":"123","rbioname":"123","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0505","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":178,"rbicreatetime":"2017-08-05 18:03:25","multiplenum":0,"suspectednum":0,"rbiaddress":"北京市大兴区天华大街中粮丰通(北京)食品有限公司内","rbioname":"记录","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0101","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":237,"rbicreatetime":"2017-08-12 10:37:21","multiplenum":0,"rbibackmsg":"该机构唯一并真实存在，审核通过","suspectednum":1,"rbiaddress":"北京市东城区龙潭湖路8号","rbioname":"未来优学教育集团","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"1404","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"},{"rbiid":278,"rbicreatetime":"2017-08-23 17:19:18","multiplenum":0,"suspectednum":1,"rbiaddress":"北京市丰台区角门路13号","rbioname":"姚姚银色音画22","rbiphone":"18858585858","rbidistrict":"110101","rbiotype":"0101,0102","comefrom":"自行注册","rbilogosurl":"http://static.txboss.com/matter/15abroad.png"}]
     */

    public PagerBean pager;
    public List<ListBean> list;


    public static class ListBean {
        /**
         * rbiid : 27
         * rbicreatetime : 2017-08-31 12:46:11
         * multiplenum : 0
         * suspectednum : 0
         * rbiaddress : 北京市朝阳区大郊亭南街
         * rbioname : 牛宝宝
         * rbiphone : 18858585858
         * rbidistrict : 110101
         * rbiotype : 0105
         * comefrom : 自行注册
         * rbilogosurl : http://static.txboss.com/matter/15abroad.png
         * rbibackmsg : 该机构唯一并真实存在，审核通过
         */

        public int rbiid;
        public String rbicreatetime;
        public int multiplenum;
        public int suspectednum;
        public String rbiaddress;
        public String rbioname;
        public String rbiphone;
        public String rbidistrict;
        public String rbiotype;
        public String comefrom;
        public String rbilogosurl;
        public String rbibackmsg;
    }
}
