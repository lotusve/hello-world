package org.spacelab.helloworld.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import org.spacelab.helloworld.Config.Config;
import org.spacelab.helloworld.R;
import org.spacelab.helloworld.component.ViewModelFactory;
import org.spacelab.helloworld.data.source.remote.http.gallery.ResponseBean;
import org.spacelab.helloworld.util.FileUtil;
import org.spacelab.helloworld.util.ImageSelectUtil;

import java.io.IOException;

/**
 * 人脸识别
 */
public class GalleryFragment extends Fragment implements View.OnClickListener {

    private Activity activity;

    private View root;

    private ImageView imageView;

    private GalleryViewModel galleryViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_gallery, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        obtainViewModel();
        initView();
    }

    private void obtainViewModel() {

        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplicationContext());

        galleryViewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel.class);
    }

    private void initView() {
        imageView = root.findViewById(R.id.image);
        imageView.setOnClickListener(this);

        final TextView textView = root.findViewById(R.id.text_gallery);
        final TextView genderTV = root.findViewById(R.id.gender);
        final TextView ageTV = root.findViewById(R.id.age);
        final TextView smileTV = root.findViewById(R.id.smile);
        final TextView emotionTV = root.findViewById(R.id.emotion);
        final TextView beautyTV = root.findViewById(R.id.beauty);
        final TextView skinstatusTV = root.findViewById(R.id.skinstatus);

        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        galleryViewModel.getResponseBean().observe(this, new Observer<ResponseBean>() {
            @Override
            public void onChanged(ResponseBean responseBean) {

                try {
                    ResponseBean.Face face = responseBean.getFaces().get(0);

                    genderTV.setText(String.format(getResources().getString(R.string.gender), face.getAttributes().getGender().getValue()));
                    ageTV.setText(String.format(getResources().getString(R.string.age), face.getAttributes().getAge().getValue()));

                    smileTV.setText(String.format(getResources().getString(R.string.smile), String.valueOf(face.getAttributes().getSmile().getValue())));

                    ResponseBean.Emotion emotion = face.getAttributes().getEmotion();
                    emotionTV.setText(String.format(getResources().getString(R.string.emotion), String.valueOf(emotion.getAnger()), String.valueOf(emotion.getDisgust()), String.valueOf(emotion.getFear()), String.valueOf(emotion.getHappiness()), String.valueOf(emotion.getNeutral()), String.valueOf(emotion.getSadness()), String.valueOf(emotion.getSurprise())));

                    ResponseBean.Beauty beauty = face.getAttributes().getBeauty();
                    beautyTV.setText(String.format(getResources().getString(R.string.beauty), String.valueOf(beauty.getMale_score()), String.valueOf(beauty.getFemale_score())));

                    ResponseBean.Skinstatus skinstatus = face.getAttributes().getSkinstatus();
                    skinstatusTV.setText(String.format(getResources().getString(R.string.skinstatus), String.valueOf(skinstatus.getHealth()), String.valueOf(skinstatus.getStain()), String.valueOf(skinstatus.getAcne()), String.valueOf(skinstatus.getDark_circle())));

                } catch (Exception e) {
                    Log.e(Config.TAG, e.getMessage(), e);
                }
            }
        });

    }

    /**
     * 获取人脸识别的的结果
     * @param imageFilePath 人脸图片绝对路径
     */
    private void getData(String imageFilePath) {
        galleryViewModel.getData(imageFilePath);
    }

    private void getData(Bitmap bitmap) {
        galleryViewModel.getData(bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:

                // ImageSelectUtil.pickImageFromLocal(this);

                // ImageSelectUtil.pickAndCropSmallBitmap(this);

                // ImageSelectUtil.pickAndCropBigBitmap(this, activity);

                ImageSelectUtil.startImageCapture(this, activity);

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case ImageSelectUtil.REQUEST_PICK_IMAGE:

                Uri imageUri = data.getData();
                Log.d(Config.TAG, "imageUri: " + imageUri);

                updateImageView(imageUri);

                String imagePath = FileUtil.getPath(activity, imageUri);
                Log.d(Config.TAG, "imagePath: " + imagePath);

                getData(imagePath);

                break;
            case ImageSelectUtil.REQUEST_CROP_IMAGE_SMALL:

                Bitmap bitmap = data.getParcelableExtra("data");

                updateImageView(bitmap);

                getData(bitmap);

                break;
            case ImageSelectUtil.REQUEST_CROP_IMAGE_BIG:

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), ImageSelectUtil.imageUri);

                    updateImageView(bitmap);

                    getData(bitmap);
                } catch (IOException e) {
                    Log.e(Config.TAG, e.getMessage(), e);
                }
                break;
            case ImageSelectUtil.REQUEST_CAPTURE_AND_CROP:

                ImageSelectUtil.cropImage(this, ImageSelectUtil.imageUri);

                break;
            default:
                break;
        }
    }

    private void updateImageView(Bitmap bitmap) {
        Glide.with(activity).load(bitmap).into(imageView);
    }

    private void updateImageView(Uri imageUri) {
        Glide.with(activity).load(imageUri).into(imageView);
    }
}