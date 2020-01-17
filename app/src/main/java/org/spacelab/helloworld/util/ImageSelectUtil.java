package org.spacelab.helloworld.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.fragment.app.Fragment;

import org.spacelab.helloworld.Config.Config;
import org.spacelab.helloworld.component.CommonFileProvider;

import java.io.File;
import java.io.IOException;

public class ImageSelectUtil {

    public static final int REQUEST_PICK_IMAGE = 101;
    public static final int REQUEST_CROP_IMAGE_SMALL = 102;
    public static final int REQUEST_CROP_IMAGE_BIG = 103;
    public static final int REQUEST_CAPTURE_AND_CROP = 104;

    public static Uri imageUri;

    /**
     * 从本地选择一张图片
     */
    public static void pickImageFromLocal(Fragment fragment){

        // action 可选择Intent.ACTION_GET_CONTENT or Intent.ACTION_PICK
        // 辅以 Uri or Type
        // IntentFilter 会过滤

//        方式一
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");

//        方式二
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");

//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        fragment.startActivityForResult(intent, REQUEST_PICK_IMAGE);

    }

    /**
     * 选择一张图片并裁剪获得一个小图
     */
    public static void pickAndCropSmallBitmap(Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        fragment.startActivityForResult(intent, REQUEST_CROP_IMAGE_SMALL);
    }

    /**
     * 选择一张图片并裁剪获得一个大图
     */
    public static void pickAndCropBigBitmap(Fragment fragment, Activity activity) {

        imageUri = getImageUri(activity, true);

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 2000);
        intent.putExtra("outputY", 2000);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        fragment.startActivityForResult(intent, REQUEST_CROP_IMAGE_BIG);
    }

    /**
     * 拍照并裁剪
     */
    public static void startImageCapture(Fragment fragment, Activity activity) {

        imageUri = getImageUri(activity, false);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        fragment.startActivityForResult(intent, REQUEST_CAPTURE_AND_CROP);
    }

    /**
     * 裁剪拍照后得到的图片
     */
    public static void cropImage(Fragment fragment, Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        fragment.startActivityForResult(intent, REQUEST_CROP_IMAGE_BIG);
    }

    /**
     * 构造 Image Uri
     * @param activity activity
     * @param fromFile if create uri from file
     * @return
     */
    private static Uri getImageUri(Activity activity, boolean fromFile) {
        Uri uri = null;
        try {
            File imageFile = new File(activity.getExternalCacheDir(), System.currentTimeMillis() + ".png");

            if (imageFile.exists()) {
                imageFile.delete();
            }

            imageFile.createNewFile();

            if (fromFile) {
                uri = Uri.fromFile(imageFile);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = CommonFileProvider.getUriForFile(activity, Config.AUTHORITY, imageFile);
                } else {
                    uri = Uri.fromFile(imageFile);
                }
            }
        } catch (IOException e) {
            Log.e(Config.TAG, e.getMessage(), e);
        }
        return uri;
    }
}
