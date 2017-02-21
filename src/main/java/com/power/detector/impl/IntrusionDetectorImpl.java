package com.power.detector.impl;

import com.power.detector.IntrusionDetector;
import com.power.detector.IntrusionDetectorManual;
import com.power.entity.LogEnry;
import com.power.entity.RecentLoginEntryContainer;

public class IntrusionDetectorImpl implements IntrusionDetector {

    private RecentLoginEntryContainer recentLoginEntryContainer = RecentLoginEntryContainer.INSTANCE;

    public String detectIntrusion(LogEnry logEnry) {
        IntrusionDetectorManual intrusionDetectorManual = new IntrusionDetectorManualImpl();
        intrusionDetectorManual.clean(logEnry.getEpochTime());
        if (recentLoginEntryContainer.addLogin(logEnry)) {
            return logEnry.getLogin(); //intrusion
        } else {
            return null;
        }
    }

}
