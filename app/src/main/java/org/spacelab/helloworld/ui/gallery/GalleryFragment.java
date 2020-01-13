package org.spacelab.helloworld.ui.gallery;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import org.spacelab.helloworld.R;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;

import java.io.File;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private GalleryViewModel galleryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        initView(root);

        return root;
    }

    private void initView(View root) {
        final TextView textView = root.findViewById(R.id.text_gallery);
        final ImageView imageView = root.findViewById(R.id.image);
        final TextView genderTV = root.findViewById(R.id.gender);
        final TextView ageTV = root.findViewById(R.id.age);

        final TextView smileTV = root.findViewById(R.id.smile);
        final TextView emotionTV = root.findViewById(R.id.emotion);
        final TextView beautyTV = root.findViewById(R.id.beauty);
        final TextView skinstatusTV = root.findViewById(R.id.skinstatus);

        imageView.setOnClickListener(this);

        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        galleryViewModel.getResponseBean().observe(this, new Observer<ResponseBean>() {
            @Override
            public void onChanged(ResponseBean responseBean) {

                ResponseBean.Face face = responseBean.getFaces().get(0);

                Glide.with(GalleryFragment.this).load(new File(getImageFilePath())).into(imageView);

                genderTV.setText(String.format(getResources().getString(R.string.gender), face.getAttributes().getGender().getValue()));
                ageTV.setText(String.format(getResources().getString(R.string.age), face.getAttributes().getAge().getValue()));

                smileTV.setText(String.format(getResources().getString(R.string.smile), String.valueOf(face.getAttributes().getSmile().getValue())));

                ResponseBean.Emotion emotion = face.getAttributes().getEmotion();
                emotionTV.setText(String.format(getResources().getString(R.string.emotion), String.valueOf(emotion.getAnger()), String.valueOf(emotion.getDisgust()), String.valueOf(emotion.getFear()), String.valueOf(emotion.getHappiness()), String.valueOf(emotion.getNeutral()), String.valueOf(emotion.getSadness()), String.valueOf(emotion.getSurprise())));

                ResponseBean.Beauty beauty = face.getAttributes().getBeauty();
                beautyTV.setText(String.format(getResources().getString(R.string.beauty), String.valueOf(beauty.getMale_score()), String.valueOf(beauty.getFemale_score())));

                ResponseBean.Skinstatus skinstatus = face.getAttributes().getSkinstatus();
                skinstatusTV.setText(String.format(getResources().getString(R.string.skinstatus), String.valueOf(skinstatus.getHealth()), String.valueOf(skinstatus.getStain()), String.valueOf(skinstatus.getAcne()), String.valueOf(skinstatus.getDark_circle())));

            }
        });

    }

    private void getData() {
        galleryViewModel.getData(getImageFilePath());
    }

    private String getImageFilePath (){

        File storageDirectory = Environment.getExternalStorageDirectory();

        String imageFilePath = "/baidu/searchbox/downloads/1578479381607.jpg";
        // String imageFilePath = "/DCIM/Camera/IMG_20200113_115331.jpg";

        return storageDirectory + imageFilePath;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.image:

                getData();

                break;
            default:
                break;
        }

    }
}