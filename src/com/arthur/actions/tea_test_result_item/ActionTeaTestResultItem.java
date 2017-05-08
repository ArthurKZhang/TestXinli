package com.arthur.actions.tea_test_result_item;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.mongo.bean.TestResultItemBean;
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
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class ActionTeaTestResultItem extends BaseAction {
    public void getResultItem() {
        CTeaTestResultItem c = new RqsTool<CTeaTestResultItem>() {
        }.rqsTool(request);

        String teacherId = c.getTeaId();

        String testId = null;
        String testName = null;
        String answerNum = "0";

        List<TestResultItemBean> items = new ArrayList<>();//要返回的数据

        MongoClient client = MongoManager.getMongoClient();
        MongoDatabase db = client.getDatabase(MongoManager.DB_NAME1);

        //TODO 先访问用户数据库，得到所拥有的所有测试题的ID
        MongoCollection userColl = db.getCollection(MongoManager.COLLECTION_USER);
        MongoCollection publColl = db.getCollection(MongoManager.COLLECTION_PUBLISH);
        MongoCollection testResultColl = db.getCollection(MongoManager.COLLECTION_TESTRESULT);

        MongoCursor<Document> teaCur = userColl.find(new Document("_id", new ObjectId(teacherId))).iterator();
        List<String> testidlist = (List<String>) teaCur.next().get("testidlist");
        if (testidlist != null) {
            for (String tid : testidlist) {
                //TODO 再去publish数据库，找到已经答题结束的测试题的ID
                Document pubDoc = (Document) publColl.find(new Document("testId", tid)).iterator().next();
                Date endDate = (Date) pubDoc.get("endDate");
                testName = (String) pubDoc.get("testName");
                if (endDate.before(new Date())) {
                    // TODO 去testresult中得到答题人数
                    MongoCursor<Document> testResultCur = testResultColl.find(new Document("tesId", tid)).iterator();
                    if (testResultCur.hasNext()) {
                        List<String> hisIds = (List<String>) testResultCur.next().get("historyIds");
                        answerNum = hisIds == null ? "0" : String.valueOf(hisIds.size());

                        items.add(new TestResultItemBean(tid, testName, answerNum));
                    }
                }
            }

        }


        Gson gson = new Gson();
        Type t = new TypeToken<List<TestResultItemBean>>() {
        }.getType();
        STeaTestResultItem s = new STeaTestResultItem(gson.toJson(items, t));
        new RpnTool<STeaTestResultItem>().send(response, s);
    }
}
