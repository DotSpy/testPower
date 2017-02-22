package com.oxagile.pc.detector;

/**
 * interface to clean old data from buffer
 */
public interface IntrusionDetectorManual {

    /**
     * Delete time from buffer if more then INVALIDATE_TIME pass
     * @param currentTime time from Intrusion timer
     */
    void clean(Long currentTime);
}
