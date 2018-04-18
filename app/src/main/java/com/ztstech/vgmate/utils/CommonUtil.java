package com.ztstech.vgmate.utils;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.renderscript.Element;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseApplicationLike;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by smm on 2017/5/21.
 * 一些常用的方法工具类
 */

public class CommonUtil {

    /**
     * 动态设置ListView的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }



    /** 
          * 计算从startColor过度到endColor过程中百分比为franch时的颜色值 
          * @param startColor 起始颜色 （格式#FFFFFFFF） 
          * @param endColor 结束颜色 （格式#FFFFFFFF） 
          * @param franch 百分比0.5 
          * @return 返回String格式的color（格式#FFFFFFFF） 
          */

    public static String caculateColor(String startColor, String endColor,
                                       float franch) {
        int startAlpha = Integer.parseInt(startColor.substring(1, 3), 16);
        int startRed = Integer.parseInt(startColor.substring(3, 5), 16);
        int startGreen = Integer.parseInt(startColor.substring(5, 7), 16);
        int startBlue = Integer.parseInt(startColor.substring(7), 16);

        int endAlpha = Integer.parseInt(endColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(3, 5), 16);
        int endGreen = Integer.parseInt(endColor.substring(5, 7), 16);
        int endBlue = Integer.parseInt(endColor.substring(7), 16);

        int currentAlpha = (int) ((endAlpha - startAlpha) * franch + startAlpha);
        int currentRed = (int) ((endRed - startRed) * franch + startRed);
        int currentGreen = (int) ((endGreen - startGreen) * franch + startGreen);
        int currentBlue = (int) ((endBlue - startBlue) * franch + startBlue);

        return "#" + getHexString(currentAlpha) + getHexString(currentRed)
                + getHexString(currentGreen) + getHexString(currentBlue);
    }

    public static String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    /**
     * 传入一个数值如果这个数小于小数点后没有六位数则补全六位数返回
     */
    public static String getAfterPointSixNum(double num){
        String numStr = String.valueOf(num);
        if (!numStr.isEmpty()){
            String[] arr = numStr.split("\\.");
            if (arr == null || arr.length < 2){
                return "";
            }else {           //后台需要小数点后六位，小数点后不够6位需要补零
                if (arr[1].length() < 6){
                    arr[1] = arr[1] + "000000";
                    arr[1] = arr[1].substring(0,6);
                }else {
                    arr[1] = arr[1].substring(0,6);
                }
                numStr = arr[0]+"."+arr[1];
            }
        }
        return numStr;
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = BaseApplicationLike.getApplicationInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(BaseApplicationLike.getApplicationInstance()
                    .getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /***
     * 从assets文件中获得Json串
     */

    public static String getDataFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
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

    /**
     * 获取应用进程名
     * @param pID
     * @param context
     * @return
     */
    public static String getAppName(int pID, Context context) {
        String processName = null;
        ActivityManager am = (ActivityManager) context
                .getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
                    .next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm
                            .getApplicationInfo(info.processName,
                                    PackageManager.GET_META_DATA));
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
            }
        }
        return processName;
    }

    /**
     * 根据生日计算年龄
     */
    public static String calculateAgeByBirth(String birth){
        if (birth == null || birth.length() < 4){
            return "";
        }
        try {
            String nowYear = TimeUtils.getDateWithFormater("yyyy");
            String birthday = birth.substring(0,4);
            int age = Integer.parseInt(nowYear) - Integer.parseInt(birthday);
            return String.valueOf(age);
        }catch (Exception e){
            return "";
        }

    }

    /**
     * 根据年龄计算出生年份
     */
    public static String calculateBirthByAge(String age){
        if (age == null || age.isEmpty()){
            return "";
        }
        try {
            String nowYear = TimeUtils.getDateWithFormater("yyyy");
            int birth = Integer.parseInt(nowYear) - Integer.parseInt(age);
            return String.valueOf(birth);
        }catch (Exception e){
            return "18";
        }

    }

    /**
     * 获取设备的机型
     */
    public static String getPhoneModel(){
        return android.os.Build.MODEL;
    }

    /**
     * bitmap转file
     * @param bitmap
     * @param fileName
     * @return
     */
    public static File bitmapToFile(Bitmap bitmap, String fileName){
        File file = new File(Environment.getExternalStorageDirectory(), "/ztstechDown/" + fileName);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 将米转化为千米并保留后两位
     */
    public static String mToKm(double distance){
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
            double km = distance / 1000;
            return df.format(km).toString();
        }catch (Exception e){
            return "99";
        }

    }

    /**
     * 对图片进行高斯模糊处理
     * @param sentBitmap
     * @param radius
     * @return
     */
    public static Bitmap blur(Bitmap sentBitmap, int radius) {

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    /**
     * 高斯模糊转化
     * @param bitmap
     * @param radius
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Bitmap bitmap, float radius, Context context) {
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        android.renderscript.RenderScript rs = android.renderscript.RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        android.renderscript.ScriptIntrinsicBlur blurScript = android.renderscript.ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        android.renderscript.Allocation allIn = android.renderscript.Allocation.createFromBitmap(rs, bitmap);
        android.renderscript.Allocation allOut = android.renderscript.Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(radius);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        //        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
//        view.setBackground(new BitmapDrawable(context.getResources(), outBitmap));
        rs.destroy();

        return outBitmap;
    }

    public static void copy(String text, Context context) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(text);
    }



    /**
     * 读取照片exif信息中的旋转角度
     * @param path 照片路径
     * @return角度
     */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     *
     * @param img
     * @return
     */
    public static Bitmap toturn(Bitmap img){
        Matrix matrix = new Matrix();
        matrix.postRotate(+90); /*翻转90度*/
        int width = img.getWidth();
        int height =img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }


    /**
     * 获取联系人以及手机号
     * @param uri
     * @return
     */
    public static String[] getPhoneContacts(Context context, Uri uri){
        String[] contact=new String[2];
        //ContentProvider展示数据类似一个单个数据库表
        //ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
        ContentResolver cr = context.getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            //获得DATA表中的名字
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            //条件为联系人ID
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
            // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        }
        else
        {
            return null;
        }
        return contact;
    }

    /**
     *  启动应用的设置
     * @since 2.5.0
     */
    public static void startAppSettings(Context context, String packageName) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }


    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public static int getAppStatus(Context context, String pageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);
        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }

    /**
     * 获得栈中最顶层的Activity
     *
     * @param context
     * @return
     */
    public static String getTopActivity(Context context) {
        android.app.ActivityManager manager = (android.app.ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            return (runningTaskInfos.get(0).topActivity).toString();
        } else
            return null;
    }


    /**
     * 开始播放模式选择动画
     *
     * @param view       目标View
     * @param isSelected 是否选择
     */
    public static void startModeSelectAnimation(View view, boolean isSelected) {
        RotateAnimation rotate;
        if (isSelected) {
            rotate = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            rotate = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        rotate.setFillAfter(true);
        rotate.setDuration(200);
        view.startAnimation(rotate);
    }

    /**
     * 播放扫码成功的声音
     */
    public static void startCodeSuccessMusic(Context context){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.code);
        mediaPlayer.start();
    }

    /**
     * 月日个位数的不带0填上0
     */
    public static String handleZero(String data) {
        StringBuffer result = new StringBuffer();
        if (data.length() == 1 && !data.equals("0")) {
            result.append("0");

        }
        result.append(data);
        return result.toString();
    }

    /**
     * 将图片地址转化为list（传入到图片浏览器时需要）
     * @return
     */
    public static List<String> imgUrlsToList(@NonNull String picurls){
        List<String> list = new ArrayList<>();
        String[] array = picurls.split(",");
        if(array.length > 0){
            for (int i = 0; i < array.length; i++){
                list.add(array[i]);
            }
        }
        return list;
    }
    /**
     * 数组转list
     */
    public static List arraytolist(String[] str,List list){
        if (str.length == 0){
            return null;
        }
        for (int i = 0; i < str.length;i++){
            list.add(str[i]);
        }
        return list;
    }
    //判断机构来源类型
    public static void orgfFromType(Context context,TextView textView,String cstatus,String nowchancetype,
                                    String chancetype){
        /**
         * web登记
         * cststus 15 && nowchancetype == null && chancetype （01 || 05 || 07 || 09）
         */
        if (TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) &&
                TextUtils.isEmpty(nowchancetype) &&
                (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_REGISTER) ||
                        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_CHECK_IN) ||
                        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_PASSER_CHECK_IN) ||
                        TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_SALE_CHECK_IN))){
            textView.setText("web登记");
            textView.setTextColor(context.getResources().getColor(R.color.color_102));
            return;
        }
        /**
         * web认领
         * (cststus 15 && nowchancetype == null && chancetype(03)) ||
         * (cststus 15 && nowchancetype != null && nowchancetype (03))
         */
        if ((TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) && TextUtils.isEmpty(nowchancetype)
                && TextUtils.equals(chancetype,Constants.CHANCE_TYPE_WEB_ORG_CLAIM)) ||
                (TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) &&
                        !TextUtils.isEmpty(nowchancetype) && TextUtils.equals(nowchancetype,Constants.NOW_CHANCE_TYPE_WEB_CLAIM))){
            textView.setText("web认领");
            textView.setTextColor(context.getResources().getColor(R.color.color_102));
            return;
        }
        /**
         * app登记
         * cststus 15 && nowchancetype == null && chancetype(02 || 06 || 08 || 10)
         */
        if (TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) &&
                TextUtils.isEmpty(nowchancetype) && (TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_REGISTER)
                || TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_CHECK_IN) ||
                TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_PASSER_CHECK_IN))||
                TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_SALE_CHECK_IN)){
            textView.setText("app登记");
            textView.setTextColor(context.getResources().getColor(R.color.color_102));
            return;
        }
        /**
         * app认领
         * (cststus 15 && nowchancetype == null && chancetype(04)) ||
         * (cststus 15 && nowchancetype != null && nowchancetype (04))
         */
        if ((TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) && TextUtils.isEmpty(nowchancetype)
                && TextUtils.equals(chancetype,Constants.CHANCE_TYPE_APP_ORG_CLAIM)) ||
                (TextUtils.equals(cstatus,Constants.CSTATUS_ALREADY_CLAIM) &&
                        !TextUtils.isEmpty(nowchancetype) &&
                        TextUtils.equals(nowchancetype,Constants.NOW_CHANCE_TYPE_APP_CLAIM))){
            textView.setText("app认领");
            textView.setTextColor(context.getResources().getColor(R.color.color_102));
            return;
        }
        /**
         * cstatus 14 机构认领审核
         */
        if (TextUtils.equals(cstatus, Constants.CSTATUS_ORG_CLAIM_ING)) {
            textView.setText("机构认领审核");
            textView.setTextColor(context.getResources().getColor(R.color.color_006));
            return;
        }
        /**
         * cstatus 11 机构登记审核
         */
         if (TextUtils.equals(cstatus,Constants.CSTATUS_GRAY_UNVERIFIED)){
            textView.setText("机构登记审核");
            textView.setTextColor(context.getResources().getColor(R.color.color_006));
            return;
        }
    }
}
