package com.arthur.actions.tea_down_cache;

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
 * Created by zhangyu on 30/04/2017.
 */
public class ActionTeaDownCache extends BaseAction {
    //    downcache.action
    public void downcache() {
        CDownTeaTestCache c = new RqsTool<CDownTeaTestCache>() {
        }.rqsTool(request);
        String testID = c.getTestId();
        String testName = c.getTestName();

        ObjectId fileId = new ObjectId(testID);

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(MongoManager.DB_NAME1);
        GridFSBucket gridFSBucket = GridFSBuckets.create(database, MongoManager.GRIDFS_TEACHER_TEST);
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileId);
        try {
            String test = IOUtils.toString(downloadStream, "UTF-8");
            SDownTeaTestCache s = new SDownTeaTestCache(testID, test);
            new RpnTool<SDownTeaTestCache>().send(response, s);
        } catch (IOException e) {
            System.err.println("ActionTeaDownCache: cache may not found-(IOUtils.toString Exception)");
            SDownTeaTestCache s = new SDownTeaTestCache(testID, "");
            new RpnTool<SDownTeaTestCache>().send(response, s);
            e.printStackTrace();
        } finally {
            downloadStream.close();
        }

    }
}
