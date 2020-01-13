package org.spacelab.helloworld.ui.gallery;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private MutableLiveData<ResponseBean> mResponseBean;

    private DataRepository dataRepository;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("The face test.");

        mResponseBean = new MutableLiveData<>();

        dataRepository = DataRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<ResponseBean> getResponseBean() {
        return mResponseBean;
    }

    public void getData(String imageFilePath){

        RequestBean bean = new RequestBean();
        bean.setApi_key(Constant.FACE_API_KEY);
        bean.setApi_secret(Constant.FACE_API_SECRET);
        bean.setReturn_attributes("gender,age");

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

}