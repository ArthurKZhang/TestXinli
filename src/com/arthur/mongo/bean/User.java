package com.arthur.mongo.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyu on 18/04/2017.
 */
public class User {
    private String identity;
    private String name;
    private String password;
    private String institution;
    private Date enrollmentDate;

    //照片单独存储
    //做题的历史列表，存储题目的ID
    private List<String> testIds;

    public User(){}

    public User(String identity, String name, String password, String institution, Date enrollmentDate, List<String> testIds) {
        this.identity = identity;
        this.name = name;
        this.password = password;
        this.institution = institution;
        this.enrollmentDate = enrollmentDate;
        this.testIds = testIds;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public List<String> getTestIds() {
        return testIds;
    }

    public void setTestIds(List<String> testIds) {
        this.testIds = testIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return identity.equals(user.identity);

    }

    @Override
    public int hashCode() {
        return identity.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", institution='" + institution + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", testIds=" + testIds +
                '}';
    }
}
