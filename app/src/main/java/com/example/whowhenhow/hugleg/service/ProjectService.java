package com.example.whowhenhow.hugleg.service;

import com.example.whowhenhow.hugleg.bean.Label;
import com.example.whowhenhow.hugleg.bean.Project;

import java.util.List;
import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ERIC on 2018/1/3.
 */

public interface ProjectService {

    // 发布项目帖子
    @FormUrlEncoded
    @POST("/addProject")
    Observable<Map<String, Integer>> addProject(@Field("user_account") String userAccount, @Field("project_name") String projectName, @Field("need_person_number") int needPersonNumber, @Field("project_introduction") String projectIntroduction, @Field("project_main_address") String projectMainAddress, @Field("project_label") List<String> projectLabel);

    // 随机获取5条项目帖子
    @FormUrlEncoded
    @POST("/getRanProject")
    Observable<List<Project>> getRanProject(@Field("void") String anything); //参数传null就可以了

    // 按标签获取项目帖子
    @FormUrlEncoded
    @POST("/getLabelProject")
    Observable<List<Project>> getLabelProject(@Field("label_name") String labelName);

    // 参加项目
    @FormUrlEncoded
    @POST("/joinProject")
    Observable<Map<String, String>> joinProject(@Field("user_account") String userAccount, @Field("project_id") int projectId);

    // 退出项目
    @FormUrlEncoded
    @POST("/quitProject")
    Observable<Map<String, String>> quitProject(@Field("user_account") String userAccount, @Field("project_id") int projectId);

    // 删除项目
    @FormUrlEncoded
    @POST("/deleteProject")
    Observable<Map<String, String>> deleteProject(@Field("project_id") int projectId);
}
