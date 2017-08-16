package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class LocationBean {


    /**
     * sid : 110000
     * sname : 北京市
     * city : [{"sid":"110100","sname":"市辖区","site":[{"sid":"110101","sname":"东城区"},{"sid":"110102","sname":"西城区"},{"sid":"110105","sname":"朝阳区"},{"sid":"110106","sname":"丰台区"},{"sid":"110107","sname":"石景山区"},{"sid":"110108","sname":"海淀区"},{"sid":"110109","sname":"门头沟区"},{"sid":"110111","sname":"房山区"},{"sid":"110112","sname":"通州区"},{"sid":"110113","sname":"顺义区"},{"sid":"110114","sname":"昌平区"},{"sid":"110115","sname":"大兴区"},{"sid":"110116","sname":"怀柔区"},{"sid":"110117","sname":"平谷区"},{"sid":"110118","sname":"密云区"},{"sid":"110119","sname":"延庆区"}]}]
     */

    private String sid;
    private String sname;
    private List<CityBean> city;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * sid : 110100
         * sname : 市辖区
         * site : [{"sid":"110101","sname":"东城区"},{"sid":"110102","sname":"西城区"},{"sid":"110105","sname":"朝阳区"},{"sid":"110106","sname":"丰台区"},{"sid":"110107","sname":"石景山区"},{"sid":"110108","sname":"海淀区"},{"sid":"110109","sname":"门头沟区"},{"sid":"110111","sname":"房山区"},{"sid":"110112","sname":"通州区"},{"sid":"110113","sname":"顺义区"},{"sid":"110114","sname":"昌平区"},{"sid":"110115","sname":"大兴区"},{"sid":"110116","sname":"怀柔区"},{"sid":"110117","sname":"平谷区"},{"sid":"110118","sname":"密云区"},{"sid":"110119","sname":"延庆区"}]
         */

        private String sid;
        private String sname;
        private List<SiteBean> site;

        private boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public List<SiteBean> getSite() {
            return site;
        }

        public void setSite(List<SiteBean> site) {
            this.site = site;
        }

        public static class SiteBean {
            /**
             * sid : 110101
             * sname : 东城区
             */

            private String sid;
            private String sname;

            private boolean selected;

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }
        }
    }
}
