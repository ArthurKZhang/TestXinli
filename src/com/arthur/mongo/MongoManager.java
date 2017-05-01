package com.arthur.mongo;

import com.arthur.mongo.codec.UploadRecordc;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * Created by zhangyu on 16/04/2017.
 */
public class MongoManager {
    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String DB_NAME1 = "DB";

    public static final String COLLECTION_USER = "user";
    public static final String COLLECTION_PUBLISH = "publish";
    public static final String COLLECTION_DIDTEST = "testresult";//记录一套题的所有用户的做题结果
    public static final String COLLECTION_HISTORY = "history";//记录学生的做题历史，里面有testId和userId

    public static final String GRIDFS_USER_PHOTO = "user_photo";
    public static final String GRIDFS_TEACHER_TEST = "teacher_test";


    private static MongoClient mongoClient = null;

    /**
     * 线程安全的单例模式
     *
     * @return
     */
    public static MongoClient getMongoClient() {
//        Arrays.asList(new ServerAddress("localhost", 27017),
//                new ServerAddress("localhost", 27018),
//                new ServerAddress("localhost", 27019));
        if (mongoClient == null) {
            synchronized (MongoManager.class) {
                if (mongoClient == null) {
                    CodecRegistry codecRegistry =
                            CodecRegistries.fromRegistries(
                                    CodecRegistries.fromCodecs(new UploadRecordc()),
                                    MongoClient.getDefaultCodecRegistry());

                    MongoClientOptions.Builder buide = new MongoClientOptions.Builder();
                    buide.connectionsPerHost(100);// 与目标数据库可以建立的最大链接数
                    buide.connectTimeout(1000 * 60 * 20);// 与数据库建立链接的超时时间
                    buide.maxWaitTime(100 * 60 * 5);// 一个线程成功获取到一个可用数据库之前的最大等待时间
                    buide.threadsAllowedToBlockForConnectionMultiplier(100);
                    buide.maxConnectionIdleTime(0);
                    buide.maxConnectionLifeTime(0);
                    buide.socketTimeout(0);
                    buide.socketKeepAlive(true);
                    buide.codecRegistry(codecRegistry);
                    MongoClientOptions myOptions = buide.build();
                    mongoClient = new MongoClient(new ServerAddress(MongoManager.HOST, MongoManager.PORT), myOptions);
                }
            }
        }
        return mongoClient;

    }


    public boolean initDB() {
        MongoClient mongoClient = new MongoClient(MongoManager.HOST, MongoManager.PORT);
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

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
