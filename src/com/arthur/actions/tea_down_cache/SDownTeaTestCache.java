package com.arthur.actions.tea_down_cache;

/**
 * Created by zhangyu on 29/04/2017.
 */
public class SDownTeaTestCache {
    private String testId;
    private String cache;

    @Override
    public String toString() {
        return "SDownTeaTestCache{" +
                "testId='" + testId + '\'' +
                ", cache='" + cache + '\'' +
                '}';
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public SDownTeaTestCache(String testId, String cache) {

        this.testId = testId;
        this.cache = cache;
    }
}
