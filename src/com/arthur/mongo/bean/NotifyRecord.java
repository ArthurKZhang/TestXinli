package com.arthur.mongo.bean;

import java.util.Date;
import java.util.Objects;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class NotifyRecord {
    private String testId;
    private Date endDate;
    private String teaName;
    private String testName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotifyRecord record = (NotifyRecord) o;
        return Objects.equals(testId, record.testId) &&
                Objects.equals(endDate, record.endDate) &&
                Objects.equals(teaName, record.teaName) &&
                Objects.equals(testName, record.testName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, endDate, teaName, testName);
    }

    @Override
    public String toString() {
        return "SStuGetNotify{" +
                "testId='" + testId + '\'' +
                ", endDate=" + endDate +
                ", teaName='" + teaName + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public NotifyRecord(String testId, Date endDate, String teaName, String testName) {

        this.testId = testId;
        this.endDate = endDate;
        this.teaName = teaName;
        this.testName = testName;
    }
}
