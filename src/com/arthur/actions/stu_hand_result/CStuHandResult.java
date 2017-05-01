package com.arthur.actions.stu_hand_result;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class CStuHandResult {
    private String stuid;
    private String testId;
    private String result;//Map<Integer, Map<Integer, List<String>>> 的Json

    @Override
    public String toString() {
        return "CStuHandResult{" +
                "stuid='" + stuid + '\'' +
                ", testId='" + testId + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CStuHandResult(String stuid, String testId, String result) {

        this.stuid = stuid;
        this.testId = testId;
        this.result = result;
    }
}
