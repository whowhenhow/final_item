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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_information);

        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectInfo.this, ProjectActivity.class);
                startActivity(intent);
            }
        });

        /**绑定数据**/
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Project project = (Project) bundle.getSerializable("data");
        TextView projectname = (TextView) findViewById(R.id.project_name);
        TextView adress = (TextView) findViewById(R.id.project_address);
        TextView personnumber = (TextView) findViewById(R.id.personnumber);
        TextView manager = (TextView) findViewById(R.id.project_manager);
        TextView introduction = (TextView) findViewById(R.id.introduction);
        projectname.setText(project.getProject_name());
        adress.setText(project.getProject_main_address());
        personnumber.setText(project.getNeed_person_number() + "");
        manager.setText(project.getProject_manager_account());
        introduction.setText(project.getProject_introduction());
    }
}
