package com.ngyb.mobtech.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ngyb.mobtech.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sharesdk(View view) {
        Intent intent = new Intent(this, ShareSDKActivity.class);
        startActivity(intent);
    }
}
