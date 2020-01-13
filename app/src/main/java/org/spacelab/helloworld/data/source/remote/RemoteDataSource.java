package org.spacelab.helloworld.data.source.remote;

import android.util.Log;

import org.spacelab.helloworld.Config.Config;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.ApiService;
import org.spacelab.helloworld.data.source.remote.http.gallery.ApiServiceHelper;
import org.spacelab.helloworld.data.source.remote.http.gallery.Constant;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    private ApiService apiService;

    private RemoteDataSource() {
        apiService = ApiServiceHelper.getApiService();
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getData(RequestBean bean, GetDataCallback callback) {

        getDataByImageBase64(bean, callback);

    }

    private void getDataByImageBase64(RequestBean bean, final GetDataCallback callback) {

        MediaType textType = MediaType.parse("text/plain");

        RequestBody api_key = RequestBody.create(textType, bean.getApi_key());
        RequestBody api_secret = RequestBody.create(textType, bean.getApi_secret());
        RequestBody return_attributes = RequestBody.create(textType, bean.getReturn_attributes());
        RequestBody image = RequestBody.create(textType, bean.getImage_base64());

        Call<ResponseBean> call = apiService.getCallDetect(api_key, api_secret, return_attributes, image);

        Log.d(Config.TAG, "request begin.");
        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                Log.d(Config.TAG, "onResponse.");
                ResponseBean bean = response.body();
                Log.d(Config.TAG, bean.toString());
                callback.onDataLoaded(bean);
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                Log.e(Config.TAG, "onFailure", t);
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void getData(String imageUrl) {

        Log.d(Config.TAG, "request begin.");

        String return_attributes = "gender,age";

        String image_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578494493546&di=a08099ead320449cd910fe7f82ef0b49&imgtype=0&src=http%3A%2F%2Fimg.baizhan.net%2Fuploads%2Fallimg%2F160520%2F21_160520163111_1.jpg";

        Call<ResponseBean> call = apiService.getCall(Constant.FACE_API_KEY, Constant.FACE_API_SECRET, return_attributes, image_url);

        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                Log.d(Config.TAG, "onResponse.");
                ResponseBean bean = response.body();
                Log.d(Config.TAG, bean.toString());
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                Log.e(Config.TAG, "onFailure", t);
            }
        });

    }

    @Override
    public void saveData() {

    }

    /**
     * 测试不通过
     */
    private void getDataByUploadFile() {

        MediaType textType = MediaType.parse("text/plain");
        RequestBody api_key = RequestBody.create(textType, Constant.FACE_API_KEY);
        RequestBody api_secret = RequestBody.create(textType, Constant.FACE_API_SECRET);
        RequestBody return_attributes = RequestBody.create(textType, "gender,age");

        File imgFile = new File("/storage/emulated/0/baidu/searchbox/downloads/1578479381607.jpg");

        Log.d(Config.TAG, "image file: " + imgFile.getAbsolutePath());

        RequestBody imgRB = RequestBody.create(MediaType.parse("image/jpg"), imgFile);
        // RequestBody imgRB = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image_file", imgFile.getName(), imgRB);

        Call<ResponseBean> call = apiService.getCall(api_key, api_secret, return_attributes, imgPart);

        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                Log.d(Config.TAG, "onResponse.");
                ResponseBean bean = response.body();
                Log.d(Config.TAG, bean.toString());
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                Log.e(Config.TAG, "onFailure", t);
            }
        });
    }

}
