package com.arthur.actions.stu_hand_result;

/**
 * Created by zhangyu on 02/05/2017.
 */
public class SStuHandResult {
    private String reply;

    @Override
    public String toString() {
        return "SStuHandResult{" +
                "reply='" + reply + '\'' +
                '}';
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public SStuHandResult(String reply) {

        this.reply = reply;
    }
}
