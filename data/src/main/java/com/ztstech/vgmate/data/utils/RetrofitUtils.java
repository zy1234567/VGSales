package com.ztstech.vgmate.data.utils;

import com.google.gson.Gson;
import com.ztstech.vgmate.data.constants.NetConstants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Emitter;
import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public class RetrofitUtils {

    public static <T> T createService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(clazz);
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
