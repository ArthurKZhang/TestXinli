package com.arthur.actions.tea_down_testlist;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.mongo.bean.UploadRecord;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSFindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 26/04/2017.
 */
public class ActionDownTeacherTestList extends BaseAction {

    public void downTeacherTestList() {
        CDownTeacherTestList cData = new RqsTool<CDownTeacherTestList>() {
        }.rqsTool(request);

        String userName = cData.getUserName();

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        MongoCollection collection = db.getCollection(MongoManager.COLLECTION_USER);

        MongoCursor<Document> mongoCursor = collection.find(new Document("name", userName)).iterator();
        List<UploadRecord> records = new ArrayList<UploadRecord>();
        List<String> testIdList = null;

        //用户不存在,什么都不错做，因为这不应该发生
        if (!mongoCursor.hasNext()) {
            System.out.println("ActionDownTeacherTestList-userName:"+userName+"doesn't exist");
//            SDownTeacherTestList sData = new SDownTeacherTestList(records);
//            new RpnTool<>().send(response, sData);
            return;
        }
        Document d = mongoCursor.next();
        testIdList = (List<String>) d.get("testidlist");
        if (testIdList != null ) {
            GridFSBucket gridFSBucket = GridFSBuckets.create(db, MongoManager.GRIDFS_TEACHER_TEST);

            for (String id : testIdList) {
                GridFSFindIterable fsIte = gridFSBucket.find(new Document("_id",new ObjectId(id)));
                String filename = fsIte.iterator().next().getFilename();
                UploadRecord u = new UploadRecord(userName,filename,id,null);
                records.add(u);
            }
        }
        SDownTeacherTestList sData = new SDownTeacherTestList(records);
        new RpnTool<>().send(response, sData);
    }

}
