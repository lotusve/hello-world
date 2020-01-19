package org.spacelab.helloworld.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限工具类
 */
public class PermissionUtil {

    public static final int REQUEST_CODE = 101;

    private static String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    public static void checkPermission(Context context) {

        List<String> unGrantedPermissionList = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                // 没有授权
                unGrantedPermissionList.add(permission);
            }
        }

        if (unGrantedPermissionList.size() > 0) {
            // 申请权限
            ActivityCompat.requestPermissions((Activity) context, unGrantedPermissionList.toArray(new String[unGrantedPermissionList.size()]), REQUEST_CODE);
        }

    }

}
