package com.oxagile.pc.detector.impl;

import com.oxagile.pc.detector.IntrusionDetector;
import com.oxagile.pc.detector.IntrusionDetectorManual;
import com.oxagile.pc.entity.LogEnry;
import com.oxagile.pc.entity.RecentLoginEntryContainer;

public class IntrusionDetectorImpl implements IntrusionDetector {

    private RecentLoginEntryContainer recentLoginEntryContainer = RecentLoginEntryContainer.getInstance();

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
