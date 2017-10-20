package com.lovcreate.core.bean;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 姚博 on 2017/8/6.
 */

public class UserBean {

    private Integer id;

    private String name;

    private Integer sex;

    private String imgUrl;

    private String mobile;

    private String password;

    private Integer isActivate;

    private Integer isApplying;

    private String role;

    private String updateUserId;

    private Date createTime;

    private Integer delStatus;

    private Date updateTime;

    private String token;

    private String channelId;

    private String iosChannelId;

    private Integer deviceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(Integer isActivate) {
        this.isActivate = isActivate;
    }

    public Integer getIsApplying() {
        return isApplying;
    }

    public void setIsApplying(Integer isApplying) {
        this.isApplying = isApplying;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time = Long.valueOf(createTime);
            String d = format.format(time);
            Date date = new Date("d");
            this.createTime = date;
        }catch (Exception e){
            Log.e("Exception",e.toString());
        }
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long time = Long.valueOf(updateTime);
            String d = format.format(time);
            Date date = new Date("d");
            this.updateTime = date;
        }catch (Exception e){
            Log.e("Exception",e.toString());
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIosChannelId() {
        return iosChannelId;
    }

    public void setIosChannelId(String iosChannelId) {
        this.iosChannelId = iosChannelId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }
}