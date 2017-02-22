package com.oxagile.pc.detector;

import com.oxagile.pc.entity.LogEnry;

/**
 * interface for detecting intrusions
 */
public interface IntrusionDetector {

    /**
     *
     * @param logEnry with FAILED login status
     * @return if no intrusion return null else return login
     */
    String detectIntrusion(LogEnry logEnry);
}
