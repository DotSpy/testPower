package com.power.entity;

public class IntrusionTimer {

    public final static IntrusionTimer INSTANCE = new IntrusionTimer();

    private IntrusionTimer() {
    }
    private Long epochTime;

    public Long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Long epochTime) {
        this.epochTime = epochTime;
    }
}
