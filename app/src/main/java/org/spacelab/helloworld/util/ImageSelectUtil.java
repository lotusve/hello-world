package org.spacelab.helloworld.util;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ImageSelectUtil {

    public static final int REQUEST_PICK_IMAGE = 101;

    /**
     * 从本地相册选择一张图片
     * @param fragment
     */
    public static void getOneImageFromLocal(Fragment fragment){

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

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        fragment.startActivityForResult(intent, REQUEST_PICK_IMAGE);

    }

}
