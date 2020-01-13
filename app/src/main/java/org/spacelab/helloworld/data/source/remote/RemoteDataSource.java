package org.spacelab.helloworld.data.source.remote;

import android.util.Base64;
import android.util.Log;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.ApiService;
import org.spacelab.helloworld.data.source.remote.http.gallery.Config;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private static RemoteDataSource INSTANCE;

    private ApiService apiService;

    private RemoteDataSource() {
        initApiService();
    }

    private void initApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.FACE_BASE_URL) // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .client(getOkHttpClient())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private OkHttpClient getOkHttpClient() {
        SSLContext sslContext = null;
        X509TrustManager x509TrustManager = null;
        try {
            sslContext = SSLContext.getInstance(Config.PROTOCOL);
            x509TrustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            TrustManager[] trustAllCerts = new TrustManager[]{x509TrustManager};
            sslContext.init(null, trustAllCerts, new SecureRandom());
        } catch (Exception e) {
            Log.e(Config.TAG, e.getMessage(), e);
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                .hostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS)
                .build();

        return client;
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
    public void getData(GetDataCallback callback, String imageFilePath) {

        Log.d(Config.TAG, "request begin.");

        // getDataByUploadFile();

        getDatabyUploadFileString(imageFilePath);

    }

    private void getDatabyUploadFileString(String imageFilePath) {

        MediaType textType = MediaType.parse("text/plain");
        RequestBody api_key = RequestBody.create(textType, Config.FACE_API_KEY);
        RequestBody api_secret = RequestBody.create(textType, Config.FACE_API_SECRET);
        RequestBody return_attributes = RequestBody.create(textType, "gender,age");

        String image_base64 = getImageBase64String(imageFilePath);
        Log.d(Config.TAG, "image_base64: " + image_base64);
        RequestBody image = RequestBody.create(textType, image_base64);

        Call<ResponseBean> call = apiService.getCallDetect(api_key, api_secret, return_attributes, image);

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

    private String getImageBase64String(String imageFilePath) {

        String image_base64 = "";

        File imgFile = new File(imageFilePath);

        FileInputStream fis = null;

        try {

            fis = new FileInputStream(imgFile);

            byte[] buffer = new byte[(int) imgFile.length()];

            fis.read(buffer);

            image_base64 = Base64.encodeToString(buffer, Base64.DEFAULT);

        } catch (Exception e) {
            Log.e(Config.TAG, e.getMessage(), e);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }
        return image_base64;
    }

    private void getDataByUploadFile() {

        MediaType textType = MediaType.parse("text/plain");
        RequestBody api_key = RequestBody.create(textType, Config.FACE_API_KEY);
        RequestBody api_secret = RequestBody.create(textType, Config.FACE_API_SECRET);
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

    @Override
    public void getData() {

        Log.d(Config.TAG, "request begin.");

        String return_attributes = "gender,age";
        String image_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578494493546&di=a08099ead320449cd910fe7f82ef0b49&imgtype=0&src=http%3A%2F%2Fimg.baizhan.net%2Fuploads%2Fallimg%2F160520%2F21_160520163111_1.jpg";

        Call<ResponseBean> call = apiService.getCall(Config.FACE_API_KEY, Config.FACE_API_SECRET, return_attributes, image_url);

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
}
