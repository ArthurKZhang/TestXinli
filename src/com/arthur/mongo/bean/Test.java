package com.arthur.mongo.bean;

import java.util.List;

/**
 * Created by zhangyu on 21/03/2017.
 */
public class Test {
    private String testName;

    private List<Quz> quzs;

    @Override
    public String toString() {
        return "Test{" +
                "testName='" + testName + '\'' +
                ", quzs=" + quzs +
                '}';
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Quz> getQuzs() {
        return quzs;
    }

    public void setQuzs(List<Quz> quzs) {
        this.quzs = quzs;
    }

    public Test(String testName, List<Quz> quzs) {

        this.testName = testName;
        this.quzs = quzs;
    }

    public Test() {

    }
}
