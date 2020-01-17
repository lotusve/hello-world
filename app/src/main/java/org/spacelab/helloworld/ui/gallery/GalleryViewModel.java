package org.spacelab.helloworld.ui.gallery;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.spacelab.helloworld.Config.Config;
import org.spacelab.helloworld.data.source.DataRepository;
import org.spacelab.helloworld.data.source.DataSource;
import org.spacelab.helloworld.data.source.remote.http.gallery.Constant;
import org.spacelab.helloworld.data.source.remote.http.gallery.RequestBean;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<ResponseBean> mResponseBean;

    private DataRepository dataRepository;

    public GalleryViewModel(DataRepository repository) {
        mText = new MutableLiveData<>();
        mText.setValue("The face test.");

        mResponseBean = new MutableLiveData<>();

        dataRepository = repository;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<ResponseBean> getResponseBean() {
        return mResponseBean;
    }

    /**
     * 传入图片，获得分析结果数据
     * @param imageFilePath 图片文件路径
     */
    public void getData(String imageFilePath) {
        RequestBean bean = new RequestBean();
        bean.setApi_key(Constant.FACE_API_KEY);
        bean.setApi_secret(Constant.FACE_API_SECRET);
        bean.setReturn_attributes("gender,age,smiling,emotion,beauty,skinstatus");

        String image_base64 = getImageBase64String(imageFilePath);
        Log.d(Config.TAG, "image_base64: " + image_base64);
        bean.setImage_base64(image_base64);

        dataRepository.getData(bean, new DataSource.GetDataCallback() {
            @Override
            public void onDataLoaded(ResponseBean bean) {
                mResponseBean.setValue(bean);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    /**
     * 传入图片，获得分析结果数据
     * @param bitmap bitmap 图片
     */
    public void getData(Bitmap bitmap) {
        RequestBean bean = new RequestBean();
        bean.setApi_key(Constant.FACE_API_KEY);
        bean.setApi_secret(Constant.FACE_API_SECRET);
        bean.setReturn_attributes("gender,age,smiling,emotion,beauty,skinstatus");

        String image_base64 = getImageBase64String(bitmap);
        Log.d(Config.TAG, "image_base64: " + image_base64);
        bean.setImage_base64(image_base64);

        dataRepository.getData(bean, new DataSource.GetDataCallback() {
            @Override
            public void onDataLoaded(ResponseBean bean) {
                mResponseBean.setValue(bean);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    /**
     * 将 Bitmap 二进制内容，Base64 编码
     * @param imageFilePath bitmap 文件路径
     * @return
     */
    private String getImageBase64String(String imageFilePath) {
        String image_base64 = "";
        File imgFile = new File(imageFilePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imgFile);
            byte[] buffer = new byte[(int) imgFile.length()];
            fis.read(buffer);

            image_base64 = Base64.encodeToString(buffer, Base64.DEFAULT); // base64 加密
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

    /**
     * 将 Bitmap 二进制内容，Base64 编码
     * @param bitmap bitmap 图片
     * @return
     */
    private String getImageBase64String(Bitmap bitmap) {
        String image_base64 = "";
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            baos.flush();

            image_base64 = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT); // base64 加密
        } catch (Exception e) {
            Log.e(Config.TAG, e.getMessage(), e);
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                Log.e(Config.TAG, e.getMessage(), e);
            }
        }
        return image_base64;
    }

}