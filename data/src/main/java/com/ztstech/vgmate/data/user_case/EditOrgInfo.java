package com.ztstech.vgmate.data.user_case;

import com.ztstech.vgmate.data.api.OrgInfoApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.data.utils.RetrofitUtils;

import rx.Observable;

/**
 * Created by zhiyuan on 2017/10/29.
 * 编辑机构资料
 */

public class EditOrgInfo implements UserCase<Observable<BaseRespBean>> {

    private OrgInfoApi api;

    private String rbiid;
    private String oname;
    private String advertisingwall;
    private String advertisingwallsurl;
    private String walldescribe;
    private String introduction;
    private String courseintroduction;
    private String tollintroduction;
    private String tag;
    private String contphone;
    private String manager;
    private String managerphone;
    private String logourl;
    private String logosurl;



    public EditOrgInfo(String rbiid, String oname, String advertisingwall, String advertisingwallsurl,
                       String walldescribe, String introduction, String courseintroduction,
                       String tollintroduction, String tag, String contphone, String manager,
                       String managerphone, String logourl, String logosurl) {
        api = RetrofitUtils.createService(OrgInfoApi.class);
        this.rbiid = rbiid;
        this.oname = oname;
        this.advertisingwall = advertisingwall;
        this.advertisingwallsurl = advertisingwallsurl;
        this.walldescribe = walldescribe;
        this.introduction = introduction;
        this.courseintroduction = courseintroduction;
        this.tollintroduction = tollintroduction;
        this.tag = tag;
        this.contphone = contphone;
        this.manager = manager;
        this.managerphone = managerphone;
        this.logourl = logourl;
        this.logosurl = logosurl;
    }

    @Override
    public Observable<BaseRespBean> run() {
        return api.editOrgInfo(rbiid, oname, advertisingwall, advertisingwallsurl, walldescribe,
                introduction, courseintroduction, tollintroduction, tag, contphone, manager,
                managerphone, logourl, logosurl, UserRepository.getInstance().getAuthId());
    }
}
