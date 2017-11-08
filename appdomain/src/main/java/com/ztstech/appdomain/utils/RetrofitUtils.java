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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class RetrofitUtils {

    private static OkHttpClient httpClient;
    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
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
        return createService(UploadApi.class).uploadFile("savetype", parts);
    }

    /**
     * 如果文件存在就上传，否则直接回调，url为""
     * @param files 文件列表
     * @return
     */
    public static Observable<UploadImageBean> uploadIfExist(File[] files) {
        if (files == null || files.length == 0) {
            return new Observable<UploadImageBean>(new Observable.OnSubscribe<UploadImageBean>() {
                @Override
                public void call(Subscriber<? super UploadImageBean> subscriber) {
                    UploadImageBean fakeResult = new UploadImageBean();
                    fakeResult.status = NetConstants.STATUS_SUCCEED;
                    fakeResult.errmsg = null;
                    fakeResult.suourls = "";
                    fakeResult.urls = "";
                    subscriber.onNext(fakeResult);
                    subscriber.onCompleted();
                }
            }){};
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



    /**
     * 创建测试接口
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createTestService(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, final Method method, Object[] objects) throws Throwable {
                return  rx.Observable.create(new Action1<Emitter<Object>>() {
                    @Override
                    public void call(Emitter<Object> emitter) {
                        try {
                            //线程阻塞200mm，模拟请求耗时
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        int randomInt = new Random().nextInt(10) + 1;
//                        if (randomInt == 6) {
//                            //随机数为6，模拟失败
//                            emitter.onError(new Throwable("模拟请求失败"));
//                        }else {
                        //获取Observable中的泛型类型
                        Type type = ((ParameterizedType)method.getGenericReturnType())
                                .getActualTypeArguments()[0];
                        //生成实例化对象
                        emitter.onNext(new Gson().fromJson("{}", type));
//                        }
                        emitter.requested();
                        emitter.onCompleted();
                    }
                }, Emitter.BackpressureMode.NONE);
            }
        });
    }

}
