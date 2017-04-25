package com.arthur.actions.register;

import com.arthur.actions.BaseAction;
import com.arthur.actions.login.ActionLogin;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by zhangyu on 18/04/2017.
 */
public class ActionRegister extends BaseAction {
    Logger logger = LogManager.getLogger(ActionLogin.class);

    public void register() {

        CRegister cRegister = new RqsTool<CRegister>() {
        }.rqsTool(request);

        SRegister sRegister = null;

        RpnTool<SRegister> rpnTool = new RpnTool<SRegister>();

        if (cRegister == null) {
            logger.error("ActionRegister.CRegister received is null");
            System.err.println("ActionRegister.CRegister received is null");
        }
        String name = cRegister.getName();

        //判断名字，用户名不可重复
        MongoClient mongoClient = MongoManager.getMongoClient();
//                new MongoClient(MongoManager.HOST, MongoManager.PORT);
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        MongoCollection collection = db.getCollection("user");

        FindIterable<Document> findIterable = collection.find(new Document("name", name));

        MongoCursor<Document> mongoCursor = findIterable.iterator();
        if (mongoCursor.hasNext()) {
            //用户名已经存在，返回错误的结果
            sRegister = new SRegister("error", null);
            rpnTool.send(response, sRegister);
//            send(sRegister);
            return;
        }

        //用户名不存在->不存在该用户，存储注册信息，返回用户ID
        Document newUser = new Document("name", name)
                .append("passwd", cRegister.getPassword())
                .append("institution", cRegister.getInstitution())
                .append("enrolDate", cRegister.getEnrollmentDate())
                .append("type",cRegister.getType());
        collection.insertOne(newUser);
        ObjectId id = newUser.getObjectId("_id");
        System.out.println("-----id：" + id.toString());

        //返回注册成功的信息
        sRegister = new SRegister("success", id.toString());
        rpnTool.send(response, sRegister);
    }
}
