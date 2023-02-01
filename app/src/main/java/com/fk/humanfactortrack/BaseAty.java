package com.fk.humanfactortrack;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseAty extends AppCompatActivity {
    private static final String TAG = "com.fk.humanfactortrack/BaseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        boolean isGetStoragePermissions = PermissionUtils.isGetStoragePermissions(this);
        if (isGetStoragePermissions) {
            initViews();
            initViewsData();
            initData();
            updateUI();
        } else {
            Log.d(TAG, "未申请权限");
            //申请权限
            PermissionUtils.verifyStoragePermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        try {
            if (requestCode == PermissionUtils.PERMISSION_REQUEST_CODE) {
                if (PermissionUtils.hasDeniedNecessaryPermission(permissions, grantResults)) {
                    Toast.makeText(this, "权限申请失败", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    //权限申请成功 初始化
                    initViews();
                    initViewsData();
                    initData();
                    updateUI();
                }
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        } catch (Exception e) {
            Log.i(TAG, "permission result error=" + e.toString());
        }

    }

    protected abstract void initView();

    protected abstract void initViews();

    protected abstract void initViewsData();

    protected abstract void initData();

    protected abstract void updateUI();

}
