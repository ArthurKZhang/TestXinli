package com.arthur.actions.stu_get_notify;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.mongo.bean.NotifyRecord;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class ActionStuGetNotify extends BaseAction {
    public void getnotify() {
        CStuGetNotify c = new RqsTool<CStuGetNotify>() {
        }.rqsTool(request);

        String stuId = c.getUserId();
        if(stuId==null){
            SStuGetNotify s = new SStuGetNotify(null);
            new RpnTool<SStuGetNotify>().send(response, s);
        }
        Date now = new Date();

        MongoClient client = MongoManager.getMongoClient();
        MongoDatabase db = client.getDatabase(MongoManager.DB_NAME1);

        MongoCollection<Document> publishCol = db.getCollection(MongoManager.COLLECTION_PUBLISH);
        MongoCollection<Document> userCol = db.getCollection(MongoManager.COLLECTION_USER);
        MongoCollection<Document> historyCol = db.getCollection(MongoManager.COLLECTION_HISTORY);

        BasicDBObject query = new BasicDBObject();
//        query.put("_id", ;
        MongoCursor<Document> userCurser = userCol.find(new Document("_id",new ObjectId(stuId))).iterator();
        Document user = null;
        if (userCurser.hasNext()) {
            user = userCurser.next();
        }
        if (user == null) return;

        String userInstitution = (String) user.get("institution");
        Date userEnrolDate = (Date) user.get("enrolDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String Y = sdf.format(userEnrolDate);
        System.out.println("user:" + user.get("name") + Y + "入学");

        MongoCursor<Document> publishcurser = publishCol.find(
                and(
                        eq("forEnrollYear", Y),
                        eq("forInstitude", userInstitution),
                        gte("endDate", now)
                )).iterator();

        List<NotifyRecord> notifys = new ArrayList<>();
        MongoCursor<Document> historyCursor;
        while (publishcurser.hasNext()) {
            Document publi = publishcurser.next();
            String testId = (String) publi.get("testId");
            //TODO 如果已经有做这套题的记录了，那么就跳过继续遍历
            historyCursor = historyCol.find(and(eq("testid", testId), eq("userid", stuId))).iterator();
            if (historyCursor.hasNext()) {
                continue;
            }
            String teaName = (String) publi.get("teacherName");
            String testName = (String) publi.get("testName");
            Date endDate = (Date) publi.get("endDate");
            NotifyRecord record = new NotifyRecord(testId, endDate, teaName, testName);
            System.out.println("add NotifyRecord" + record);
            notifys.add(record);
        }


        SStuGetNotify s = new SStuGetNotify(notifys);
        new RpnTool<SStuGetNotify>().send(response, s);
    }
}
