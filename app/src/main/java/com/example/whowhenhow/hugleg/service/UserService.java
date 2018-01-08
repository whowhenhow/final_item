package com.example.whowhenhow.hugleg.service;

import com.example.whowhenhow.hugleg.bean.Label;
import com.example.whowhenhow.hugleg.bean.Person_info;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ERIC on 2018/1/3.
 */

public interface UserService {

    // 注册用户
    @FormUrlEncoded
    @POST("/addUser")
    Observable<Map<String, String>> addUser(@Field("user_account") String userAccount, @Field("user_password") String userPassword);

    // 登录用户
    @FormUrlEncoded
    @POST("/login")
    Observable<Person_info> login(@Field("user_account") String userAccount, @Field("user_password") String userPassword);

    // 获取用户信息
    @GET("/getUserInfo")
    Observable<Person_info> getUserInfo(@Query("user_account") String userAccount);

    // 修改用户头像
    @POST("/changeUserAvatar")
    Observable<Map<String, String>> changeUserAvatar(@Body RequestBody body);

    // 修改用户各项信息
    @FormUrlEncoded
    @POST("/changeUserInfo")
    Observable<Map<String, String>> changeUserInfo(@Field("user_id") int userId, @Field("user_info_name") String user_info_name, @Field("user_info") String user_info);

    // 修改个人标签
    @FormUrlEncoded
    @POST("/changeUserLabel")
    Observable<List<String>> changeUserLabel(@Field("user_id") int userId, @Field("user_label") List<String> userLabel);

}
