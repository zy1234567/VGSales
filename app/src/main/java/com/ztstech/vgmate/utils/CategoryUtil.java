package com.ztstech.vgmate.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ztstech.vgmate.base.BaseApplication;
import com.ztstech.vgmate.data.beans.CategoriesBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhiyuan on 2017/9/2.
 */

public class CategoryUtil {

    /* 父类别map */
    private static Map<String, CategoriesBean> fatCategoryMaps;
    /* 子类别map*/
    private static Map<String, CategoriesBean.HobbiesBean> subCategoryMaps;
    /* <id,name> */
    private static Map<String, String> categoryMaps;
    /* <name,id> */
    private static Map<String, String> categoryMaps2;

    static {
        fatCategoryMaps = new HashMap<>();
        subCategoryMaps = new HashMap<>();
        categoryMaps = new HashMap<>();
        categoryMaps2 = new HashMap<>();

        List<CategoriesBean> categoryBeans = new Gson().fromJson(getJsonFromAssets("categories.txt"), new TypeToken<List<CategoriesBean>>() {
        }.getType());

        for (CategoriesBean fatBean : categoryBeans) {
            fatCategoryMaps.put(fatBean.getLid(), fatBean);
            categoryMaps.put(fatBean.getLid(), fatBean.getLname());
            categoryMaps2.put(fatBean.getLname(), fatBean.getLid());

            for (CategoriesBean.HobbiesBean subBean : fatBean.getHobbies()) {
                subCategoryMaps.put(subBean.getLid(), subBean);
                categoryMaps.put(subBean.getLid(), subBean.getLname());
                categoryMaps2.put(subBean.getLname(), subBean.getLid());
            }
        }
    }

    /**
     * 根据分类id获取对应的分类名称
     *
     * @param cateId
     * @return String
     */
    public static String getCategoryName(String cateId) {

        return categoryMaps.get(cateId);
    }

    /**
     * 根据id获取名称
     * @param cateIds 以,隔开的多个id
     * @return
     */
    public static String getCategoryNames(String cateIds) {
        if (TextUtils.isEmpty(cateIds)) {
            return cateIds;
        }
        String[] ids = cateIds.split(",");
        StringBuilder sb = new StringBuilder();
        for (String id : ids) {
            sb.append(categoryMaps.get(id));
            sb.append("、");
        }
        String result = sb.toString();
        if (result.length() != 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 根据分类名称获取对应的分类id
     *
     * @param cateName
     * @return String
     */
    public static String getCategoryId(String cateName) {
        return categoryMaps2.get(cateName);
    }

    /**
     * 查看本地分类中是否包含此分类ID
     *
     * @param cateId
     * @return boolean
     */
    public static boolean containsId(String cateId) {
        return categoryMaps.containsKey(cateId);
    }

    /**
     * 查看本地分类中是否包含此分类名称
     *
     * @param cateName
     * @return boolean
     */
    public static boolean containsName(String cateName) {
        return categoryMaps.containsValue(cateName);
    }

    /**
     * 根据父类别id获取父类对象
     *
     * @param cateId
     * @return CategoriesBean
     */
    public static CategoriesBean getFatherCategoryById(String cateId) {
        return fatCategoryMaps.get(cateId);
    }

    /**
     * 根据子类别id获取子类对象
     *
     * @param cateId
     * @return CategoriesBean.HobbiesBean
     */
    public static CategoriesBean.HobbiesBean getChildCategoryById(String cateId) {
        return subCategoryMaps.get(cateId);
    }

    /**
     * 根据父类id获取子类对象
     *
     * @param cateId
     * @return List<CategoriesBean.HobbiesBean> 子类对象list
     */
    public static List<CategoriesBean.HobbiesBean> getChildCategoryByFatherId(String cateId) {
        return getFatherCategoryById(cateId).getHobbies();
    }

    /**
     * 根据子类id获取父类对象
     *
     * @param cateId
     * @return CategoriesBean 父类对象
     */
    public static CategoriesBean getFatherCategoryByChildId(String cateId) {
        if(subCategoryMaps.containsKey(cateId)){
            return getFatherCategoryById(getChildCategoryById(cateId).getFlid());
        }
        return null;
    }

    /**
     * 根据类别id获取类别对象
     *
     * @param cateId
     * @return Object
     */
    public static Object getCategoryById(String cateId) {
        if (fatCategoryMaps.containsKey(cateId)) {
            return fatCategoryMaps.get(cateId);
        }
        if (subCategoryMaps.containsKey(cateId)) {
            return subCategoryMaps.get(cateId);
        }
        return null;
    }


    /**
     * 从assets文件中获得Json串
     *
     * @param fileName
     * @return
     */
    public static String getJsonFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(BaseApplication.getApplicationInstance()
                    .getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
