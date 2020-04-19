package com.example.wanandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment5 extends Fragment {
    private String name, account;
    private View view;


    public MyFragment5(String name, String account) {
        super();
        this.name = name;
        this.account = account;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.pager_view5, container, false);
        TextView textView = view.findViewById(R.id.text_name);
        TextView textView1 = view.findViewById(R.id.text_account);
        Button button = view.findViewById(R.id.btn_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
                getActivity().finish();
            }
        });
        textView.setText("用户名:" + name);
        textView1.setText("账户:" + account);
        return view;
    }
}
