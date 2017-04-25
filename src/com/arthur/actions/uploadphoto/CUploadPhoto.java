package com.arthur.actions.uploadphoto;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class CUploadPhoto {
    private String userName;
    private String photo;

    @Override
    public String toString() {
        return "CUploadPhoto{" +
                "userName='" + userName + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CUploadPhoto(String userName, String photo) {

        this.userName = userName;
        this.photo = photo;
    }
}
