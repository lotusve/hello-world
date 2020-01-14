package org.spacelab.helloworld.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import java.io.File;

public class ImageSelectUtil {

    public static final int REQUEST_PICK_IMAGE = 101;
    public static final int REQUEST_CROP_IMAGE_SMALL = 102;
    public static final int REQUEST_CROP_IMAGE_BIG = 103;

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
    public static void pickAndCropBigBitmap(Fragment fragment) {
        imageUri = getTmpUri();
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
     * 获得临时保存图片的Uri，用当前的毫秒值作为文件名
     */
    private static Uri getTmpUri() {
        String IMAGE_FILE_DIR = Environment.getExternalStorageDirectory() + "/" + "app_name";
        File dir = new File(IMAGE_FILE_DIR);
        File file = new File(IMAGE_FILE_DIR, Long.toString(System.currentTimeMillis()));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return Uri.fromFile(file);
    }

}
