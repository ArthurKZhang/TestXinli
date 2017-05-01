package com.arthur.actions.stu_get_notify;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class CStuGetNotify {
    private String userId;

    @Override
    public String toString() {
        return "CStuGetNotify{" +
                "userId='" + userId + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CStuGetNotify(String userId) {

        this.userId = userId;
    }
}
