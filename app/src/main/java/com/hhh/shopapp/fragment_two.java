package com.hhh.shopapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class fragment_two extends Fragment{
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String username;
    private TextView tv_name,tv_price;
    private List<Map<String,String>> list;
    private MyAdapter adapter;
    private ListView lv;
    private MySqlite mySqlite;
    private SQLiteDatabase db;
    private TextView tv_total,tv_next;
    private int total = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sp.edit();
        username = sp.getString("username","");
        lv = (ListView) view.findViewById(R.id.lv);
        tv_total = view.findViewById(R.id.tv_total);
        tv_next = view.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PayActivity.class);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
        list = new ArrayList<>();
        getData();
        adapter = new MyAdapter(
                getActivity(),
                list,
                R.layout.item,
                new String[]{"name","price"},
                new int[]{R.id.tv_name,R.id.tv_price}
        );
        lv.setAdapter(adapter);
        tv_total.setText("总计："+total+"元");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        adapter = new MyAdapter(
                getActivity(),
                list,
                R.layout.item,
                new String[]{"name","price"},
                new int[]{R.id.tv_name,R.id.tv_price}
        );
        lv.setAdapter(adapter);
        tv_total.setText("总计："+total+"元");
    }

    private class MyAdapter extends SimpleAdapter {
        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource,
                         String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            tv_name = v.findViewById(R.id.tv_name);
            tv_price = v.findViewById(R.id.tv_price);
            tv_name.setText(list.get(position).get("name"));
            tv_price.setText(list.get(position).get("price")+"元");
            return v;
        }
    }

    public List<Map<String,String>> getData(){
        list.clear();
        total = 0;
        MySqlite mySQLite = new MySqlite(getActivity(), 1);
        SQLiteDatabase database = mySQLite.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from record where username = '"+username+"'", null);
        System.out.println(cursor.getCount());
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String id = cursor.getString(cursor.getColumnIndex("id"));
            Map<String,String> map = new HashMap<>();
            map.put("name",name);
            map.put("price",price);
            map.put("id",id);
            list.add(map);
            total = total + Integer.parseInt(price);
        }
        return list;
    }
}
