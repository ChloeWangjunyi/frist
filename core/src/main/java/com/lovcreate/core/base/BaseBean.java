package com.lovcreate.core.base;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：yuanYe创建于2016/11/14
 * QQ：962851730
 * 
 * 服务端接口返回实体基本类, 对应后端制定的json串格式
 */
public class BaseBean {

    // 状态码
    private String code;

    // 信息
    @SerializedName("msg")
    private String message;

    // 具体数据内容(json)
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new StringBuilder("BaseBean{").append("code='").append(code).append('\'').append(", message='")
            .append(message).append('\'').append(", data='").append(data).append('\'').append('}').toString();
    }
}
