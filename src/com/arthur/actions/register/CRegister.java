package com.arthur.actions.register;

import java.util.Date;

/**
 * Created by zhangyu on 21/02/2017.
 */
public class CRegister {
    private String name;
    private String password;
    private String institution;
    private Date enrollmentDate;
    private String type;

    public CRegister(String name, String password, String institution, Date enrollmentDate, String type) {
        this.name = name;
        this.password = password;
        this.institution = institution;
        this.enrollmentDate = enrollmentDate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CRegister{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", institution='" + institution + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", type='" + type + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
