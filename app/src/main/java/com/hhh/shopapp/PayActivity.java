package com.hhh.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {
    private Button bt_submit;
    private TextView tv_result;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        tv_result  =findViewById(R.id.tv_result);
        bt_submit  =findViewById(R.id.bt_submit);
        iv_back  =findViewById(R.id.iv_back);
        Intent intent = getIntent();
        tv_result.setText("总金额："+intent.getIntExtra("total",0)+"元");
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord();
                Toast.makeText(PayActivity.this, "结算成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void deleteRecord(){
        MySqlite mySqlite=new MySqlite(this,1);
        SQLiteDatabase db=mySqlite.getWritableDatabase();
        db.delete("record","",new String[]{});
        db.close();
    }

}
