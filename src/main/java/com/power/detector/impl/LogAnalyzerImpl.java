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
        // магические цифры, лучше ввести поименнованные переменные
        LogEnry logEnry = LogEnry.newBuilder()
                .withIp(loginList.get(0))
                .withEpochTime(Long.parseLong(loginList.get(1)))
                .withStatus(LoginStatus.valueOf(loginList.get(2)))
                .withLogin(loginList.get(3)).build();
        // по такой логике лучше объект создавать, если мы вошли в if
        // ведь в противном случае он не нужен
        if (logEnry.getStatus().equals(LoginStatus.FAILURE)) {
            return intrusionDetector.detectIntrusion(logEnry);
        }
        return null;
    }
}
