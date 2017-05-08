package com.arthur.actions.tea_down_test_result_excel;

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
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.result.UpdateResult;
import javafx.collections.transformation.SortedList;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.commons.io.IOUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class ActionTeaDownTestResult extends BaseAction {
    public void teaDownResult() {
        CTeaDownTestResult c = new RqsTool<CTeaDownTestResult>() {
        }.rqsTool(request);

        String testId = c.getTestId();

        MongoClient mongoClient = MongoManager.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase(MongoManager.DB_NAME1);
        MongoCollection testResultColl = database.getCollection(MongoManager.COLLECTION_TESTRESULT);
        MongoCollection hisColl = database.getCollection(MongoManager.COLLECTION_HISTORY);
        MongoCollection userColl = database.getCollection(MongoManager.COLLECTION_USER);

        MongoCursor<Document> testResultCur = testResultColl.find(new Document("tesId", testId)).iterator();
        if (testResultCur.hasNext()) {
            Document testResDocment = testResultCur.next();
            String fileId = (String) testResDocment.get("fileId");
            if (fileId == null || fileId.equals("")) {
                // 根据testId找到所有的historyId List<String>
                List<String> historyIds = (List<String>) testResDocment.get("historyIds");
                File Excel = new File(testId + ".xlsx");
                try {
                    //创建工作薄
                    WritableWorkbook workbook = Workbook.createWorkbook(Excel);
                    //创建新的一页
                    WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                    int flag = 0;
                    for (String historyid : historyIds) {
                        // 对每一个historyId，获得答题结果和studentId，根据studentId的到studentName
                        MongoCursor<Document> hisCur = hisColl.find(new Document("_id", new ObjectId(historyid))).iterator();
                        String stuId = (String) hisCur.next().get("userId");
                        String resultAsJson = (String) hisCur.next().get("result");
                        Gson gson = new Gson();
                        Type type = new TypeToken<Map<Integer, Map<Integer, List<String>>>>() {
                        }.getType();
                        Map<Integer, Map<Integer, List<String>>> result = gson.fromJson(resultAsJson, type);

                        Document stuDoc = (Document) userColl.find(new Document("_id", new ObjectId(stuId))).iterator().next();
                        String stuName = (String) stuDoc.get("name");

                        int n = result.size();
                        if (flag == 0) {    //写标题和第一行
                            //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
                            //写标题
                            Label xingMing = new Label(0, flag, "姓名");
                            sheet.addCell(xingMing);
                            for (int i = 1; i <= n; i++) {
                                Label tiHao = new Label(i, flag, "第" + i + "题");
                                sheet.addCell(tiHao);
                            }
                            flag++;
                            Label xingMing2 = new Label(0, flag, stuName);
                            sheet.addCell(xingMing2);
                            for (int i = 1; i <= n; i++) {
                                Map<Integer, List<String>> r = result.get(i);
                                Label tiHao = new Label(i, flag, getQuzResult(r));
                                sheet.addCell(tiHao);
                            }
                            flag++;
                        } else {
                            //写内容
                            Label xingMing3 = new Label(0, flag, stuName);
                            sheet.addCell(xingMing3);
                            for (int i = 1; i <= n; i++) {
                                Map<Integer, List<String>> r = result.get(i);
                                Label tiHao = new Label(i, flag, getQuzResult(r));
                                sheet.addCell(tiHao);
                            }
                            flag++;
                        }//end 写一行完成
                    }//for循环完成，文件内容写好了
                    workbook.write();
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }finally {

                }
                //TODO 把文件存到GridFS，将GridFS的id存到testResult中
                GridFSBucket gridFSBucket = GridFSBuckets.create(database, MongoManager.GRIDFS_EXCEL_RESULT);
                ObjectId excelId;
                InputStream streamToUploadFrom = null;
                try {
                    streamToUploadFrom = new FileInputStream(Excel);
                    excelId = gridFSBucket.uploadFromStream(testId + ".xlsx", streamToUploadFrom);

                    //把excelID存到testResult中
                    testResDocment.append("fileId",excelId.toHexString());
                    testResultColl.replaceOne(eq("tesId", testId),testResDocment);

                    streamToUploadFrom.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //发送文件
                try {
                    InputStream is = new FileInputStream(Excel);
                    String excelAsString = IOUtils.toString(is,"UTF-8");
                    STeaDownTestResult s = new STeaDownTestResult(excelAsString);
                    new RpnTool<STeaDownTestResult>().send(response, s);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else { //文件已经生成过了
                //TODO 找到文件，发送给客户端
                GridFSBucket gridFSBucket = GridFSBuckets.create(database, MongoManager.GRIDFS_EXCEL_RESULT);
                GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(fileId));
                int fileLength = (int) downloadStream.getGridFSFile().getLength();

                byte[] bytesToWriteTo = new byte[fileLength];
                downloadStream.read(bytesToWriteTo);
                downloadStream.close();

                String excelAsString = new String(bytesToWriteTo, Charset.forName("UTF-8"));
                STeaDownTestResult s = new STeaDownTestResult(excelAsString);
                new RpnTool<STeaDownTestResult>().send(response, s);
            }
        } else {//testresult里面没有找到这个testid对应的记录
            STeaDownTestResult s = new STeaDownTestResult("");
            new RpnTool<STeaDownTestResult>().send(response, s);
        }

    }

    private String getQuzResult(Map<Integer, List<String>> r) {
        Set<Integer> set = r.keySet();
        if (set.contains(1)) {
            String tmp = r.get(1).toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        if (set.contains(2)) {
            String tmp = r.get(2).toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        if (set.contains(3)) {
            String tmp = r.get(1).toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        if (set.contains(4)) {
            String tmp = r.get(1).toString();
            return tmp.substring(1, tmp.length() - 1);
        }
        return null;
    }
}
