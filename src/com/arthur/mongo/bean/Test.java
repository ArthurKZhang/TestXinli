package com.arthur.mongo.bean;

import java.util.List;

/**
 * Created by zhangyu on 21/03/2017.
 */
public class Test {
    private List<Quz> test;

    public Test(){}

    public Test(List<Quz> test) {
        this.test = test;
    }

    public List<Quz> getTest() {
        return test;
    }

    public void setTest(List<Quz> test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "com.arthur.mongo.bean.Test{" +
                "test=" + test +
                '}';
    }
}
