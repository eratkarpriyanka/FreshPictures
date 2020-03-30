package com.spinproducts.freshpics.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * List returned by api
 */
public class SpinApiResponse {

    @SerializedName("data")
    ArrayList<Picture> pictureArrayList;

    public ArrayList<Picture> getPictureArrayList() {
        return pictureArrayList;
    }

    public void setPictureArrayList(ArrayList<Picture> pictureArrayList) {
        this.pictureArrayList = pictureArrayList;
    }
}
