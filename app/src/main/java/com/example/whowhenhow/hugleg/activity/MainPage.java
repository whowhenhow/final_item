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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.adapter.ProjectAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;

import java.util.ArrayList;
import java.util.List;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.service.ProjectService;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2017/12/23.
 */

public class MainPage extends AppCompatActivity{
    private List<Project> mList = new ArrayList<>();
    final ProjectAdapter adapter = new ProjectAdapter(mList, this);
    private List<Person_info> mList1 = new ArrayList<>();
    final PersonAdapter adapter1 = new PersonAdapter(mList1, this);
    private ProjectService projectService;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
       //Log.d("getinfo",personInfo.getUser_account());

        /**填充项目列表**/

        Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
        projectService = retrofit.create(ProjectService.class);
        projectService.getRanProject(null)
                .subscribeOn(rx.schedulers.Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Project>>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.d("tag","error");
                    }
                    @Override
                    public void onNext(List<Project> projectList) {
                        for (int i = 0; i < projectList.size(); i++){
                            Project project = projectList.get(i);
                            mList.add(project);
                            //adapter.notifyDataSetChanged();
                            //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.project_view);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(MainPage.this);
                        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                });
        adapter.setonItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                Intent intent = new Intent(MainPage.this, ProjectInfo.class);
                intent.putExtras(bundle);
                intent.putExtra("flag", 0);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
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


        /**填充大腿列表**/
        userService = retrofit.create(UserService.class);
        userService.getRanUser(null)
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
                            mList1.add(person_info);
                        }
                        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.person_view);
                        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(MainPage.this);
                        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView1.setLayoutManager(mLayoutManager1);
                        recyclerView1.setAdapter(adapter1);
                    }
                });
        adapter1.setonItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList1.get(position));
                Intent intent = new Intent(MainPage.this, PersonInfo.class);
                intent.putExtras(bundle);
                intent.putExtra("flag", 0);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
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
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到项目**/
        ImageView project = (ImageView) findViewById(R.id.project);
        project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, ProjectActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到发现**/
        ImageView find = (ImageView) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Find.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**切换到个人**/
        ImageView aboutme = (ImageView) findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
}
