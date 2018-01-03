package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whowhenhow.hugleg.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**是否已经登陆**/
        SharedPreferences sharedPre = getSharedPreferences("issignin?",Context.MODE_PRIVATE);
        int flag = sharedPre.getInt("flag", 0);//获取flag，如果没有则返回0
        if (flag == 1){
            SharedPreferences sharedPreferences = getSharedPreferences("issignin?", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("flag",0);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, MainPage.class);
            startActivity(intent);
        }
        /**使得edittext不在一开始就获取焦点**s/
        LinearLayout parent = (LinearLayout) findViewById(R.id.parent_of_edittext);
        parent.setFocusable(true);
        parent.setFocusableInTouchMode(true);

        /**跳转到注册页面**/
        Button register = (Button) findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        /**跳转到首页**/
        Button signin = (Button) findViewById(R.id.signin_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("issignin?", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("flag",1);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, MainPage.class);
                startActivity(intent);
            }
        });
    }
}
