package com.hhh.shopapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 用户登录注册处理类
 */
public class userService {
    private MySqlite dbHelper;
    public userService(Context context){
        dbHelper=new MySqlite(context,1);
    }

    //登录用，检测账号密码是否与数据库中的信息匹配
    public boolean login(String username, String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //注册用，将相应的账号密码写入数据库
    public boolean register(user user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password) values(?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }

    //检测该账号的用户是否存在
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String Query = "select * from user where username =?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}
