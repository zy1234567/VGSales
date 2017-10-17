package com.ztstech.vgmate.data.beans;

/**
 * Created by zhiyuan on 2017/10/16.
 */

public class OrgInfoBean extends BaseRespBean {
    /**
     * info : {"walldescribe":"[\n  \"\",\n  \"Rtyhsgdsjahkshjdghshajsk\",\n  \"8765434567890456789876787678767878878778778787678767878778767767878787878987876789\"\n]","phone":"13691496869","logourl":"http://static.txboss.com/matter/01language.png","advertisingwall":"http://static.verygrow.com/bucea/image/20171016/1_0_104437101071.jpg,http://static.verygrow.com/bucea/image/20171016/1_0_104437891294.jpg,http://static.verygrow.com/bucea/image/20171016/1_0_104437256456.jpg","address":"北京市朝阳区成寿寺路258号","oname":"六大埭仓库","logosurl":"http://static.txboss.com/matter/01language.png","advertisingwallsurl":"http://static.verygrow.com/bucea/image/20171016/0_104437101071.jpg,http://static.verygrow.com/bucea/image/20171016/0_104437891294.jpg,http://static.verygrow.com/bucea/image/20171016/0_104437256456.jpg","gps":"39.843581,116.454350","district":"120101","otype":"0101","introduction":"TqywieuytwuyiquwoidsfygahkdvgakjLdkfgjshajk"}
     */

    public InfoBean info;

    public static class InfoBean {
        /**
         * walldescribe : [
         "",
         "Rtyhsgdsjahkshjdghshajsk",
         "8765434567890456789876787678767878878778778787678767878778767767878787878987876789"
         ]
         * phone : 13691496869
         * logourl : http://static.txboss.com/matter/01language.png
         * advertisingwall : http://static.verygrow.com/bucea/image/20171016/1_0_104437101071.jpg,http://static.verygrow.com/bucea/image/20171016/1_0_104437891294.jpg,http://static.verygrow.com/bucea/image/20171016/1_0_104437256456.jpg
         * address : 北京市朝阳区成寿寺路258号
         * oname : 六大埭仓库
         * logosurl : http://static.txboss.com/matter/01language.png
         * advertisingwallsurl : http://static.verygrow.com/bucea/image/20171016/0_104437101071.jpg,http://static.verygrow.com/bucea/image/20171016/0_104437891294.jpg,http://static.verygrow.com/bucea/image/20171016/0_104437256456.jpg
         * gps : 39.843581,116.454350
         * district : 120101
         * otype : 0101
         * introduction : TqywieuytwuyiquwoidsfygahkdvgakjLdkfgjshajk
         */

        public String phone;
        public String logourl;
        public String oname;
        public String gps;
        public String district;
        public String manager;
        public String manageruid;
        public String managerphone;
        public String address;
        public String updatetime;
        public String updatename;
        /**该机构是否可以被编辑(00可以,01不可以)*/
        public String status;
        public String logo;
        public String rbioname;
        public String advertisingwall;
        public String advertisingwallsurl;
        public String walldescribe;
        public String introduction;
        public String courseintroduction;
        public String tollintroduction;
        public String tag;
        public String names;
        public String logosurl;
        public String otype;


//        * rbioname 培训机构名称
//  * advertisingwall 照片墙
//  * advertisingwallsurl 照片墙缩略图
//  * walldescribe 照片墙描述
//  *introduction 机构简介
//  * courseintroduction 课程简介
//  * tollintroduction 收费简介
//  * tag 招生标签
//  * names 教师名称
//  * manager 负责人
//  * manageruid  负责人id
//  * phone 咨询电话
//  * logosurl logo缩略图
//  * logourl logo地址
//  * otype 类型编码
//  * district 区县
//  * gps GPS坐标
//  * address 机构地址
//  * managerphone 负责人手机号
//  * updatetime 更新时间
//  * updatename 更新人
    }


//    public List<WallImageBean> advertisingWalldata;
////    var advertisingWalldata:[WallImageBean] = []
////
////    var oname = ""//机构名字
//    public String oname;
////    var district = ""//所在地区
//    public String district;
////    var rbioname = ""//培训机构名称
//    public String rbioname;
////
////    var advertisingwall = ""//广告墙
//    public String advertisingwall;
////    var advertisingwallsurl = ""//广告墙缩略图
//    public String advertisingwallsurl;
////    var walldescribe = ""//广告墙描述
//    public String walldescribe;
////
////    var introduction = ""//机构简介
//    public String introduction;
////    var courseintroduction = ""//课程简介
//    public String courseintroduction;
////    var tollintroduction = ""//收费简介
//    public String tollintroduction;
////    var tag = ""//招生标签
//    public String tag;
////    var names = ""//教师名称
//    public String names;
////    var manager = ""//负责人
//    public String manager;
////    var manageruid = ""//负责人id
//    public String manageruid;
////    var managerphone = ""//负责人手机号
//    public String managerphone;
////    var phone = ""//咨询电话
//    public String phone;
////    var logosurl = ""//logo缩略图
////    var logourl = ""//logo地址
////    var otype = ""//机构标签
////    var gps = ""//GPS坐标
////    var address = ""//机构地址
////
////    var logo:UIImage?//图片logo
////
////    var rbiid:String = ""///机构id
////    var status = ""//该机构是否可以被编辑(00可以,01不可以)
////
////    var updatetime = ""//最后更新时间
////    var updatename = ""//最后跟新人

}
