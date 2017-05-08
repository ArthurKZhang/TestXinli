package com.arthur.actions.tea_down_test_result_excel;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class STeaDownTestResult {
    private String doc; //excel document as Json

    @Override
    public String toString() {
        return "STeaDownTestResult{" +
                "doc='" + doc + '\'' +
                '}';
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public STeaDownTestResult(String doc) {

        this.doc = doc;
    }
}
