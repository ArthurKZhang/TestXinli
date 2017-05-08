package com.arthur.actions.tea_down_test_result_excel;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class CTeaDownTestResult {
    private String testId;

    @Override
    public String toString() {
        return "CTeaDownTestResult{" +
                "testId='" + testId + '\'' +
                '}';
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public CTeaDownTestResult(String testId) {

        this.testId = testId;
    }
}
