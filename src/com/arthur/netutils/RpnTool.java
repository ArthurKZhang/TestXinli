package com.arthur.netutils;


import com.arthur.actions.login.SLogin;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Calendar;

/**
 * Created by zhangyu on 16/04/2017.
 */
public class RpnTool<T> {

    private  Logger logger;

    public RpnTool(){
        logger = LogManager.getLogger(this);
    }

    public void send(HttpServletResponse response, Object object) {
        Gson gson = new Gson();
        Type stype = new com.google.gson.reflect.TypeToken<T>() {
        }.getType();

        String result = gson.toJson(object, stype);

        //发送返回信息
        response.setCharacterEncoding("utf-8");

        try {
            int length = result.getBytes("UTF-8").length;
            System.out.println("send response, length:"+length);
            response.setContentLength(length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("send response: "+result);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            System.err.println("cannot send back response");
            e.printStackTrace();
        }
    }

}
