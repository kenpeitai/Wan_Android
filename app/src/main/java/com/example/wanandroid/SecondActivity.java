package com.example.wanandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class SecondActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private CheckBox remember_pass;

    private TextView textView, info;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();
        editText1 = findViewById(R.id.edit_account);
        editText2 = findViewById(R.id.edit_password);
        button = findViewById(R.id.log_on);
        remember_pass = findViewById(R.id.remember_pass);

        textView = findViewById(R.id.register);
        info = findViewById(R.id.info1);
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);

        if (isRemember) {
            String account = sharedPreferences.getString("remember_account", "");
            String password = sharedPreferences.getString("remember_pass", "");
            editText1.setText(account);
            editText2.setText(password);
            remember_pass.setChecked(true);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = editText1.getText().toString();
                String password = editText2.getText().toString();
                editor = sharedPreferences.edit();
                if (sharedPreferences.getString(account, "").equals("")) {
                    info.setText("此账户不存在");

                } else if (sharedPreferences.getString(account, "").equals(password)) {
                    info.setText("");

                    if (remember_pass.isChecked()) {

                        editor.putString("remember_pass", password);
                        editor.putString("remember_account", account);
                        editor.putBoolean("remember_password", true);
                    } else {
                        editor.clear();
                        editor.putBoolean("remember_password", false);

                    }
                    editor.apply();

                    String name = sharedPreferences.getString(account + "|", "");
                    Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                    intent1.putExtra("name", name);
                    intent1.putExtra("account",account);
                    startActivity(intent1);
                    finish();

                } else {
                    info.setText("密码错误");
                    editText2.setText("");
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);


            }
        });

    }
}

