package com.power.detector;

import com.power.detector.impl.LogAnalyzerImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LogAnalyzerTest {

    @Test
    public void parseLineTest() throws Exception {
        LogAnalyzer logAnalyzer = LogAnalyzerImpl.INSTANCE;
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.127.12,1462458822,SUCCESS,Peter.Rascal"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462459026,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462459026,SUCCESS,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462459026,FAILURE,Peter.Rascal"));
    }

    @Test
    public void parseLineTestIntrusion() throws Exception {
        LogAnalyzer logAnalyzer = LogAnalyzerImpl.INSTANCE;
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertEquals("Thomas.Davenport",logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
    }

    @Test
    public void parseLineTestTimer() throws Exception {
        LogAnalyzer logAnalyzer = LogAnalyzerImpl.INSTANCE;
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1475457652,FAILURE,Thomas.Davenport"));
    }

    @Test
    public void parseLineCompromiseFewIP() throws Exception {
        LogAnalyzer logAnalyzer = LogAnalyzerImpl.INSTANCE;
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.127.12,1462457652,FAILURE,Thomas.Davenport"));
    }
}