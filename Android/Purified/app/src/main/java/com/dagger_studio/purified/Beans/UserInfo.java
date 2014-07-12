package com.dagger_studio.purified.Beans;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.dagger_studio.purified.R;

import java.io.File;
import java.util.Date;

/**
 * U
 * Created by alex on 14-7-7.
 */
public class UserInfo {

    public static UserInfo getInstance(Activity activity){
        if (userInfo == null){
            userInfo = new UserInfo();
            userInfo.setActivity(activity);
            userInfo.setAvatarBitmapByFile(activity.getApplicationContext().getFilesDir()+"/images/avatar.png");
        }
        return userInfo;
    }


    /**
     * 根据文件路径来设置用户的头像图片Bitmap
     *
     * @param avatarImagePath 用户的头像文件的路径，通常是AppPath/images/avatarImage.png
     * @return true:用户自定义头像存在，加载自定义头像
     *         false:用户自定义头像不存在，加载默认的空头像
     */
    public boolean setAvatarBitmapByFile(String avatarImagePath){
        File file = new File(avatarImagePath);
        if (file.exists()){
            avatarBitmap = BitmapFactory.decodeFile(avatarImagePath);
            return true;
        }else{
            avatarBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.icon_user_default);
            return false;
        }
    }

    /**
     * 为了让DAO实现相应方法必须使用一个Activity来传入相应方法，因此需要一个setActivity
     * @param activity 传入一个Activity来便于DAO实现方法
     *
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Bitmap getAvatarBitmap() {
        return avatarBitmap;
    }

    public void setAvatarBitmap(Bitmap avatarBitmap) {
        this.avatarBitmap = avatarBitmap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static UserInfo userInfo = null;


    //========================  Declaration  =================

    private Activity activity;
    private String username = null;
    private String email = null;
    private String moto = null;
    private Date date = new Date();
    private String gender = null;
    private Bitmap avatarBitmap = null;


}
