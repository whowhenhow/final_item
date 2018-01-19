package com.example.whowhenhow.hugleg.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class User_password extends AppCompatActivity {
    private UserService userService;
    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.user_password);
        final Person_info personInfo = (Person_info)getIntent().getSerializableExtra(MainActivity.SER_KEY);
        final int user_id = personInfo.getUser_id();
        final String password = personInfo.getUser_password();
        Log.d("dedede",password);
        final EditText oldpass = (EditText) findViewById(R.id.old_password_edittext);
        final EditText newpass = (EditText) findViewById(R.id.new_password_edittext);
        final EditText confirmpass = (EditText) findViewById(R.id.confirm_new_password_edittext);
        Button finish = (Button) findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passin = oldpass.getText().toString();
                String newpassin = newpass.getText().toString();
                String newpasscon = confirmpass.getText().toString();
                Log.d("dededede",passin+" "+newpassin+" "+newpasscon);
                if(!passin.equals(password)){
                    Toast.makeText(User_password.this,"旧密码错误",Toast.LENGTH_SHORT).show();
                }
                else if(!newpassin.equals(newpasscon)||newpassin.equals("")||newpasscon.equals("")){
                    Toast.makeText(User_password.this,"密码不匹配或为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    Retrofit retrofit = Util.createRetrofit(Const.BASEURL);
                    userService = retrofit.create(UserService.class);
                    userService.changeUserInfo(user_id,"user_password",newpassin)
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
                                        Toast.makeText(User_password.this,"修改失败！",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(User_password.this,"修改成功！",Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(User_password.this, Aboutme.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable(MainActivity.SER_KEY,personInfo);
                intent.putExtras(mBundle);
                startActivity(intent);
            }
        });
    }
}
