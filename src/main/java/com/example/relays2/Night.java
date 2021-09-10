package com.example.relays2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Night {

    @SerializedName("m")
    @Expose
    public Integer m;
    @SerializedName("h")
    @Expose
    public Integer h;
    @SerializedName("temp")
    @Expose
    public Double temp;

}
