package com.example.whowhenhow.hugleg.bean;

import java.io.Serializable;

/**
 * Created by 黄国正 on 2017/12/23.
 */

public class Person_info implements Serializable {
    private int id;
    private String tagid;
    private String name;
    private String gender;
    private String life;
    private String place;
    private String country;
    private String story;

    public int getId(){return id;}
    public String getTagid(){return tagid;}
    public String getName(){return name;}
    public String getGender(){return gender;}
    public String getLife(){return life;}
    public String getPlace(){return place;}
    public String getCountry(){return country;}
    public String getStory(){return story;}

    public void setId(int id){this.id = id;}
    public void setTagid(String tagid){this.tagid = tagid;}
    public void setName(String name) {this.name = name;}
    public void setGender(String gender) {this.gender = gender;}
    public void setLife(String life) {this.life = life;}
    public void setPlace(String place) {this.place = place;}
    public void setCountry(String country) {this.country = country;}
    public void setStory(String story) {this.story = story;}
}
