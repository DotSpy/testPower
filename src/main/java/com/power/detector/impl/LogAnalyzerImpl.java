package com.power.detector.impl;

import com.power.detector.IntrusionDetector;
import com.power.detector.LogAnalyzer;
import com.power.entity.LogEnry;
import com.power.entity.LoginStatus;

import java.util.Arrays;
import java.util.List;

public class LogAnalyzerImpl implements LogAnalyzer {

    public String parseLine(String line) {
        IntrusionDetector intrusionDetector = new IntrusionDetectorImpl();
        List<String> loginList = Arrays.asList(line.split(","));
        LogEnry logEnry = LogEnry.newBuilder()
                .withIp(loginList.get(0))
                .withEpochTime(Long.parseLong(loginList.get(1)))
                .withStatus(LoginStatus.valueOf(loginList.get(2)))
                .withLogin(loginList.get(3)).build();
        if (logEnry.getStatus().equals(LoginStatus.FAILURE)) {
            return intrusionDetector.detectIntrusion(logEnry);
        }
        return null;
    }
}
