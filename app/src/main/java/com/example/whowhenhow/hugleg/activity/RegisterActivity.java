package com.example.whowhenhow.hugleg.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;

import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2017/12/23.
 */

public class RegisterActivity extends AppCompatActivity{
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        /**返回登录界面**/
        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**确认注册后返回登录界面**/
        Button confirm = (Button) findViewById(R.id.confirm_button);
        final EditText user_phone = (EditText) findViewById(R.id.user_name);
        final EditText user_pass = (EditText) findViewById(R.id.user_password);
        final EditText user_confirm = (EditText) findViewById(R.id.user_confirm_password);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d("tag",user_pass.getText().toString()+" "+user_confirm.getText().toString());
                if(user_pass.getText().toString().equals("")||user_confirm.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("密码不能为空");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //获取点击位置
                        }
                    });
                    builder.show();
                }

                else if(!user_pass.getText().toString().equals(user_confirm.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("密码不匹配");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //获取点击位置
                        }
                    });
                    builder.show();
                }
                else {
                    Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                    userService = retrofit.create(UserService.class);
                    userService.addUser(user_phone.getText().toString(),user_pass.getText().toString())
                            .subscribeOn(rx.schedulers.Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Map<String, String>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(Map<String, String> stringStringMap) {

                                    if(stringStringMap.get("Response").equals("fail")){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                        builder.setTitle("您的账户名已经存在");
                                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //获取点击位置
                                            }
                                        });
                                        builder.show();
                                    }
                                    else{
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                }
            }
        });
    }
}
