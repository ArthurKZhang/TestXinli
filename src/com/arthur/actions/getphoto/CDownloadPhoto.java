package com.arthur.actions.getphoto;

/**
 * Created by zhangyu on 25/04/2017.
 */
public class CDownloadPhoto {
    String photoID;

    @Override
    public String toString() {
        return "CDownloadPhoto{" +
                "photoID='" + photoID + '\'' +
                '}';
    }

    public CDownloadPhoto(String photoID) {
        this.photoID = photoID;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }
}
