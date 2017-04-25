package com.arthur.actions.teacher_post_test;

import com.arthur.mongo.bean.Test;

/**
 * Created by zhangyu on 26/03/2017.
 */
public class CTeacherPostTest {
    String id;
    Test test;

    public CTeacherPostTest(String id, Test test) {
        this.id = id;
        this.test = test;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "CTeacherPostTest{" +
                "id='" + id + '\'' +
                ", test=" + test +
                '}';
    }
}
