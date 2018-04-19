package com.ztstech.vgmate.data.dto;

import com.ztstech.vgmate.data.beans.BaseRespBean;

/**
 * Created by dongdong on 2018/3/26.
 */

public class UploadProtocolData  extends BaseRespBean {

    /**
     * protocolMap : {"orgid":"552b3276e32a4626a3996edbb47ffedd","posterpicurl":"http://static.verygrow.com/bucea/image/20180327/1_171626186041.jpg,http://static.verygrow.com/bucea/image/20180327/1_171627196405.jpg","secretagreementpicurl":"http://static.verygrow.com/bucea/image/20180327/1_171610840715.jpg"}
     * status : 0
     */

    public ProtocolMapBean protocolMap;

    public static class ProtocolMapBean {
        /**
         * orgid : 552b3276e32a4626a3996edbb47ffedd
         * posterpicurl : http://static.verygrow.com/bucea/image/20180327/1_171626186041.jpg,http://static.verygrow.com/bucea/image/20180327/1_171627196405.jpg
         * secretagreementpicurl : http://static.verygrow.com/bucea/image/20180327/1_171610840715.jpg
         */
        public String oname;
        public String orgid;
        public String posterpicurl;
        public String secretagreementpicurl;
        public String promisebookpicurl;
        public String teamworkprotocalpicurl;
        public String teamworkprotocalstatus;
    }

}
