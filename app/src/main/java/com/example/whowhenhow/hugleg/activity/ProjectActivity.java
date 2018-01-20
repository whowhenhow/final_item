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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.adapter.PersonAdapter;
import com.example.whowhenhow.hugleg.adapter.ProjectAdapter;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;
import com.example.whowhenhow.hugleg.service.ProjectService;
import com.example.whowhenhow.hugleg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2018/1/7.
 */

public class ProjectActivity extends AppCompatActivity{
    private List<Project> mList = new ArrayList<>();
    final ProjectAdapter adapter = new ProjectAdapter(mList, this);
    private ProjectService projectService;
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project);

        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        /**填充列表**/

        final Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
        projectService = retrofit.create(ProjectService.class);
        projectService.getLabelProject("技术")
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
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(ProjectActivity.this);
                        //mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                });
        adapter.setonItemClickListener(new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mList.get(position));
                Intent intent = new Intent(ProjectActivity.this, ProjectInfo.class);
                intent.putExtras(bundle);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
        adapter.setonItemLongClickListener(new ProjectAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                Project project = mList.get(position);
                if (project.getProject_manager_account().equals(personInfo.getUser_account())){
                    projectService = retrofit.create(ProjectService.class);
                    projectService.deleteProject(project.getProject_id())
                            .subscribeOn(rx.schedulers.Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Map<String, String>>() {
                                @Override
                                public void onCompleted() {

                                }
                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    Log.d("tag","error");
                                }
                                @Override
                                public void onNext(Map<String, String> map) {
                                    if (map.get("response").equals("success")){
                                        mList.remove(position);
                                        adapter.notifyDataSetChanged();
                                    }else{
                                        Toast.makeText(ProjectActivity.this, "删除失败！", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(ProjectActivity.this, "您不能删除他人的项目！", Toast.LENGTH_SHORT).show();
                }
                /**/
            }
        });


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
                Intent intent = new Intent(ProjectActivity.this, ProjectActivity.class);
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
                Intent intent = new Intent(ProjectActivity.this, Find.class);
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
                Intent intent = new Intent(ProjectActivity.this, Aboutme.class);
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
                projectService = retrofit.create(ProjectService.class);
                projectService.getLabelProject("技术")
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
                                mList.clear();
                                for (int i = 0; i < projectList.size(); i++){
                                    Project project = projectList.get(i);
                                    mList.add(project);
                                    //adapter.notifyDataSetChanged();
                                    //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xff008875);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                projectService = retrofit.create(ProjectService.class);
                projectService.getLabelProject("市场")
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
                                mList.clear();
                                for (int i = 0; i < projectList.size(); i++){
                                    Project project = projectList.get(i);
                                    mList.add(project);
                                    //adapter.notifyDataSetChanged();
                                    //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xff008875);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                projectService = retrofit.create(ProjectService.class);
                projectService.getLabelProject("美工")
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
                                mList.clear();
                                for (int i = 0; i < projectList.size(); i++){
                                    Project project = projectList.get(i);
                                    mList.add(project);
                                    //adapter.notifyDataSetChanged();
                                    //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xff008875);
                manage.setTextColor(0xffaaaaaa);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                projectService = retrofit.create(ProjectService.class);
                projectService.getLabelProject("产品")
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
                                mList.clear();
                                for (int i = 0; i < projectList.size(); i++){
                                    Project project = projectList.get(i);
                                    mList.add(project);
                                    //adapter.notifyDataSetChanged();
                                    //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                tech.setTextColor(0xffaaaaaa);
                market.setTextColor(0xffaaaaaa);
                beauty.setTextColor(0xffaaaaaa);
                product.setTextColor(0xffaaaaaa);
                manage.setTextColor(0xff008875);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                projectService = retrofit.create(ProjectService.class);
                projectService.getLabelProject("运营")
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
                                mList.clear();
                                for (int i = 0; i < projectList.size(); i++){
                                    Project project = projectList.get(i);
                                    mList.add(project);
                                    //adapter.notifyDataSetChanged();
                                    //Toast.makeText(MainPage.this, project.getProject_introduction(), Toast.LENGTH_SHORT).show();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }
}
