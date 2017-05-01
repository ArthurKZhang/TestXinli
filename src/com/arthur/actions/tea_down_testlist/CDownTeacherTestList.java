package com.arthur.actions.tea_down_testlist;

/**
 * Created by zhangyu on 26/04/2017.
 */
public class CDownTeacherTestList {
    private String userName;

    @Override
    public String toString() {
        return "CDownTeacherTestList{" +
                "userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CDownTeacherTestList(String userName) {

        this.userName = userName;
    }
}
