package com.cxel.tvplayer.tvmenu;

import java.io.Serializable;

public class TvMenuBean implements Serializable {
private String name;
private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "TvMenuBean{" +
                "name='" + name + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
