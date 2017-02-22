package com.oxagile.pc.entity;

public class IntrusionTimer {

    private static IntrusionTimer instance = null;

    private IntrusionTimer() {
    }

    public static IntrusionTimer getInstance() {
        if (instance == null) {
            instance = new IntrusionTimer();
        }
        return instance;
    }

    private Long epochTime;

    public Long getEpochTime() {
        return epochTime;
    }

    public void setEpochTime(Long epochTime) {
        this.epochTime = epochTime;
    }
}
