package com.hhh.shopapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_register;
    private EditText et_ID, et_password, et_again;
    private String username, password, again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_ID = (EditText) findViewById(R.id.et_ID);
        et_password = (EditText) findViewById(R.id.et_password);
        et_again = (EditText) findViewById(R.id.et_again);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_register:
                username = et_ID.getText().toString().trim();
                password = et_password.getText().toString().trim();
                again = et_again.getText().toString().trim();
                if (username.equals("") || password.equals("") || again.equals("")) {
                    Toast.makeText(this, "请完善信息！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!password.equals(again)) {
                        Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    userService userService = new userService(this);
                    Boolean flag = userService.CheckIsDataAlreadyInDBorNot(username);
                    if (flag == Boolean.TRUE) {
                        Toast.makeText(this, "该用户已存在！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        user user = new user();
                        user.setUsername(username);
                        user.setPassword(password);
                        userService.register(user);
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                break;
        }
    }
}
