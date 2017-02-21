package com.power.detector.impl;

import com.power.detector.IntrusionDetectorManual;
import com.power.entity.IntrusionTimer;
import com.power.entity.RecentLoginEntryContainer;
import com.power.entity.RingBuffer;

public class IntrusionDetectorManualImpl implements IntrusionDetectorManual {

    private static final Long INVALIDATE_TIME = 300000L;

    private RecentLoginEntryContainer recentLoginEntryContainer = RecentLoginEntryContainer.INSTANCE;

    private IntrusionTimer intrusionTimer = IntrusionTimer.INSTANCE;

    public void clean(Long currentTime) {
        intrusionTimer.setEpochTime(currentTime);
        for (RingBuffer ringBuffer : recentLoginEntryContainer.getRecentLoginMap().values()) {
            if (ringBuffer.takeTail() != null && intrusionTimer.getEpochTime() - ringBuffer.takeTail() > INVALIDATE_TIME) {
                ringBuffer.deleteTail();
            }
        }
    }
}
