package com.arthur.actions.stu_get_test;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class SStuGetTest {
    private String testAsJson;

    @Override
    public String toString() {
        return "SStuGetTest{" +
                "testAsJson='" + testAsJson + '\'' +
                '}';
    }

    public String getTestAsJson() {
        return testAsJson;
    }

    public void setTestAsJson(String testAsJson) {
        this.testAsJson = testAsJson;
    }

    public SStuGetTest(String testAsJson) {

        this.testAsJson = testAsJson;
    }
}
