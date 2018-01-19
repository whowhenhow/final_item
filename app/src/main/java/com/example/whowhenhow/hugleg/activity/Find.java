package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2017/12/24.
 */

public class Find extends AppCompatActivity {
    private List<Person_info> mList = new ArrayList<>();
    final PersonAdapter adapter = new PersonAdapter(mList, this);
    private UserService userService;
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);

        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        /**填充列表**/
        Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
        userService = retrofit.create(UserService.class);
        userService.getLabelUser("技术")
                .subscribeOn(rx.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Person_info>>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("tag","error");
                        //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(List<Person_info> person_infos) {
                        for (int i = 0; i < person_infos.size(); i++){
                            Person_info person_info = person_infos.get(i);
                            mList.add(person_info);
                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Find.this));
                        recyclerView.setAdapter(adapter);
                    }
                });
        adapter.setonItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                Intent intent = new Intent(Find.this, PersonInfo.class);
                intent.putExtras(bundle);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        adapter.setonItemLongClickListener(new PersonAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                /*mList.remove(position);
                adapter.notifyDataSetChanged();*/
            }
        });


        /**切换到首页**/
        ImageView home = (ImageView) findViewById(R.id.mainpage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, MainPage.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到项目**/
        ImageView project = (ImageView) findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, ProjectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到发现**/
        ImageView find = (ImageView) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, Find.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到个人**/
        ImageView aboutme = (ImageView) findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**选择标签**/
        final TextView tech = (TextView) findViewById(R.id.tech);
        final TextView market = (TextView) findViewById(R.id.market);
        final TextView beauty = (TextView) findViewById(R.id.beauty);
        final TextView product = (TextView) findViewById(R.id.product);
        final TextView manage = (TextView) findViewById(R.id.manage);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tech.setTextColor(0xff008875);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                userService.getLabelUser("技术")
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Person_info>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(List<Person_info> person_infos) {
                                mList.clear();
                                for (int i = 0; i < person_infos.size(); i++){
                                    Person_info person_info = person_infos.get(i);
                                    mList.add(person_info);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xff008875);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                userService.getLabelUser("市场")
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Person_info>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(List<Person_info> person_infos) {
                                mList.clear();
                                for (int i = 0; i < person_infos.size(); i++){
                                    Person_info person_info = person_infos.get(i);
                                    mList.add(person_info);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xff008875);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                userService.getLabelUser("美工")
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Person_info>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(List<Person_info> person_infos) {
                                mList.clear();
                                for (int i = 0; i < person_infos.size(); i++){
                                    Person_info person_info = person_infos.get(i);
                                    mList.add(person_info);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xff008875);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                userService.getLabelUser("产品")
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Person_info>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(List<Person_info> person_infos) {
                                mList.clear();
                                for (int i = 0; i < person_infos.size(); i++){
                                    Person_info person_info = person_infos.get(i);
                                    mList.add(person_info);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xff008875);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                userService = retrofit.create(UserService.class);
                userService.getLabelUser("运营")
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Person_info>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                                //Toast.makeText(MainActivity.this,"密码错误或账户不存在",Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onNext(List<Person_info> person_infos) {
                                mList.clear();
                                for (int i = 0; i < person_infos.size(); i++){
                                    Person_info person_info = person_infos.get(i);
                                    mList.add(person_info);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }
}
