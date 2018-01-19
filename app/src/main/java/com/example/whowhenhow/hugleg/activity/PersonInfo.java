package com.example.whowhenhow.hugleg.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;

/**
 * Created by 黄国正 on 2018/1/5.
 */

public class PersonInfo extends AppCompatActivity {
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_information);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final Person_info person_info = (Person_info) bundle.getSerializable("data");
        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);

        /**对话**/
        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("联系方式：" + person_info.getUser_account());
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //获取点击位置
                    }
                });
                builder.show();
            }
        });

        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        final int flag = intent.getIntExtra("flag", 1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    Intent intent = new Intent(PersonInfo.this, Find.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SER_KEY,personInfo);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PersonInfo.this, MainPage.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SER_KEY,personInfo);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }

            }
        });

        /**绑定数据**/
        TextView nickname = (TextView) findViewById(R.id.nickname);
        TextView adress = (TextView) findViewById(R.id.adress);
        TextView intro = (TextView) findViewById(R.id.introduction);
        nickname.setText("昵称：" + person_info.getUser_nickname());
        adress.setText("地址：" + person_info.getUser_address());
        intro.setText("介绍：" + person_info.getUser_introduction());
        final Button tech = (Button) findViewById(R.id.tech);
        final Button market = (Button) findViewById(R.id.market);
        final Button beauty = (Button) findViewById(R.id.beauty);
        final Button product = (Button) findViewById(R.id.product);
        final Button manage = (Button) findViewById(R.id.manage);
        if (person_info.getUser_label().equals(null)){
            tech.setTextColor(0xffaaaaaa);
            market.setTextColor(0xffaaaaaa);
            beauty.setTextColor(0xffaaaaaa);
            product.setTextColor(0xffaaaaaa);
            manage.setTextColor(0xffaaaaaa);
        }else{
            tech.setTextColor(0xffaaaaaa);
            market.setTextColor(0xffaaaaaa);
            beauty.setTextColor(0xffaaaaaa);
            product.setTextColor(0xffaaaaaa);
            manage.setTextColor(0xffaaaaaa);
            for (int i = 0; i < person_info.getUser_label().size(); i++){
                Person_info person_info1 = person_info;
                if (person_info1.getUser_label().get(i).getLabel_name().equals("技术")){
                    tech.setTextColor(0xff008875);
                }else if(person_info1.getUser_label().get(i).getLabel_name().equals("市场")){
                    market.setTextColor(0xff008875);
                }else if(person_info1.getUser_label().get(i).getLabel_name().equals("美工")){
                    beauty.setTextColor(0xff008875);
                }else if(person_info1.getUser_label().get(i).getLabel_name().equals("产品")){
                    product.setTextColor(0xff008875);
                }else if(person_info1.getUser_label().get(i).getLabel_name().equals("运营")){
                    manage.setTextColor(0xff008875);
                }
            }
        }
    }
}
