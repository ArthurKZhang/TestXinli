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

        //发送返回信息
        response.setCharacterEncoding("utf-8");
        String result = gson.toJson(object, stype);
        logger.info("send response: "+result);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            logger.error("cannot send back response");
            e.printStackTrace();
        }
    }

}
