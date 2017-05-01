package com.arthur.mongo.codec;

import com.arthur.mongo.bean.UploadRecord;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * Created by zhangyu on 26/04/2017.
 */
public class UploadRecordc implements Codec<UploadRecord> {
    @Override
    public UploadRecord decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return null;
    }

    @Override
    public void encode(BsonWriter bsonWriter, UploadRecord uploadRecord, EncoderContext encoderContext) {

    }

    @Override
    public Class<UploadRecord> getEncoderClass() {
        return null;
    }

//    @Override
//    public UploadRecord decode(BsonReader bsonReader, DecoderContext decoderContext) {
//        String json = bsonReader.readString();
//        return new UploadRecord(Document.parse(json));
//    }
//
//    @Override
//    public void encode(BsonWriter bsonWriter, UploadRecord uploadRecord, EncoderContext encoderContext) {
//        /*
//    private String userName;
//    private String testName;
//    private String testId;
//    private Date latest;
//    private String content;
//         */
//        Document d = new Document("userName",uploadRecord.getUserName())
//                .append("testName", uploadRecord.getTestName())
//                .append("testId", uploadRecord.getTestId())
//                .append("latest", uploadRecord.getLatest())
//                .append("content", uploadRecord.getContent());
//        bsonWriter.writeString(d.toJson());
//    }
//
//    @Override
//    public Class<UploadRecord> getEncoderClass() {
//        return UploadRecord.class;
//    }
}
