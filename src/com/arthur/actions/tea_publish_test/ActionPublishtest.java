package com.arthur.actions.tea_publish_test;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;


/**
 * Created by zhangyu on 28/04/2017.
 */
public class ActionPublishtest extends BaseAction {

    public void publish() {
        CPublishTest c = new RqsTool<CPublishTest>() {
        }.rqsTool(request);

        System.out.println("ActionPublishtest:"+c.toString()+"<end>");
        Date startDate = c.getsDate();
        Date endDate = c.geteDate();
        String enrollYear = c.getEnrollYear();
        String teacherName = c.getTeacherName();
        String testId = c.getTestid();
        String forInstitude = c.getUserInstitution();

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        MongoCollection collection = db.getCollection(MongoManager.COLLECTION_PUBLISH);
        Document doc = new Document("startDate",startDate)
                .append("endDate",endDate)
                .append("forEnrollYear",enrollYear)
                .append("teacherName",teacherName)
                .append("testId",testId)
                .append("forInstitude",forInstitude);

        collection.insertOne(doc);
        ObjectId id = doc.getObjectId("_id");

        SPublishTest s = new SPublishTest("success");
        new RpnTool<SPublishTest>().send(response,s);
    }
}
