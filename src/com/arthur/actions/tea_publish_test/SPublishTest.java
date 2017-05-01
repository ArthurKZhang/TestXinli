package com.arthur.actions.tea_publish_test;

/**
 * Created by zhangyu on 28/04/2017.
 */
public class SPublishTest {
    private String result;

    @Override
    public String toString() {
        return "SPublishTest{" +
                "result='" + result + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SPublishTest(String result) {

        this.result = result;
    }
}
