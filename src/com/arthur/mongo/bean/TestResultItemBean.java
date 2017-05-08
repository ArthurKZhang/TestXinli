package com.arthur.mongo.bean;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class TestResultItemBean {
    private String testId;
    private String testName;
    private String answerNum;

    @Override
    public String toString() {
        return "TestResultItemBean{" +
                "testId='" + testId + '\'' +
                ", testName='" + testName + '\'' +
                ", answerNum='" + answerNum + '\'' +
                '}';
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(String answerNum) {
        this.answerNum = answerNum;
    }

    public TestResultItemBean(String testId, String testName, String answerNum) {

        this.testId = testId;
        this.testName = testName;
        this.answerNum = answerNum;
    }
}
