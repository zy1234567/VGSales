package com.ztstech.vgmate.utils;

/**
 * Created by zhiyuan on 2017/9/17.
 * 翻译帮助类
 */

public class TransHelper {

    private String[] names;
    private String[] ids;

    public TransHelper(String[] names, String[] ids) {
        this.names = names;
        this.ids = ids;
    }

    public String getNameById(String id) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(id)) {
                return names[i];
            }
        }
        return null;
    }

    public String getIdByName(String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[1].equals(name)) {
                return names[i];
            }
        }
        return null;
    }
}
