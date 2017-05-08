package com.arthur.actions.stu_hand_result;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class ActionStuHandResult extends BaseAction {
    public void stuHandResult() {
        CStuHandResult c = new RqsTool<CStuHandResult>() {
        }.rqsTool(request);

        String stuId = c.getStuid();
        String testId = c.getTestId();
        String resultAsJson = c.getResult();
        Gson gson = new Gson();
        Type t = new TypeToken<Map<Integer, Map<Integer, List<String>>>>() {
        }.getType();
        /**
         * Map<Integer, Map<Integer, List<String>>> resultMap
         * 第一个integer代表着题号
         * 第二个integer代表着这道题的题目类型 1-单选，2-多选，3-判断，4-简答
         * String数组承载着选项信息 单选，判断，简答都只有一个String， 多选可以有多个
         */
//        Map<Integer, Map<Integer, List<String>>> resultMap = gson.fromJson(resultAsJson,t);

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        MongoCollection collection = db.getCollection(MongoManager.COLLECTION_HISTORY);
        Document doc = new Document("testId",testId)
                .append("userId",stuId)
                .append("result",resultAsJson);

        collection.insertOne(doc);
        ObjectId hisId = doc.getObjectId("_id");

        //一套题的所有用户答题结果需要记录下来
        MongoCollection<Document> didTestCol = db.getCollection(MongoManager.COLLECTION_TESTRESULT);
        MongoCursor<Document> cursor = didTestCol.find(new Document("tesId",testId)).iterator();
        if (cursor.hasNext()){
            Document doc1 = cursor.next();
            List<String> hisIds = (List<String>) doc1.get("historyIds");
            List<String> newl = new ArrayList<>(hisIds);
            newl.add(hisId.toHexString());
            doc1.replace("historyIds",hisIds,newl);
            didTestCol.replaceOne(eq("testId", testId), doc1);
        }else {
            List<String> newll = new ArrayList<>();
            newll.add(hisId.toHexString());
            Document newDoc = new Document("tesId",testId)
                    .append("historyIds",newll)
                    .append("fileId","");
            didTestCol.insertOne(newDoc);
        }

        SStuHandResult s = new SStuHandResult("success");
        new RpnTool<SStuHandResult>().send(response, s);
    }
}
