package com.zhangyc.framedemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtil {

    public static final int  REQUEST_CODE = 1000;
    public static void requestPermission(Activity activity, String[] permissions) {
        if (permissions == null) throw new NullPointerException();
        ArrayList<String> pers = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (!checkPermission(activity, permissions[i])){
                pers.add(permissions[i]);
            }
        }
        if (pers.size() > 0) {
            String[] objects = new String[pers.size()];
            ActivityCompat.requestPermissions(activity, pers.toArray(objects), REQUEST_CODE);
        }
    }

    public static boolean checkPermission(Context context,String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
