package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.bean.Project;

/**
 * Created by 黄国正 on 2018/1/7.
 */

public class ProjectInfo extends AppCompatActivity {
    public  final static String SER_KEY = "ser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_information);

        Intent intent = getIntent();
        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        final int flag = intent.getIntExtra("flag", 1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1){
                    Intent intent = new Intent(ProjectInfo.this, ProjectActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SER_KEY,personInfo);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ProjectInfo.this, MainPage.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable(SER_KEY,personInfo);
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }

            }
        });

        /**绑定数据**/
        Bundle bundle = intent.getExtras();
        Project project = (Project) bundle.getSerializable("data");
        TextView projectname = (TextView) findViewById(R.id.project_name);
        TextView adress = (TextView) findViewById(R.id.project_address);
        TextView personnumber = (TextView) findViewById(R.id.personnumber);
        TextView manager = (TextView) findViewById(R.id.project_manager);
        TextView introduction = (TextView) findViewById(R.id.introduction);
        projectname.setText(project.getProject_name());
        adress.setText("地址：" + project.getProject_main_address());
        personnumber.setText("需要人数：" + project.getNeed_person_number() + "");
        manager.setText("联系人：" + project.getProject_manager_account());
        introduction.setText("项目介绍：" + project.getProject_introduction());
        final Button tech = (Button) findViewById(R.id.tech);
        final Button market = (Button) findViewById(R.id.market);
        final Button beauty = (Button) findViewById(R.id.beauty);
        final Button product = (Button) findViewById(R.id.product);
        final Button manage = (Button) findViewById(R.id.manage);
        if (project.getProject_label().equals(null)){
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
            for (int i = 0; i < project.getProject_label().size(); i++){
                Project project1 = project;
                if (project1.getProject_label().get(i).getLabel_name().equals("技术")){
                    tech.setTextColor(0xff008875);
                }else if(project1.getProject_label().get(i).getLabel_name().equals("市场")){
                    market.setTextColor(0xff008875);
                }else if(project1.getProject_label().get(i).getLabel_name().equals("美工")){
                    beauty.setTextColor(0xff008875);
                }else if(project1.getProject_label().get(i).getLabel_name().equals("产品")){
                    product.setTextColor(0xff008875);
                }else if(project1.getProject_label().get(i).getLabel_name().equals("运营")){
                    manage.setTextColor(0xff008875);
                }
            }
        }
    }
}
