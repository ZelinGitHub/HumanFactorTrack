package com.fk.humanfactortrack;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * Created by Hurley on 2019/11/12 19:30
 */
public class PermissionUtils {


    public static final int PERMISSION_REQUEST_CODE = 1;
    static String[] NECESSARY_PERMISSIONS = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    public static boolean isGetStoragePermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        int permissionRead = ActivityCompat.checkSelfPermission(activity,
                "android.permission.READ_EXTERNAL_STORAGE");
        return permissionWrite == PackageManager.PERMISSION_GRANTED && permissionRead == PackageManager.PERMISSION_GRANTED;
    }

    public static void verifyStoragePermissions(Activity activity) {
        try {
            ActivityCompat.requestPermissions(activity, NECESSARY_PERMISSIONS, PERMISSION_REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasDeniedNecessaryPermission(String[] permissions, int[] grantResults) {
        if (permissions == null || permissions.length == 0) return false;
        if (grantResults == null || grantResults.length == 0) return false;

        for (int i = 0; i < grantResults.length; ++i) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                if (containedInNecessaryPermissions(permissions[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean containedInNecessaryPermissions(String permission) {
        for (String eachPermission : NECESSARY_PERMISSIONS) {
            if (eachPermission.equals(permission)) {
                return true;
            }
        }
        return false;
    }

}
