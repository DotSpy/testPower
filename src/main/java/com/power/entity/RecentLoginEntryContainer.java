package com.power.entity;

import java.util.HashMap;
import java.util.Map;

public class RecentLoginEntryContainer {

    public final static RecentLoginEntryContainer INSTANCE = new RecentLoginEntryContainer();

    private RecentLoginEntryContainer() {
    }

    private Map<String, RingBuffer> recentLoginMap = new HashMap<String, RingBuffer>();

    public Boolean addLogin(LogEnry logEnry) {
        if (recentLoginMap.containsKey(logEnry.getIp())) {
            recentLoginMap.get(logEnry.getIp()).put(logEnry.getEpochTime());
            if (recentLoginMap.get(logEnry.getIp()).available() == 5) {
                return true;
            }
        } else {
            RingBuffer ringBuffer = new RingBuffer(5);
            ringBuffer.put(logEnry.getEpochTime());
            recentLoginMap.put(logEnry.getIp(), ringBuffer);
            if (recentLoginMap.get(logEnry.getIp()).available() == 5) {
                return true;
            }
        }
        return false;
    }

    public Map<String, RingBuffer> getRecentLoginMap() {
        return recentLoginMap;
    }
}
