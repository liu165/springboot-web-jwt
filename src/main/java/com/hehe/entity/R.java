package com.hehe.entity;

import java.util.List;

/**
 * ClassName: R <br/>
 * Description: <br/>
 * date: 2019/10/21 16:06<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
public class R<T> {
    public  static final String success_state = "ok";
    public  static final String failed_state = "fail";
    public List<T> data;
    public String msg;
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
