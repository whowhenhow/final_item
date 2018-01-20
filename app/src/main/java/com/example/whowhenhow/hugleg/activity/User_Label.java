package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Label;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2017/12/24.
 */

public class User_Label extends AppCompatActivity {
    int tech_flag = 0;
    int market_flag = 0;
    int beauty_flag = 0;
    int product_flag = 0;
    int manage_flag = 0;
    List<String> labelList = new ArrayList<>();
    private UserService userService;
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.user_label);

        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Label.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });

        /**修改标签**/
        Button finish = (Button) findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                    userService = retrofit.create(UserService.class);
                    userService.changeUserLabel(personInfo.getUser_id(), labelList)
                            .subscribeOn(rx.schedulers.Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<List<String>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(User_Label.this,"修改失败！",Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(List<String> list) {
                                    if(list.equals(null)){
                                        Toast.makeText(User_Label.this,"修改失败！",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(User_Label.this,"修改成功！",Toast.LENGTH_SHORT).show();
                                        List<Label> labels = new ArrayList<Label>();
                                        for (int i = 0; i < labelList.size(); i++){
                                            Label label = new Label();
                                            label.setLabel_name(labelList.get(i));
                                            labels.add(label);
                                        }
                                        personInfo.setUser_label(labels);
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
        for (int i = 0; i < personInfo.getUser_label().size(); i++){
            Person_info personInfo1 = personInfo;
            if (personInfo1.getUser_label().get(i).getLabel_name().equals("技术")){
                tech.setTextColor(0xff008875);
                tech_flag = 1;
            }else if(personInfo1.getUser_label().get(i).getLabel_name().equals("市场")){
                market.setTextColor(0xff008875);
                market_flag = 1;
            }else if(personInfo1.getUser_label().get(i).getLabel_name().equals("美工")){
                beauty.setTextColor(0xff008875);
                beauty_flag = 1;
            }else if(personInfo1.getUser_label().get(i).getLabel_name().equals("产品")){
                product.setTextColor(0xff008875);
                product_flag = 1;
            }else if(personInfo1.getUser_label().get(i).getLabel_name().equals("运营")){
                manage.setTextColor(0xff008875);
                manage_flag = 1;
            }
        }
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
