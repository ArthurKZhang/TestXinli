package com.arthur.actions.uploadphoto;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class SUploadPhoto {
    private String photoid;

    @Override
    public String toString() {
        return "SUploadPhoto{" +
                "photoid='" + photoid + '\'' +
                '}';
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public SUploadPhoto(String photoid) {

        this.photoid = photoid;
    }
}
