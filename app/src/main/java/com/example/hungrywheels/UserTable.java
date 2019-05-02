package com.example.hungrywheels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "user",primaryKeys = "username")
public class UserTable implements Serializable {

    @NonNull
    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name="password")
    private String password;

    @ColumnInfo(name="age")
    private String age;

    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "fblogin")
    private String facebook;

    @ColumnInfo(name = "pic")
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPicflag() {
        return picflag;
    }

    public void setPicflag(String picflag) {
        this.picflag = picflag;
    }

    @ColumnInfo(name = "picflag")
    private String picflag;


    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @ColumnInfo(name = "twitterlogin")
    private String twitter;


    @Ignore
    public UserTable(@NonNull String username, String password, String age, String name, String email, String phone, String pic,String picflag) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pic=pic;
        this.picflag=picflag;
    }

    public UserTable() {
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String lastName) {
        this.email = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
