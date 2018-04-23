package com.ztstech.vgmate.data.dto;

/**
 * Created by dongdong on 2018/4/21.
 */

public class OrgPassData {
    /**
     * 路人登记 定位认证
     * 机构登记 加V认证
     *  @param: authId
     * @param: rbiid 机构rbiid
     * @param: rbiostatus 00：审核通过
     * @param: identificationtype 01:定位认证,02:加V认证
     * @param: terminal 处理终端，01：客服平台，02：其他
     * @param: type 00机构沟通记录，01机会沟通记录，02打点机构沟通记录
     * @param: communicationtype 沟通方式,00:电话沟通,01:上门拜访,03:远程审核
     * @param: contactsname 联系人姓名
     * @param: contactsphone 联系人手机
     * @param: roletype 担任职位
     * @param: msg 沟通内容
     * @param: description 补充说明
     * @param: spotgps 实地定位
     * @param: spotphotos 实地拍照
     * @param: wechatid 微信号码
     * @param: videopicurl 视频通话截图
     * @param: positionpicurl 位置共享截图
     */
    /**
     * 审核通过的数据实体类
     */
    public String rbiid;
    public String rbiostatus;
    public String identificationtype;
    public String terminal;
    public String type;
    public String communicationtype;
    public String contactsname;
    public String contactsphone;
    public String roletype;
    public String msg;
    public String description;
    public String spotgps;
    public String spotphotos;
    public String wechatid;
    public String videopicurl;
    public String positionpicurl;
}
