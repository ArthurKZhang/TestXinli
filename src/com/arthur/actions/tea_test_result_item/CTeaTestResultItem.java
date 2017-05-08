package com.arthur.actions.tea_test_result_item;

/**
 * Created by zhangyu on 04/05/2017.
 */
public class CTeaTestResultItem {
    private String teaId;

    @Override
    public String toString() {
        return "CTeaTestResultItem{" +
                "teaId='" + teaId + '\'' +
                '}';
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public CTeaTestResultItem(String teaId) {

        this.teaId = teaId;
    }
}
