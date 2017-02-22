package com.oxagile.pc.detector.impl;

import com.oxagile.pc.entity.IntrusionTimer;
import com.oxagile.pc.entity.RingBuffer;
import com.oxagile.pc.detector.IntrusionDetectorManual;
import com.oxagile.pc.entity.RecentLoginEntryContainer;

public class IntrusionDetectorManualImpl implements IntrusionDetectorManual {

    private static final Long INVALIDATE_TIME = 300000L;

    private RecentLoginEntryContainer recentLoginEntryContainer = RecentLoginEntryContainer.getInstance();

    private IntrusionTimer intrusionTimer = IntrusionTimer.getInstance();

    public void clean(Long currentTime) {
        intrusionTimer.setEpochTime(currentTime);
        for (RingBuffer ringBuffer : recentLoginEntryContainer.getRecentLoginMap().values()) {
            if (ringBuffer.takeTail() != null && timeExpired(ringBuffer.takeTail())) {
                ringBuffer.deleteTail();
            }
        }
    }

    private Boolean timeExpired(Long time){
        return intrusionTimer.getEpochTime() - time > INVALIDATE_TIME;
    }
}
