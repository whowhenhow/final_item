package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Label;
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
 * Created by 黄国正 on 2018/1/5.
 */

public class Add_project extends AppCompatActivity {
    int tech_flag = 0;
    int market_flag = 0;
    int beauty_flag = 0;
    int product_flag = 0;
    int manage_flag = 0;
    List<String> labelList = new ArrayList<>();
    private ProjectService projectService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);

        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_project.this, ProjectActivity.class);
                startActivity(intent);
            }
        });

        /**完成**/
        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText project_name = (EditText) findViewById(R.id.project_name);
                EditText project_manager = (EditText) findViewById(R.id.project_manager_account);
                EditText project_address = (EditText) findViewById(R.id.project_main_address);
                EditText project_neednumber = (EditText) findViewById(R.id.need_person_number);
                EditText project_intro = (EditText) findViewById(R.id.project_introduction);
                Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                projectService = retrofit.create(ProjectService.class);
                projectService.addProject(project_manager.getText().toString(),
                        project_name.getText().toString(),
                        Integer.parseInt(project_neednumber.getText().toString()),
                        project_intro.getText().toString(),
                        project_address.getText().toString(),
                        labelList)
                        .subscribeOn(rx.schedulers.Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Map<String, Integer>>() {
                            @Override
                            public void onCompleted() {

                            }
                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                Log.d("tag","error");
                            }
                            @Override
                            public void onNext(Map<String, Integer> map) {
                                if (map.get("project_id").toString().equals("")){
                                    Toast.makeText(Add_project.this, "添加失败！", Toast.LENGTH_SHORT).show();
                                }else{
                                    Intent intent = new Intent(Add_project.this, ProjectActivity.class);
                                    startActivity(intent);
                                }

                            }
                        });
            }
        });

        /**选择标签**/
        final Button tech = (Button) findViewById(R.id.tech);
        final Button market = (Button) findViewById(R.id.market);
        final Button beauty = (Button) findViewById(R.id.beauty);
        final Button product = (Button) findViewById(R.id.product);
        final Button manage = (Button) findViewById(R.id.manage);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tech_flag == 0){
                    tech.setTextColor(0xff008875);
                    tech_flag = 1;
                }else {
                    tech.setTextColor(0xffffffff);
                    tech_flag = 0;
                }
                labelList.add("技术");
            }
        });
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (market_flag == 0){
                    market.setTextColor(0xff008875);
                    market_flag = 1;
                }else {
                    market.setTextColor(0xffffffff);
                    market_flag = 0;
                }
                labelList.add("市场");
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beauty_flag == 0){
                    beauty.setTextColor(0xff008875);
                    beauty_flag = 1;
                }else {
                    beauty.setTextColor(0xffffffff);
                    beauty_flag = 0;
                }
                String label = new String();
                label = "美工";
                labelList.add(label);
            }
        });
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product_flag == 0){
                    product.setTextColor(0xff008875);
                    product_flag = 1;
                }else {
                    product.setTextColor(0xffffffff);
                    product_flag = 0;
                }
                labelList.add("产品");
            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manage_flag == 0){
                    manage.setTextColor(0xff008875);
                    manage_flag = 1;
                }else {
                    manage.setTextColor(0xffffffff);
                    manage_flag = 0;
                }
                labelList.add("运营");
            }
        });
    }
}
