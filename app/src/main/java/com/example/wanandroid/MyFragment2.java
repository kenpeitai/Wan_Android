package com.example.wanandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFragment2 extends Fragment {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    ArrayList<Datas> datasArrayList = new ArrayList<>();
    View view;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null){
            return view;
        }

        view = inflater.inflate(R.layout.pager_view2, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new MyRecyclerViewAdapter(datasArrayList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        initItem();
        adapter.notifyDataSetChanged();
        return view;
    }


    private void initItem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.wanandroid.com/article/list/0/json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseData);
                    JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("datas");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String title = jsonObject1.getString("title");
                        String niceDate = jsonObject1.getString("niceDate");
                        String superChapterName = jsonObject1.getString("superChapterName");
                        datasArrayList.add(new Datas(title, niceDate, superChapterName));
                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }
}
