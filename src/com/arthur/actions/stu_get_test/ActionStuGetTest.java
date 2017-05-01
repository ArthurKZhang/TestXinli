package com.arthur.actions.stu_get_test;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class ActionStuGetTest extends BaseAction {
    public void stuGetTest() {
        CStuGetTest c = new RqsTool<CStuGetTest>() {
        }.rqsTool(request);

        String testid = c.getTestId();
        ObjectId fileId = new ObjectId(testid);

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(MongoManager.DB_NAME1);
        GridFSBucket gridFSBucket = GridFSBuckets.create(database, MongoManager.GRIDFS_TEACHER_TEST);
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileId);

        String testAsJson;
        try {
            testAsJson = IOUtils.toString(downloadStream, "UTF-8");
        } catch (IOException e) {
            testAsJson = "";
            e.printStackTrace();
        }finally {
            downloadStream.close();
        }

        SStuGetTest s = new SStuGetTest(testAsJson);
        new RpnTool<SStuGetTest>().send(response,s);
    }
}
