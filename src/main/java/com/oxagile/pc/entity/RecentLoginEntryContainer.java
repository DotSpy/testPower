package com.oxagile.pc.entity;

import java.util.HashMap;
import java.util.Map;

public class RecentLoginEntryContainer {

    private final static Integer BUFFER_CAPACITY = 5;

    private static RecentLoginEntryContainer instance = null;

    private RecentLoginEntryContainer() {
    }

    public static RecentLoginEntryContainer getInstance() {
        if (instance == null) {
            instance = new RecentLoginEntryContainer();
        }
        return instance;
    }

    private Map<String, RingBuffer> recentLoginMap = new HashMap<>();

    public Boolean addLogin(LogEnry logEnry) {
        if (recentLoginMap.containsKey(logEnry.getIp())) {
            recentLoginMap.get(logEnry.getIp()).put(logEnry.getEpochTime());
            if (recentLoginMap.get(logEnry.getIp()).available() == BUFFER_CAPACITY) {
                return true;
            }
        } else {
            RingBuffer ringBuffer = new RingBuffer(BUFFER_CAPACITY);
            ringBuffer.put(logEnry.getEpochTime());
            recentLoginMap.put(logEnry.getIp(), ringBuffer);
        }
        return false;
    }

    public Map<String, RingBuffer> getRecentLoginMap() {
        return recentLoginMap;
    }
}
