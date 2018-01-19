package com.example.whowhenhow.hugleg.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERIC on 2018/1/3.
 */

public class Project implements Serializable {
    private int project_id;
    private String project_name;
    private String project_manager_account;
    private int need_person_number;
    private String project_introduction;
    private String project_main_address;
    private List<Label> project_label;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_manager_account() {
        return project_manager_account;
    }

    public void setProject_manager_account(String project_manager_account) {
        this.project_manager_account = project_manager_account;
    }

    public int getNeed_person_number() {
        return need_person_number;
    }

    public void setNeed_person_number(int need_person_number) {
        this.need_person_number = need_person_number;
    }

    public String getProject_introduction() {
        return project_introduction;
    }

    public void setProject_introduction(String project_introduction) {
        this.project_introduction = project_introduction;
    }

    public String getProject_main_address() {
        return project_main_address;
    }

    public void setProject_main_address(String project_main_address) {
        this.project_main_address = project_main_address;
    }

    public List<Label> getProject_label() {
        return project_label;
    }

    public void setProject_label(List<Label>  project_label) {
        this.project_label = project_label;
    }
}
