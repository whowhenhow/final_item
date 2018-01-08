package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄国正 on 2017/12/24.
 */

public class Find extends AppCompatActivity {
    private List<Person_info> mList = new ArrayList<>();
    final PersonAdapter adapter = new PersonAdapter(mList, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);

        /**填充列表**/
        Person_info person_info = new Person_info();
        person_info.setUser_nickname("hgz");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList.add(person_info);
        person_info = new Person_info();
        person_info.setUser_nickname("huanghaolun");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList.add(person_info);
        person_info = new Person_info();
        person_info.setUser_nickname("huangchengjie");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList.add(person_info);
        adapter.setonItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                Intent intent = new Intent(Find.this, PersonInfo.class);
                intent.putExtras(bundle);
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
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        /**切换到首页**/
        ImageView home = (ImageView) findViewById(R.id.mainpage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, MainPage.class);
                startActivity(intent);
            }
        });

        /**切换到项目**/
        ImageView project = (ImageView) findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, ProjectActivity.class);
                startActivity(intent);
            }
        });

        /**切换到发现**/
        ImageView find = (ImageView) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, Find.class);
                startActivity(intent);
            }
        });

        /**切换到个人**/
        ImageView aboutme = (ImageView) findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Find.this, Aboutme.class);
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
                mList.clear();
                Person_info person_info1 = new Person_info();
                person_info1.setUser_nickname("whowhenhow");
                person_info1.setUser_address("gzjy");
                person_info1.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
                mList.add(person_info1);
                /*person_info = new Person_info();
                person_info.setUser_nickname("whowhenhow");
                person_info.setUser_address("gzjy");
                person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
                mList.add(person_info);
                person_info = new Person_info();
                person_info.setUser_nickname("whowhenhow");
                person_info.setUser_address("gzjy");
                person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
                mList.add(person_info);*/
                adapter.notifyDataSetChanged();
                Toast.makeText(Find.this, "fuck", Toast.LENGTH_SHORT).show();
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
            }
        });
    }
}
