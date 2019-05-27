package com.example.android.findem.Models;

import java.io.Serializable;

public class Community implements Serializable {
    private long cid;

    private String name;
    private String imagepath;
    private String description;

    public Community(String name, String imagepath, String description) {
        this.name = name;
        this.imagepath = imagepath;
        this.description = description;
    }

    public long getCid() {
        return cid;
    }

    public String getDescription() {
        return description;
    }

    public String getImagepath() {
        return imagepath;
    }

    public String getName() {
        return name;
    }
}
