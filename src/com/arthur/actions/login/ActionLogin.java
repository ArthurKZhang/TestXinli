package com.arthur.actions.login;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.Manager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Calendar;

/**
 * Created by zhangyu on 15/02/2017.
 */
public class ActionLogin extends BaseAction {
    Logger logger = LogManager.getLogger(ActionLogin.class);

    /**
     * Using POST Method
     */
    public void login() {
        CLogin cLogin = new RqsTool<CLogin>() {
        }.rqsTool(request);

        SLogin sLogin = null;
        //检查用户名和密码
        MongoClient mongoClient = Manager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(Manager.DB_NAME1);

        MongoCollection collection = db.getCollection("user");
        String name = cLogin.getName();
        String passwd = cLogin.getPassword();

        //用户名不存在，密码错误两种情况
        MongoCursor<Document> mongoCursor = collection.find(new Document("name", name)).iterator();
        if (mongoCursor.hasNext()) {
            Document d = mongoCursor.next();
            //用户名存在，获取密码
            if (((String) d.get("passwd")).equals(passwd)) {
                //用户名和密码匹配，"0"表示登录成功，
                sLogin = new SLogin("0",
                        d.getObjectId("_id").toString(),
                        d.getString("institution"),
                        d.getDate("enrolDate"),
                        d.getString("type"));
            } else {
                //密码错误, "1"表示密码错误
                sLogin = new SLogin("1", null, null, null, null);
            }

        } else {
            //用户名不存在 "-1" 表示用户名不存在
            sLogin = new SLogin("-1", null, null, null, null);
        }

        new RpnTool<SLogin>().send(response, sLogin);

    }


    /**
     * Using GET Method
     */
    public void loginOld() {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<String>() {
        }.getType();
        String result = null;

        if (!request.getMethod().equalsIgnoreCase("get")) {
            System.out.println("request method used by client is not GET");
            return;
        }


        String uname = request.getParameter("uname");
        String upasswd = request.getParameter("upasswd");

        if (uname.equals("123") && upasswd.equals("123")) {

            try {
                response.setCharacterEncoding("utf-8");
                result = gson.toJson("success", type);
                System.out.println(result);
                response.getWriter().write(result);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("login exception in success");
            }
        } else {
            try {
                response.setCharacterEncoding("utf-8");
                result = gson.toJson("fail", type);
                System.out.println(result);
                response.getWriter().write(result);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("login exception in fail");
            }
        }
    }
}
