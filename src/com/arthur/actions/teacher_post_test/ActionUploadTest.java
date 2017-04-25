package com.arthur.actions.teacher_post_test;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by zhangyu on 15/02/2017.
 */
public class ActionUploadTest extends ActionSupport implements ServletRequestAware, ServletResponseAware {


    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * Using POST Method
     */
    public void uploadtest() {
        Gson gson = new Gson();
        Type ctype = new com.google.gson.reflect.TypeToken<CTeacherPostTest>() {
        }.getType();
        Type stype = new com.google.gson.reflect.TypeToken<STeacherPostTest>() {
        }.getType();

        try {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(request.getInputStream(), "UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println("1-->" + sb);
            String json = sb.toString();
            CTeacherPostTest cData = gson.fromJson(json, ctype);
            System.out.println("2-->" + cData);
            STeacherPostTest sData = new STeacherPostTest("id123456789");

            //发送返回信息
            response.setCharacterEncoding("utf-8");
            String result = gson.toJson(sData, stype);
            System.out.println("3-->" + result);
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
