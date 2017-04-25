package com.arthur.actions.getphoto;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class SDownloadPhoto {
    String photo;

    @Override
    public String toString() {
        return "SDownloadPhoto{" +
                "photo='" + photo + '\'' +
                '}';
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public SDownloadPhoto(String photo) {
        this.photo = photo;
    }
}

