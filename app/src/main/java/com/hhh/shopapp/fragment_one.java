package com.hhh.shopapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class fragment_one extends Fragment implements View.OnClickListener {
    private Button bt01,bt02,bt03,bt04,bt05;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        sp = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sp.edit();
        username = sp.getString("username","");
        bt01 = view.findViewById(R.id.bt01);
        bt02 = view.findViewById(R.id.bt02);
        bt03 = view.findViewById(R.id.bt03);
        bt04 = view.findViewById(R.id.bt04);
        bt05 = view.findViewById(R.id.bt05);

        bt01.setOnClickListener(this);
        bt02.setOnClickListener(this);
        bt03.setOnClickListener(this);
        bt04.setOnClickListener(this);
        bt05.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt01:
                insertCart("裤子","100");
                break;
            case R.id.bt02:
                insertCart("衣服","200");
                break;
            case R.id.bt03:
                insertCart("手表","300");
                break;
            case R.id.bt04:
                insertCart("鞋子","400");
                break;
            case R.id.bt05:
                insertCart("化妆品","500");
                break;
        }
    }

    private void insertCart(String name,String price) {
        MySqlite mySQLite = new MySqlite(getActivity(), 1);
        SQLiteDatabase db= mySQLite.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("price",price);
        values.put("username",username);
        db.insert("record", null, values);
        db.close();
        Toast.makeText(getActivity(),"加入购物车成功", Toast.LENGTH_SHORT).show();
    }
}
