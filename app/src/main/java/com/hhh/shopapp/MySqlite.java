package com.hhh.shopapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlite extends SQLiteOpenHelper {
    private Context context;
    public MySqlite(@Nullable Context context, int version) {
        super(context, "shop.db", null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))";
        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("create table record("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username text," +
                "name text," +
                "price text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
