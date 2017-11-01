package com.ztstech.vgmate.utils;

/**
 * Created by zhiyuan on 2017/9/17.
 */

public class SexUtils {

    public static final String[] NAME = new String[] {"男", "女"};
    public static final String[] ID = new String[] {"01", "02"};

    private static TransHelper helper;

    static {
        helper = new TransHelper(NAME, ID);
    }

    public static String getNameById(String id) {
        return helper.getNameById(id);
    }

    public static String getIdByName(String name) {
        return helper.getIdByName(name);
    }

}
