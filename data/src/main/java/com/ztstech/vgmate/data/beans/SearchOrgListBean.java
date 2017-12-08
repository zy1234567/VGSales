package com.ztstech.vgmate.data.beans;

import java.util.List;

/**
 * Created by smm on 2017/12/8.
 */

public class SearchOrgListBean extends BaseRespBean{

    public PagerBean pager;
    public List<ListBean> list;

    public static class PagerBean {
        /**
         * totalRows : 1
         * pageSize : 10
         * currentPage : 1
         * totalPages : 1
         * startRow : 0
         */

        public int totalRows;
        public int pageSize;
        public int currentPage;
        public int totalPages;
        public int startRow;
    }

    public static class ListBean {}
}
