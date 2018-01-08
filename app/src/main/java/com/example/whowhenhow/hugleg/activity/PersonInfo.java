package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;

/**
 * Created by 黄国正 on 2018/1/5.
 */

public class PersonInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_information);

        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonInfo.this, Find.class);
                startActivity(intent);
            }
        });

        /**绑定数据**/
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Person_info person_info = (Person_info) bundle.getSerializable("data");
        TextView nickname = (TextView) findViewById(R.id.nickname);
        TextView adress = (TextView) findViewById(R.id.adress);
        TextView intro = (TextView) findViewById(R.id.introduction);
        nickname.setText(person_info.getUser_nickname());
        adress.setText(person_info.getUser_address());
        intro.setText(person_info.getUser_introduction());
    }
}
