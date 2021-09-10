package com.example.relays2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelayState {
    @SerializedName("morning")
    @Expose
    public Morning morning;
    @SerializedName("day")
    @Expose
    public Day day;
    @SerializedName("evening")
    @Expose
    public Evening evening;



    @SerializedName("night")
    @Expose
    public Night night;
    @SerializedName("currentTemp")
    @Expose
    public Double currentTemp;

    @SerializedName("relay")
    @Expose
    public Integer relay;

   /* @SerializedName("curTimeOfDay")
    @Expose
    public Integer curTimeOfDay;*/

    @SerializedName("curTime")
    @Expose
    public CurTime curTime;


    public RelayState(Morning morning, Day day, Evening evening, Night night) {
        this.morning = morning;
        this.day = day;
        this.evening = evening;
        this.night = night;

    }
}
