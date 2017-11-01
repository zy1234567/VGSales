package com.ztstech.vgmate.utils;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/31.
 */

public class StringUtils {

    /**
     * 将list按照一定分割符号拼接
     * @param orign
     * @param separator
     * @return
     */
    public static String concatWith(List<String> orign, String separator) {
        StringBuilder sb = new StringBuilder();
        for (String str : orign) {
            sb.append(str);
            sb.append(separator);
        }
        int length = sb.length();
        if (length != 0) {
            return sb.toString().substring(0, length - separator.length());
        }else {
            return "";
        }
    }
}
