package com.example.tatar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        int milliseconds_delayed = 1200;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.getDefaults("first", LoadingActivity.this)==null){
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                } else{
                    startActivity(new Intent(LoadingActivity.this, MenuActivity.class));
                }
            }
        }, milliseconds_delayed);

    }
}