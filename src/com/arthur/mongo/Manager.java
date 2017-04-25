package com.arthur.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * Created by zhangyu on 16/04/2017.
 */
public class Manager {
    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String DB_NAME1 = "DB";
//    public final String COLLECTION_NAME = "IPPacket";

    private static MongoClient mongoClient = null;

    /**
     * 线程安全的单例模式
     * @return
     */
    public static MongoClient getMongoClient(){
//        Arrays.asList(new ServerAddress("localhost", 27017),
//                new ServerAddress("localhost", 27018),
//                new ServerAddress("localhost", 27019));
        if (mongoClient ==null){
            synchronized (Manager.class){
                if (mongoClient ==null){
                    MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
                    buide.connectionsPerHost(100);// 与目标数据库可以建立的最大链接数
                    buide.connectTimeout(1000 * 60 * 20);// 与数据库建立链接的超时时间
                    buide.maxWaitTime(100 * 60 * 5);// 一个线程成功获取到一个可用数据库之前的最大等待时间
                    buide.threadsAllowedToBlockForConnectionMultiplier(100);
                    buide.maxConnectionIdleTime(0);
                    buide.maxConnectionLifeTime(0);
                    buide.socketTimeout(0);
                    buide.socketKeepAlive(true);
                    MongoClientOptions myOptions = buide.build();
                    mongoClient = new MongoClient(new ServerAddress(Manager.HOST, Manager.PORT),myOptions);
                }
            }
        }
        return mongoClient;

    }


    public boolean initDB(){
        MongoClient mongoClient = new MongoClient(Manager.HOST, Manager.PORT);
        MongoDatabase db = mongoClient.getDatabase(Manager.DB_NAME1);

        try {
//            if (db.getCollection("user"))
            db.createCollection("user");
            System.out.println("Collection--" + "user" + "--created");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            mongoClient.close();
        }

        return false;
    }
}
