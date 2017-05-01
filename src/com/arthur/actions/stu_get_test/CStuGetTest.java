package com.arthur.actions.stu_get_test;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class CStuGetTest {
    private String testId;

    @Override
    public String toString() {
        return "CStuGetTest{" +
                "testId='" + testId + '\'' +
                '}';
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public CStuGetTest(String testId) {

        this.testId = testId;
    }
}
