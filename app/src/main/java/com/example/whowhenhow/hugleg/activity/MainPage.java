package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.adapter.ProjectAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄国正 on 2017/12/23.
 */
public class MainPage extends AppCompatActivity{
    private List<Project> mList = new ArrayList<>();
    final ProjectAdapter adapter = new ProjectAdapter(mList, this);
    private List<Person_info> mList1 = new ArrayList<>();
    final PersonAdapter adapter1 = new PersonAdapter(mList1, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        /**填充项目列表**/
        Project project0 = new Project();
        project0.setNeed_person_number(5);
        project0.setProject_introduction("世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！");
        project0.setProject_main_address("广州");
        project0.setProject_manager_account("和工作");
        project0.setProject_name("抱大腿");
        mList.add(project0);

        Project project1 = new Project();
        project1.setNeed_person_number(5);
        project1.setProject_introduction("世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！");
        project1.setProject_main_address("广州");
        project1.setProject_manager_account("和工作");
        project1.setProject_name("抱大腿");
        mList.add(project1);

        Project project2 = new Project();
        project2.setNeed_person_number(5);
        project2.setProject_introduction("世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！世界第一UI app！");
        project2.setProject_main_address("广州");
        project2.setProject_manager_account("和工作");
        project2.setProject_name("抱大腿");
        mList.add(project2);

        adapter.setonItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                Intent intent = new Intent(MainPage.this, ProjectInfo.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setonItemLongClickListener(new ProjectAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                /*mList.remove(position);
                adapter.notifyDataSetChanged();*/
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.project_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        /**填充大腿列表**/
        Person_info person_info = new Person_info();
        person_info.setUser_nickname("hgz");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList1.add(person_info);
        person_info = new Person_info();
        person_info.setUser_nickname("huanghaolun");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList1.add(person_info);
        person_info = new Person_info();
        person_info.setUser_nickname("huangchengjie");
        person_info.setUser_address("gzjy");
        person_info.setUser_introduction("一个他妈的大帅逼，帅到花见花开，车间车爆胎!一个他妈的大帅逼，帅到花见花开，车间车爆胎!");
        mList1.add(person_info);
        adapter1.setonItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList1.get(position));
                Intent intent = new Intent(MainPage.this, PersonInfo.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter1.setonItemLongClickListener(new PersonAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                /*mList.remove(position);
                adapter.notifyDataSetChanged();*/
            }
        });
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.person_view);
        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(this);
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView1.setAdapter(adapter1);

        /**菜单点击事件**/
        ImageView more = (ImageView) findViewById(R.id.more);
        final LinearLayout menu = (LinearLayout) findViewById(R.id.menu);
        LinearLayout main = (LinearLayout) findViewById(R.id.main_layout);
        final int[] menu_flag = {0};
        menu.setVisibility(View.GONE);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setVisibility(View.GONE);
                menu_flag[0] = 0;
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu_flag[0] == 0){
                    menu.setVisibility(View.VISIBLE);
                    menu_flag[0] = 1;
                }else{
                    menu.setVisibility(View.GONE);
                    menu_flag[0] = 0;
                }
            }
        });

        /**退出登录点击事件**/
        TextView signout = (TextView) findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("issignin?", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("flag",0);
                editor.commit();
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /**切换到首页**/
        ImageView home = (ImageView) findViewById(R.id.mainpage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, MainPage.class);
                startActivity(intent);
            }
        });

        /**切换到项目**/
        ImageView project = (ImageView) findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ProjectActivity.class);
                startActivity(intent);
            }
        });

        /**切换到发现**/
        ImageView find = (ImageView) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Find.class);
                startActivity(intent);
            }
        });

        /**切换到个人**/
        ImageView aboutme = (ImageView) findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Aboutme.class);
                startActivity(intent);
            }
        });
    }
}
