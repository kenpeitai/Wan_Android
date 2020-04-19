package com.example.wanandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment1 extends Fragment {
    public List<Banner> bannerList;
    public BannerViewPager bannerViewPager;
    public TextView textView1, textView2, textView3, textView4;
    public ImageView imageView1, imageView2, imageView3, imageView4;
    private  View view;
    private ImageView[] imageViews;
    private TextView[] textViews;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null){
            return view;
        }

        view = inflater.inflate(R.layout.pager_view1, container, false);
        bannerViewPager = view.findViewById(R.id.banner_container);
        ArrayList<View> list = new ArrayList();
        View bannerView1 = this.getLayoutInflater().inflate(R.layout.banner_view1, null);
        View bannerView2 = this.getLayoutInflater().inflate(R.layout.banner_view2, null);
        View bannerView3 = this.getLayoutInflater().inflate(R.layout.banner_view3, null);
        View bannerView4 = this.getLayoutInflater().inflate(R.layout.banner_view4, null);
        list.add(bannerView1);
        list.add(bannerView2);
        list.add(bannerView3);
        list.add(bannerView4);
        textView1 = bannerView1.findViewById(R.id.banner_text1);
        textView2 = bannerView2.findViewById(R.id.banner_text2);
        textView3 = bannerView3.findViewById(R.id.banner_text3);
        textView4 = bannerView4.findViewById(R.id.banner_text4);
        textViews = new TextView[]{textView1,textView2,textView3,textView4};
        imageView1 = bannerView1.findViewById(R.id.banner_image1);
        imageView2 = bannerView2.findViewById(R.id.banner_image2);
        imageView3 = bannerView3.findViewById(R.id.banner_image3);
        imageView4 = bannerView4.findViewById(R.id.banner_image4);
        imageViews = new ImageView[]{imageView1, imageView2, imageView3, imageView4};


        BannerAdapter bannerAdapter = new BannerAdapter(list);
        bannerViewPager.setAdapter(bannerAdapter);
        bannerViewPager.start();
        initBanner();
        return view;
    }

    private void praseJSON(String data) {
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");
        Gson gson = new Gson();
        final Bitmap[] bitmap = new Bitmap[4];
        final ArrayList<Banner> banners = new ArrayList<>();
        for (JsonElement user : jsonArray) {
            Banner banner = gson.fromJson(user, new TypeToken<Banner>() {
            }.getType());
            banners.add(banner);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    for (int i = 0; i <4 ; i++) {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(banners.get(i).getImagePath())
                                .build();
                        Response response = client.newCall(request).execute();
                        byte[] responseData = response.body().bytes();
                         bitmap[i] = BitmapFactory.decodeByteArray(responseData,0,responseData.length);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <4 ; i++) {
                            imageViews[i].setImageBitmap(bitmap[i]);
                            textViews[i].setText(banners.get(i).getTitle());
                        }
                    }
                });



            }
        }).start();


    }

    private void initBanner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.wanandroid.com/banner/json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    praseJSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();


    }


}
