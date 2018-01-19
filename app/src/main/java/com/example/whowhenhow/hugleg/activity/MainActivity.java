package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.*;
import com.example.whowhenhow.hugleg.bean.Project;
import com.example.whowhenhow.hugleg.service.ProjectService;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    //定义retrofit、okhttp和Service
    private ProjectService projectService;
    private UserService userService;
    public  final static String SER_KEY = "ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Intent intent = new Intent(MainActivity.this, MainPage.class);
        //startActivity(intent);

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
        final EditText user_name = (EditText) findViewById(R.id.user_name);
        final EditText user_pass = (EditText) findViewById(R.id.user_password);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user_name.getText().toString();
                String pass = user_pass.getText().toString();
                //debug


                SharedPreferences sharedPreferences = getSharedPreferences("issignin?", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("flag",1);
                editor.commit();

                //
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                Log.d("TAG", name+" "+pass);
                userService.login(name,pass)
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Person_info>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(Person_info person_info) {
                                if(person_info.getUser_account().toString()!=null){
                                    SharedPreferences sharedPreferences = getSharedPreferences("issignin?", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("flag",1);
                                    editor.commit();
                                    Intent intent = new Intent(MainActivity.this, MainPage.class);
                                    Bundle mBundle = new Bundle();
                                    mBundle.putSerializable(SER_KEY,person_info);
                                    intent.putExtras(mBundle);
                                    startActivity(intent);
                                }

                            }
                        });

            }
        });

    }
}
