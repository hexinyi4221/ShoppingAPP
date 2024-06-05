package com.hhh.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton rb01,rb02;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
        username = sp.getString("username","");
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.content_layout,new fragment_one());
        transaction.commit();
        initView();
    }

    private void initView(){
        rb01= (RadioButton) findViewById(R.id.rb01);
        rb02= (RadioButton) findViewById(R.id.rb02);

        rb01.setTextColor(Color.GREEN);
        rb01.setChecked(true);

        rb01.setOnClickListener(this);
        rb02.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transaction=manager.beginTransaction();
        switch (view.getId()){
            case R.id.rb01:
                rb01.setTextColor(Color.GREEN);
                rb02.setTextColor(Color.BLACK);
                transaction.replace(R.id.content_layout,new fragment_one());
                break;
            case R.id.rb02:
                rb02.setTextColor(Color.GREEN);
                rb01.setTextColor(Color.BLACK);
                transaction.replace(R.id.content_layout,new fragment_two());
                break;
        }
        transaction.commit();
    }
}
