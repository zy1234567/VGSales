package com.ztstech.vgmate.data.dto;

/**
 * Created by Administrator on 2018/4/21.
 */

public class OrgRegisterRefuseData {
    /**
     * 登记来的机构审核拒绝接口
     * @param rbiid
     * @param authId
     * @param type 01删除（回收站）    00恢复
     * @return
     */
    public String rbiid;
    public String type;
    public String rubbishtype;
    public String refuse;
    public String oname;
}
