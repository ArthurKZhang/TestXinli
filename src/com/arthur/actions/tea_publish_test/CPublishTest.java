package com.arthur.actions.tea_publish_test;

import java.util.Date;

/**
 * Created by zhangyu on 27/04/2017.
 */
public class CPublishTest {
    private String testid;
    private String testName;
    private String teacherName;
    private String userInstitution;
    private String enrollYear;
    private Date sDate;
    private Date eDate;

    @Override
    public String toString() {
        return "CPublishTest{" +
                "testid='" + testid + '\'' +
                ", testName='" + testName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", userInstitution='" + userInstitution + '\'' +
                ", enrollYear='" + enrollYear + '\'' +
                ", sDate=" + sDate +
                ", eDate=" + eDate +
                '}';
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserInstitution() {
        return userInstitution;
    }

    public void setUserInstitution(String userInstitution) {
        this.userInstitution = userInstitution;
    }

    public String getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(String enrollYear) {
        this.enrollYear = enrollYear;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public CPublishTest(String testid, String testName, String teacherName, String userInstitution, String enrollYear, Date sDate, Date eDate) {

        this.testid = testid;
        this.testName = testName;
        this.teacherName = teacherName;
        this.userInstitution = userInstitution;
        this.enrollYear = enrollYear;
        this.sDate = sDate;
        this.eDate = eDate;
    }
}
