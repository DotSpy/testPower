package com.power.detector;

import com.power.entity.LogEnry;

public interface IntrusionDetector {

    String detectIntrusion(LogEnry logEnry);
}
