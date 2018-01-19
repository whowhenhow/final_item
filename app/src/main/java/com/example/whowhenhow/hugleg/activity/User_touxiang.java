package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;

/**
 * Created by 黄国正 on 2017/12/24.
 */

public class User_touxiang extends AppCompatActivity {
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.user_touxiang);

        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_touxiang.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
}
