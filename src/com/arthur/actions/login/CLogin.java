package com.arthur.actions.login;

/**
 * Created by zhangyu on 21/02/2017.
 */
/**
 * 属性名	中文	格式	举例	备注
 * name	    用户名	String	pang
 * password	密码	String	123321
 */
public class CLogin {

    private String name;
    private String password;

    public CLogin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "CLogin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
