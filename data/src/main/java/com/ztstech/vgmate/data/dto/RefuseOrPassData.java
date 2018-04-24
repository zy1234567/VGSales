package com.ztstech.vgmate.data.dto;

import com.ztstech.vgmate.data.beans.BaseRespBean;

/**
 * Created by Administrator on 2018/4/21.
 */

public class RefuseOrPassData  {
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
    public String calid;
    public String identificationtype;
    public String status;
    public String testorg;
    public String statustype;

}
