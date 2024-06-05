package com.hhh.shopapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_login,bt_register;
    private EditText et_ID,et_password;
    private String username,password;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_ID = (EditText) findViewById(R.id.et_ID);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        editor = sp.edit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                username = et_ID.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if(et_ID.getText().toString().trim().equals("") ||
                        et_password.getText().toString().trim().equals("")){
                    Toast.makeText(this,"账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else{
                    userService userService = new userService(this);
                    Boolean flag = userService.login(username,password);
                    if(flag== Boolean.FALSE){
                        Toast.makeText(this,"账号或密码不正确！", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        editor.putString("username",username);
                        editor.commit();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;

            case R.id.bt_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
