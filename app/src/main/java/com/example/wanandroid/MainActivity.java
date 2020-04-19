package com.example.wanandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager viewPager;
    List<Fragment> fragmentList;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;

    MyFragmentPagerAdapter myFragmentPagerAdapter;
    ImageButton ImageButton1;
    ImageButton ImageButton2;
    ImageButton ImageButton3;
    ImageButton ImageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ImageButton1 = findViewById(R.id.btn_01);
        ImageButton2 = findViewById(R.id.btn_02);
        ImageButton3 = findViewById(R.id.btn_03);
        ImageButton4 = findViewById(R.id.btn_04);

        viewPager = findViewById(R.id.pager_view);

        fragmentList = new ArrayList<>();
        myFragment1 = new MyFragment1();

        fragmentList.add(myFragment1);
        myFragment2 = new MyFragment2();
        fragmentList.add(myFragment2);
        fragmentList.add(new MyFragment3());

        if (getIntent().getStringExtra("name") != null) {
            fragmentList.add(new MyFragment5(getIntent().getStringExtra("name"),getIntent().getStringExtra("account")));
        }else {
        fragmentList.add(new MyFragment4());
        }


        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(3);
        ImageButton4.setBackgroundColor(Color.rgb(135, 206, 250));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageButton1.setBackgroundColor(0xFFFFFF);
                ImageButton2.setBackgroundColor(0xFFFFFF);
                ImageButton3.setBackgroundColor(0xFFFFFF);
                ImageButton4.setBackgroundColor(0xFFFFFF);
                switch (position) {
                    case 0:
                        ImageButton1.setBackgroundColor(Color.rgb(135, 206, 250));
                        break;
                    case 1:
                        ImageButton2.setBackgroundColor(Color.rgb(135, 206, 250));

                        break;
                    case 2:
                        ImageButton3.setBackgroundColor(Color.rgb(135, 206, 250));
                        break;
                    case 3:
                        ImageButton4.setBackgroundColor(Color.rgb(135, 206, 250));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ImageButton1.setOnClickListener(this);
        ImageButton2.setOnClickListener(this);
        ImageButton3.setOnClickListener(this);
        ImageButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_01:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_02:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn_03:
                viewPager.setCurrentItem(2);
                break;
            case R.id.btn_04:
                viewPager.setCurrentItem(3);
                break;
        }
    }


}