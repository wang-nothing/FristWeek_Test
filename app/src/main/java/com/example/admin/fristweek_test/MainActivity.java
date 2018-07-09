package com.example.admin.fristweek_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Custom_Bitmap custom_tv = findViewById(R.id.custom_tv);
        String[] text = {"刮多一点","再多一点","再多一点点","完了"};
        custom_tv.setText(text[getRandom()]);
        custom_tv.initCustom(0xFFFFFFFF, 20, 1f);

    }
    private int getRandom(){
        Random random = new Random();
        int number = random.nextInt(4);
        return number;
    }
}
