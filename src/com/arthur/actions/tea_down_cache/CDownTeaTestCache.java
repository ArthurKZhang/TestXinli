package com.arthur.actions.tea_down_cache;

/**
 * Created by zhangyu on 29/04/2017.
 */
public class CDownTeaTestCache {
    private String testName;
    private String testId;

    @Override
    public String toString() {
        return "CDownTeaTestCache{" +
                "testName='" + testName + '\'' +
                ", testId='" + testId + '\'' +
                '}';
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

    public CDownTeaTestCache(String testName, String testId) {

        this.testName = testName;
        this.testId = testId;
    }
}
