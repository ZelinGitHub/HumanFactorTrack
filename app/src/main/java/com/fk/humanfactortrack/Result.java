package com.fk.humanfactortrack;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Result implements Serializable {


    //测试名称
    private String tester;
    //色块索引
    private int index = 0;
    private int place = 0;
    private long beginTime = 0;
    private long tipTime = 0;
    private long tipBlankTime = 0;
    private long actionTime = 0;
    private long actionBlankTime = 0;
    private String track;

    private int misscount;

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getTipTime() {
        return tipTime;
    }

    public void setTipTime(long tipTime) {
        this.tipTime = tipTime;
    }

    public long getTipBlankTime() {
        return tipBlankTime;
    }

    public void setTipBlankTime(long tipBlankTime) {
        this.tipBlankTime = tipBlankTime;
    }

    public long getActionTime() {
        return actionTime;
    }

    public void setActionTime(long actionTime) {
        this.actionTime = actionTime;
    }

    public long getActionBlankTime() {
        return actionBlankTime;
    }

    public void setActionBlankTime(long actionBlankTime) {
        this.actionBlankTime = actionBlankTime;
    }

    public int getMisscount() {
        return misscount;
    }

    public void setMisscount(int misscount) {
        this.misscount = misscount;
    }

    @NonNull
    @Override
    public String toString() {
        return "Result{" +
                "tester='" + tester + '\'' +
                ", index=" + index +
                ", place=" + place +
                ", beginTime=" + beginTime +
                ", tipTime=" + tipTime +
                ", tipBlankTime=" + tipBlankTime +
                ", actionTime=" + actionTime +
                ", actionBlankTime=" + actionBlankTime +
                ", track='" + track + '\'' +
                ", misscount=" + misscount +
                '}';
    }
}
