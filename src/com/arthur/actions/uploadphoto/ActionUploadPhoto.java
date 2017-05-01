package com.arthur.actions.uploadphoto;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class ActionUploadPhoto extends BaseAction {
    public void uploadPhoto() {
        CUploadPhoto cUploadPhoto = new RqsTool<CUploadPhoto>() {
        }.rqsTool(request);

        String photo = cUploadPhoto.getPhoto();
        String userName = cUploadPhoto.getUserName();

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase(MongoManager.DB_NAME1);

        //存储图片 以用户名为推案的名字，得到图片的id
        GridFSBucket gridFSBucket = GridFSBuckets.create(db, MongoManager.GRIDFS_USER_PHOTO);
        ObjectId fileId;
        //TODO 如果头像存在，那么替换掉老的
//        GridFS gridFS = new GridFS((DB) db);
//        GridFSDBFile gridFSFile = gridFS.findOne(userName);
        GridFSFindIterable fsIte = gridFSBucket.find(new Document("filename",userName));
        if (fsIte.iterator().hasNext()){
            fileId = fsIte.iterator().next().getObjectId();
            //删除掉旧的
            gridFSBucket.delete(fileId);
            System.out.println("remove old photo:"+fileId);
        }
        //重新写入新的头像
        InputStream streamToUploadFrom = new ByteArrayInputStream(photo.getBytes(StandardCharsets.UTF_8));
        fileId = gridFSBucket.uploadFromStream(userName, streamToUploadFrom);

        try {
            streamToUploadFrom.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The fileId of the uploaded file is: " + fileId.toHexString());

        //更新用户的document，添加头像id
        MongoCollection collection = db.getCollection("user");
        FindIterable<Document> findIterable = collection.find(new Document("name", userName));

        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Document doc = mongoCursor.next();
        doc.put("photoid", fileId.toHexString());
        collection.replaceOne(eq("name", userName), doc);


        SUploadPhoto sUploadPhoto = new SUploadPhoto(fileId.toHexString());
        new RpnTool<SUploadPhoto>().send(response, sUploadPhoto);
    }
}
