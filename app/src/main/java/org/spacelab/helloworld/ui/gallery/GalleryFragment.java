package org.spacelab.helloworld.ui.gallery;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.spacelab.helloworld.Config.Config;
import org.spacelab.helloworld.R;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.File;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);

        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        galleryViewModel.getResponseBean().observe(this, new Observer<ResponseBean>() {
            @Override
            public void onChanged(ResponseBean responseBean) {
                textView.setText(responseBean.toString());
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(Config.TAG, "GalleryFragment onResume.");

        getData();

    }

    private void getData() {

        File storageDirectory = Environment.getExternalStorageDirectory();

        String imageFilePath = "/baidu/searchbox/downloads/1578479381607.jpg";
        // String imageFilePath = "/DCIM/Camera/IMG_20200113_115331.jpg";

        galleryViewModel.getData(storageDirectory + imageFilePath);

    }
}