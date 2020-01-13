package org.spacelab.helloworld.data.source.remote.http.gallery;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiService {

    // 测试暂未通过
    @POST("facepp/v3/detect")
    @Multipart
    Call<ResponseBean> getCall(@Part("api_key") RequestBody api_key, @Part("api_secret") RequestBody api_secret, @Part("return_attributes") RequestBody return_attributes, @Part MultipartBody.Part image_file);

    @POST()
    Call<ResponseBean> getCall(@Url String url);

    // 测试通过
    @FormUrlEncoded
    @POST("facepp/v3/detect")
    Call<ResponseBean> getCall(@Field("api_key") String api_key, @Field("api_secret") String api_secret, @Field("return_attributes") String return_attributes, @Field("image_url") String image_url);

    // 测试通过
    @POST("facepp/v3/detect")
    @Multipart
    Call<ResponseBean> getCallDetect(@Part("api_key") RequestBody api_key, @Part("api_secret") RequestBody api_secret, @Part("return_attributes") RequestBody return_attributes, @Part("image_base64") RequestBody image_base64);


}
