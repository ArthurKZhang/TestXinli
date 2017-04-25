package com.arthur.actions.register;

/**
 * Created by zhangyu on 21/02/2017.
 */
public class SRegister{
    private String result;
    private String _id;

    public SRegister(String result, String _id) {
        this.result = result;
        this._id = _id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "SRegister{" +
                "result='" + result + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
