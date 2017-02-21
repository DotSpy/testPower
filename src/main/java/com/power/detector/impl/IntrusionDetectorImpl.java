package com.power.detector.impl;

import com.power.detector.IntrusionDetector;
import com.power.detector.IntrusionDetectorManual;
import com.power.entity.LogEnry;
import com.power.entity.RecentLoginEntryContainer;

public class IntrusionDetectorImpl implements IntrusionDetector {

    public final static IntrusionDetectorImpl INSTANCE = new IntrusionDetectorImpl();

    private IntrusionDetectorImpl() {
    }

    private RecentLoginEntryContainer recentLoginEntryContainer = RecentLoginEntryContainer.INSTANCE;

    private IntrusionDetectorManual intrusionDetectorManual = IntrusionDetectorManualImpl.INSTANCE;

    public String detectIntrusion(LogEnry logEnry) {
        intrusionDetectorManual.clean(logEnry.getEpochTime());
        if (recentLoginEntryContainer.addLogin(logEnry)) {
            return logEnry.getLogin(); //intrusion
        } else {
            return null;
        }
    }

}
