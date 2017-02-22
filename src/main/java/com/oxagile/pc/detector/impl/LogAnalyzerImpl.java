package com.oxagile.pc.detector.impl;

import com.oxagile.pc.detector.IntrusionDetector;
import com.oxagile.pc.detector.LogAnalyzer;
import com.oxagile.pc.entity.LogEnry;
import com.oxagile.pc.entity.LoginStatus;

import java.util.Arrays;
import java.util.List;

public class LogAnalyzerImpl implements LogAnalyzer {

    public String parseLine(String line) {
        IntrusionDetector intrusionDetector = new IntrusionDetectorImpl();
        List<String> loginList = Arrays.asList(line.split(","));
        LoginStatus loginStatus = LoginStatus.valueOf(loginList.get(2));
        if (loginStatus.equals(LoginStatus.FAILURE)) {
            String ip = loginList.get(0);
            Long time = Long.parseLong(loginList.get(1));
            String login = loginList.get(3);
            LogEnry logEnry = LogEnry.newBuilder()
                    .withIp(ip)
                    .withEpochTime(time)
                    .withStatus(loginStatus)
                    .withLogin(login).build();
            return intrusionDetector.detectIntrusion(logEnry);
        }
        return null;
    }
}
