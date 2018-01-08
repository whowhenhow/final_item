package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;

/**
 * Created by 黄国正 on 2018/1/5.
 */

public class Add_project extends AppCompatActivity {
    int tech_flag = 0;
    int market_flag = 0;
    int beauty_flag = 0;
    int product_flag = 0;
    int manage_flag = 0;
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
            }
        });
    }
}
