package com.example.whowhenhow.hugleg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whowhenhow.hugleg.Const;
import com.example.whowhenhow.hugleg.R;
import com.example.whowhenhow.hugleg.bean.Person_info;
import com.example.whowhenhow.hugleg.service.UserService;
import com.example.whowhenhow.hugleg.util.Util;

import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by 黄国正 on 2017/12/24.
 */

public class User_name extends AppCompatActivity {
    private UserService userService;
    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.user_name);
        final EditText name = (EditText) findViewById(R.id.name_edittext);
        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        final int user_id = personInfo.getUser_id();
        Button finish = (Button) findViewById(R.id.finish);

        /*确认修改*/
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nickname = name.getText().toString();
                if(nickname.equals("")){
                    Toast.makeText(User_name.this,"名字不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                    userService = retrofit.create(UserService.class);
                    userService.changeUserInfo(user_id,"user_nickname",nickname)
                            .subscribeOn(rx.schedulers.Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Map<String, String>>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onNext(Map<String, String> stringStringMap) {

                                    if(stringStringMap.get("user_changed_info").equals("")){
                                        Toast.makeText(User_name.this,"修改失败！",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(User_name.this,"修改成功！",Toast.LENGTH_SHORT).show();
                                        personInfo.setUser_nickname(nickname);
                                    }
                                }
                            });
                }
            }
        });
        /**返回**/
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_name.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
}
