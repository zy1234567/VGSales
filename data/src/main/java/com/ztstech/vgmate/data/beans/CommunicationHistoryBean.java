package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/11.
 * 沟通记录
 */

public class CommunicationHistoryBean extends BaseRespBean {


    public PagerBean pager;
    public List<ListBean> list;


    public static class ListBean {
        /**
         * createtime : 2017-10-24 23:29:51
         * uname : Wang
         * msg : 你好，
         */

        public String createtime;
        public String uname;
        public String msg;
    }
}
