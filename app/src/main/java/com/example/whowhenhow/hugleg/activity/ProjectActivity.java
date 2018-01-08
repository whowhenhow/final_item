package com.example.whowhenhow.hugleg.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.adapter.ProjectAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄国正 on 2018/1/7.
 */

public class ProjectActivity extends AppCompatActivity{
    private List<Project> mList = new ArrayList<>();
    final ProjectAdapter adapter = new ProjectAdapter(mList, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project);


        /**填充列表**/
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
                Intent intent = new Intent(ProjectActivity.this, ProjectInfo.class);
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
        //mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        /**发布项目**/
        Button add_project_button = (Button) findViewById(R.id.add_project_button);
        add_project_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, Add_project.class);
                startActivity(intent);
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
                Intent intent = new Intent(ProjectActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /**切换到首页**/
        ImageView home = (ImageView) findViewById(R.id.mainpage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, MainPage.class);
                startActivity(intent);
            }
        });

        /**切换到项目**/
        ImageView project = (ImageView) findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, ProjectActivity.class);
                startActivity(intent);
            }
        });

        /**切换到发现**/
        ImageView find = (ImageView) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, Find.class);
                startActivity(intent);
            }
        });

        /**切换到个人**/
        ImageView aboutme = (ImageView) findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, Aboutme.class);
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
