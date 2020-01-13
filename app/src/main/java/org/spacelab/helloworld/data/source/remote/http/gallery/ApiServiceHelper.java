package org.spacelab.helloworld.data.source.remote.http.gallery;

import android.util.Log;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.spacelab.helloworld.Config.Config;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceHelper {

    public static ApiService getApiService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.FACE_BASE_URL) // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .client(getOkHttpClient())
                .build();

        return retrofit.create(ApiService.class);
    }

    private static OkHttpClient getOkHttpClient() {
        SSLContext sslContext = null;
        X509TrustManager x509TrustManager = null;
        try {
            sslContext = SSLContext.getInstance(Constant.PROTOCOL);
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

}
