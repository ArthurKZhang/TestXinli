package com.arthur.mongo.bean;

import org.bson.Document;

import java.util.Date;

/**
 * Created by zhangyu on 26/04/2017.
 */
public class UploadRecord {

    private String userName;
    private String testName;
    private String testId;
    private String cachePath;

    @Override
    public String toString() {
        return "UploadRecord{" +
                "userName='" + userName + '\'' +
                ", testName='" + testName + '\'' +
                ", testId='" + testId + '\'' +
                ", cachePath='" + cachePath + '\'' +
                '}';
    }


    public UploadRecord(String userName, String testName, String testId, String cachePath) {
        this.userName = userName;
        this.testName = testName;
        this.testId = testId;
        this.cachePath = cachePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }
}

