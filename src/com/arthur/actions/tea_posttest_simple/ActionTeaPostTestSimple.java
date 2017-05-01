package com.arthur.actions.tea_posttest_simple;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSFindIterable;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangyu on 28/04/2017.
 */
public class ActionTeaPostTestSimple extends BaseAction {
    public void uploadtest() {
        CTeacherPostTest cdata = new RqsTool<CTeacherPostTest>() {
        }.rqsTool(request);

        String userName = cdata.getUserName();
        String testName = cdata.getTestName();
        String testAsJson = cdata.getTestAsJson();

        // 题目插入数据库，GridFS文件存储
        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        //存储题目
        GridFSBucket gridFSBucket = GridFSBuckets.create(db, MongoManager.GRIDFS_TEACHER_TEST);
        ObjectId fileId, oldId = null;

        //重新写入新的测试题
        System.out.println("TestAsString length: " + testAsJson.getBytes(StandardCharsets.UTF_8).length);
        InputStream streamToUploadFrom = new ByteArrayInputStream(testAsJson.getBytes(StandardCharsets.UTF_8));

        //如果已经存在，那么替换掉老的
        GridFSFindIterable fsIte = gridFSBucket.find(new Document("filename", testName));
        if (fsIte.iterator().hasNext()) {
            oldId = fsIte.iterator().next().getObjectId();
//            gridFSBucket.delete(oldId);//TODO 如果删掉再重新写的话id会发生变化
//            BsonObjectId bsonObjectId = new BsonObjectId(fileId);
//            gridFSBucket.uploadFromStream(bsonObjectId, testName, streamToUploadFrom);
//            System.out.println("remove old test:" + testName + "[" + fileId + "]");
        }

//        System.out.println("Test is not exist: ");
        fileId = gridFSBucket.uploadFromStream(testName, streamToUploadFrom);
        System.out.println("new test: " + testName + " id: " + fileId.toHexString());
        //把 文件id 更新进教师用户的表中
        MongoCollection collection = db.getCollection(MongoManager.COLLECTION_USER);
        FindIterable<Document> findIterable = collection.find(new Document("name", userName));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document doc = mongoCursor.next();
        List<String> testIdList = (List<String>) doc.get("testidlist");
        if (testIdList == null) {
            System.out.println("Test idList is null");
            testIdList = new ArrayList<String>();
            testIdList.add(fileId.toHexString());
            doc.put("testidlist", testIdList);
        } else {
            if (oldId!=null) testIdList.remove(oldId.toHexString());

            List<String> newList = new ArrayList<>(testIdList);
            if (!newList.contains(fileId.toHexString())) {
                System.out.println("TestIdList do not contain:" + fileId.toHexString());
                newList.add(fileId.toHexString());
                doc.replace("testidlist", testIdList, newList);
            }
        }
        collection.replaceOne(eq("name", userName), doc);


        STeacherPostTest sData = new STeacherPostTest(fileId.toHexString());
        new RpnTool<STeacherPostTest>().send(response, sData);
    }
}
