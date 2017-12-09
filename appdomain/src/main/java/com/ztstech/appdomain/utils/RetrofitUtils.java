package com.ztstech.appdomain.utils;

import com.google.gson.Gson;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.api.UploadApi;
import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.data.beans.UploadImageBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.appdomain.repository.UserRepository;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by zhiyuan on 2017/7/27.
 */

public class RetrofitUtils {

    private static OkHttpClient httpClient;
    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging)
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS).build();
    }

    public static <T> T createService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(clazz);
    }

    /** 扫码登录的两个接口用的是这个请求地址*/
    public static <T> T createQrcodeService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_QRCODE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(clazz);
    }

    /**
     * 上传文件
     * @return
     */
    public static Observable<UploadImageBean> uploadFile(File[] files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.length);
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("file"), file);

            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(),
                    requestBody);
            parts.add(part);

        }
        return createService(UploadApi.class).uploadFile("01", parts,UserRepository.getInstance().getAuthId());
    }

    /**
     * 如果文件存在就上传，否则直接回调，url为""
     * @param files 文件列表
     * @return
     *
     */
    public static Observable<UploadImageBean> uploadIfExist(File[] files) {
        if (files == null || files.length == 0) {
            return new Observable<UploadImageBean>() {
                @Override
                protected void subscribeActual(Observer<? super UploadImageBean> observer) {
                    UploadImageBean fakeResult = new UploadImageBean();
                    fakeResult.status = NetConstants.STATUS_SUCCEED;
                    fakeResult.errmsg = null;
                    fakeResult.suourls = "";
                    fakeResult.urls = "";
                    observer.onNext(fakeResult);
                    observer.onComplete();
                }
            };
        }else {
            return uploadFile(files);
        }
    }

    /**
     * 创建分享
     * @return
     */
    public static Observable<BaseRespBean> createShare(CreateShareData createShareData) {
        CreateShareApi createShareApi = RetrofitUtils.createService(CreateShareApi.class);
        return createShareApi.createShare(
                createShareData.title,
                createShareData.summary,
                createShareData.contentpicurl,
                createShareData.contentpicsurl,
                createShareData.picurl,
                createShareData.picsurl,
                createShareData.picdescribe,
                createShareData.type,
                createShareData.url,
                UserRepository.getInstance().getAuthId());
    }

    /**
     * 重新发送分享
     * @param nid
     * @return
     */
    public static Observable<BaseRespBean> resendShare(String nid) {
        CreateShareApi createShareApi = RetrofitUtils.createService(CreateShareApi.class);
        return createShareApi.resendShare(nid,
                UserRepository.getInstance().getAuthId());
    }

    /**
     * 编辑分享
     * @return
     */
    public static Observable<BaseRespBean> editShare(CreateShareData createShareData) {
        CreateShareApi createShareApi = RetrofitUtils.createService(CreateShareApi.class);
        return createShareApi.editShare(
                createShareData.nid,
                createShareData.title,
                createShareData.summary,
                createShareData.contentpicurl,
                createShareData.contentpicsurl,
                createShareData.picurl,
                createShareData.picsurl,
                createShareData.picdescribe,
                createShareData.type,
                createShareData.url,
                UserRepository.getInstance().getAuthId());
    }




}
