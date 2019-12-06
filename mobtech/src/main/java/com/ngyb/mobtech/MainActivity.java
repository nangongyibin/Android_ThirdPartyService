package com.ngyb.mobtech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] str = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CHANGE_NETWORK_STATE,
            Manifest.permission.READ_CONTACTS
    };
    private List<String> requestPermission = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : str) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "initPermission: 未允许的权限" + permission);
                    requestPermission.add(permission);
                }
            }
            if (requestPermission != null && requestPermission.size() > 0) {
                requestPermissions(requestPermission.toArray(new String[requestPermission.size()]), 7219);
            }
        }
    }

    public void asecondtest(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7219) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Intent goToSettings = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                    goToSettings.setData(Uri.parse("package:" + getPackageName()));
                    startActivity(goToSettings);
                    Log.e(TAG, "onRequestPermissionsResult: " + permissions[i]);
                }
            }
        }
    }
}
