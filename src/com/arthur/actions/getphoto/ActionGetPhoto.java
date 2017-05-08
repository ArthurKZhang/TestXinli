package com.arthur.actions.getphoto;

import com.arthur.actions.BaseAction;
import com.arthur.mongo.MongoManager;
import com.arthur.netutils.RpnTool;
import com.arthur.netutils.RqsTool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import org.bson.types.ObjectId;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class ActionGetPhoto extends BaseAction{
    public void photo() {
        CDownloadPhoto cDownloadPhoto = new RqsTool<CDownloadPhoto>() {
        }.rqsTool(request);

        String photoid = cDownloadPhoto.getPhotoID();
        if (photoid==null){
            System.err.println("ActionGetPhoto-> photoid is null");
            return;
        }
        ObjectId fileId = new ObjectId(photoid);

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(MongoManager.DB_NAME1);
        GridFSBucket gridFSBucket = GridFSBuckets.create(database,MongoManager.GRIDFS_USER_PHOTO);
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileId);
        int fileLength = (int) downloadStream.getGridFSFile().getLength();
        System.out.println("ActionGetPhoto->"+photoid+"->file length:"+fileLength);

        byte[] bytesToWriteTo = new byte[fileLength];
        downloadStream.read(bytesToWriteTo);
        downloadStream.close();

        String photo = new String(bytesToWriteTo);
        SDownloadPhoto sDownloadPhoto = new SDownloadPhoto(photo);

        new RpnTool<SDownloadPhoto>().send(response, sDownloadPhoto);
    }
}
