package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.*;
import com.example.whowhenhow.hugleg.bean.Project;
import com.example.whowhenhow.hugleg.service.ProjectService;
import com.example.whowhenhow.hugleg.service.UserService;
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

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    //定义retrofit、okhttp和Service
    private ProjectService projectService;
    private UserService userService;

    //创建OkHttp
    public static OkHttpClient createOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept", "*/*")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .build();
                        Response response = null;
                        try {
                            response = chain.proceed(request);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String data = "";
                        if (response.body() != null)
                            data = response.body().string();
                        Log.i("data",data);
                        return response.newBuilder()
                                .body(ResponseBody.create(MediaType.parse("UTF-8"), data))
                                .build();
                    }
                })
                .build();
        return okHttpClient;
    }

    //创建retrofit
    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createOkHttp())
                .build();
    }

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

        //测试网络请求——普通请求
//        Retrofit retrofit = createRetrofit(Const.BASEURL);
//        userService = retrofit.create(UserService.class);
//
////        List<String> user_label = new ArrayList<>();
////        user_label.add("技术");
////        user_label.add("产品");
//        userService.getUserInfo("Eric")
//                .subscribeOn(rx.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Person_info>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i("TAG", "completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        Log.e("Error", "error");
//                    }
//
//                    @Override
//                    public void onNext(Person_info person_info) {
//                        Log.i("PersonInfo", person_info.toString());
//                    }
//                });

        //multipart上传文件或图片，此处是更改用户头像的请求
//        Retrofit retrofit = createRetrofit(Const.BASEURL);
//        userService = retrofit.create(UserService.class);
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("user_id", JSON.toJSONString(1));
//        File file = new File(); //括号内是从手机本地获取到的资源
//        builder.addFormDataPart("user_avatar", file.getName(), RequestBody.create(MediaType.parse("image/*"), file);
//        RequestBody body = builder.build();
//        userService.changeUserAvatar(body)
//                .subscribeOn(rx.schedulers.Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Map<String, String>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.i("TAG", "completed");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onNext(Map<String, String> stringStringMap) {
//                        Log.i("URL", stringStringMap.get("user_avatar"));
//                        //获取到图片URL后 用gilde加载图片出来 路说很简单的 不会百度就行
//
//                    }
//                });
    }
}
