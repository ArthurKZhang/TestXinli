package com.arthur.actions.tea_posttest_simple;

/**
 * Created by zhangyu on 26/03/2017.
 */
public class STeacherPostTest {
    private String testID;

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public STeacherPostTest(String testID) {
        this.testID = testID;
    }

    @Override
    public String toString() {
        return "STeacherPostTest{" +
                "testID='" + testID + '\'' +
                '}';
    }
}
