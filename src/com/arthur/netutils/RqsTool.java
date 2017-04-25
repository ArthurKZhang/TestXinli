package com.arthur.netutils;


import com.arthur.actions.register.CRegister;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

import javax.lang.model.util.Types;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhangyu on 16/04/2017.
 */
public class RqsTool<T> {

    private Logger logger;

    public RqsTool() {
        logger = LogManager.getLogger(this);
        this.type = getSuperclassTypeParameter(this.getClass());
    }

    public <T> T rqsTool(HttpServletRequest request) {
        logger = LogManager.getLogger(RqsTool.class.getName());

        BufferedReader br = null;
        Gson gson = new Gson();

        try {
            br = new BufferedReader(new
                    InputStreamReader(request.getInputStream(), "UTF-8"));
        } catch (IOException e) {
            logger.error("request cannot getInputStream");
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error("readLine exception");
            e.printStackTrace();
        }
        logger.info("receive: " + sb.toString());
        System.out.println(sb.toString());
        String json = sb.toString();

        Object C = gson.fromJson(json, type);

        return (T) C;
    }


    private Type type;

    public Type getType() {
        return this.type;
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        } else {
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//            ParameterizedType parameterized = (ParameterizedType)superclass;
//            return parameterized.getActualTypeArguments()[0];
            return clazz;
        }
    }

}
