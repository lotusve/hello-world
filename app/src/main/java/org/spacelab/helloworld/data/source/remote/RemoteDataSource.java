package org.spacelab.helloworld.data.source.remote;

import android.util.Log;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.ApiService;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

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

        SSLContext sslContext = null;

        X509TrustManager x509TrustManager = null;

        try {
            sslContext = SSLContext.getInstance("TLS");

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
            e.printStackTrace();
        }


        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager).hostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-cn.faceplusplus.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);

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
    public void getData(GetDataCallback callback) {

        /*Log.d("llg-", "getData begin.");

        MediaType textType = MediaType.parse("text/plain");
        RequestBody api_key = RequestBody.create(textType, "LEgX_3o9-0f78adjCGx74JJ6Bqs30vKs");
        RequestBody api_secret = RequestBody.create(textType, "dIixyMLvWwRePplZU7gpz9At_ajBd50w");
        RequestBody return_attributes = RequestBody.create(textType, "gender,age");

        File imgFile = new File("/storage/emulated/0/baidu/searchbox/downloads/1578479381607.jpg");
        RequestBody imgRB = RequestBody.create(MediaType.parse("image/*"), imgFile);
        MultipartBody.Part imgPart = MultipartBody.Part.createFormData("image_file", imgFile.getName(), imgRB);

        Call<ResponseBean> call = apiService.getCall(api_key, api_secret, return_attributes, imgPart);

        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {

                Log.d("llg-", "onResponse.");

                ResponseBean bean = response.body();

                Log.d("llg-", bean.toString());

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

                Log.d("llg-", "onFailure", t);

            }
        });*/

    }

    @Override
    public void getData() {

        /*String url = "https://www.baidu.com/";

        Call<ResponseBean> call = apiService.getCall(url);

        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {

                Log.d("llg-2", "onResponse.");

                ResponseBean bean = response.body();

                Log.d("llg-2", bean.toString());

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

                Log.d("llg-2", "onFailure", t);

            }
        });*/

        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";

        String api_key = "LEgX_3o9-0f78adjCGx74JJ6Bqs30vKs";
        String api_secret = "dIixyMLvWwRePplZU7gpz9At_ajBd50w";
        String return_attributes = "gender,age";
        String image_url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578494493546&di=a08099ead320449cd910fe7f82ef0b49&imgtype=0&src=http%3A%2F%2Fimg.baizhan.net%2Fuploads%2Fallimg%2F160520%2F21_160520163111_1.jpg";

        Call<ResponseBean> call = apiService.getCall(api_key, api_secret, return_attributes, image_url);

        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {

                Log.d("llg-3", "onResponse.");

                ResponseBean bean = response.body();

                Log.d("llg-3", bean.toString());

            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {

                Log.d("llg-3", "onFailure", t);

            }
        });

    }

    @Override
    public void saveData() {

    }
}
