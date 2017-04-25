package com.arthur.mongo.bean;

import java.util.List;

/**
 * Created by zhangyu on 20/03/2017.
 */
public class Quz {

    public static final int CHOOSE_SIMPLE = 1;
    public static final int CHOOSE_MULTIP = 2;
    public static final int TRUE_FALSE = 3;
    public static final int QUESTIONNAIRE = 4;



    //题目类型
    public int type;
    //题目内容
    public  String quzContent;
    //选项数量
    private int chooseNum;
    //存储选项的list
    private List<String> chooseItems;

    public Quz(){}

    public Quz(int type, String quzContent, int chooseNum, List<String> chooseItems) {
        this.type = type;
        this.quzContent = quzContent;
        this.chooseNum = chooseNum;
        this.chooseItems = chooseItems;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuzContent() {
        return quzContent;
    }

    public void setQuzContent(String quzContent) {
        this.quzContent = quzContent;
    }

    public int getChooseNum() {
        return chooseNum;
    }

    public void setChooseNum(int chooseNum) {
        this.chooseNum = chooseNum;
    }

    public Object getChooseItems() {
        return chooseItems;
    }

    public void setChooseItems(List<String> chooseItems) {
        this.chooseItems = chooseItems;
    }

    @Override
    public String toString() {
        return "com.arthur.mongo.bean.Quz{" +
                "type=" + type +
                ", quzContent='" + quzContent + '\'' +
                ", chooseNum=" + chooseNum +
                ", chooseItems=" + chooseItems +
                '}';
    }
}
