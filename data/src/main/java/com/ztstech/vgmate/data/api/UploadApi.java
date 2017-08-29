package com.ztstech.vgmate.data.api;

import com.ztstech.vgmate.data.beans.BaseRespBean;
import com.ztstech.vgmate.data.beans.UploadImageBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

import static com.ztstech.vgmate.data.constants.NetConstants.UPLOAD_FILES;

/**
 * Created by zhiyuan on 2017/8/26.
 */

public interface UploadApi {


    /**
     * 上传文件
     * @param savetype 传"01"
     * @param files
     * @return
     */
    @Multipart
    @POST(UPLOAD_FILES)
    Observable<UploadImageBean> uploadFile(@Part("savetype") String savetype,
                                           @Part List<MultipartBody.Part> files);
}
