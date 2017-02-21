package com.power.detector;

import com.power.detector.impl.LogAnalyzerImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LogAnalyzerTest {

    @Test
    public void parseLineTest() throws Exception {
        LogAnalyzer logAnalyzer = new LogAnalyzerImpl();
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462457652,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.125,1462458822,SUCCESS,Peter.Rascal"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462459026,FAILURE,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.125,1462459026,SUCCESS,Thomas.Davenport"));
        assertNull(logAnalyzer.parseLine("30.212.19.124,1462459026,FAILURE,Peter.Rascal"));
    }

    @Test
    public void parseLineTestIntrusion() throws Exception {
        LogAnalyzer logAnalyzer = new LogAnalyzerImpl();
        assertNull(logAnalyzer.parseLine("127.0.0.1,1462457652,FAILURE,Peter.Davenport"));
        assertNull(logAnalyzer.parseLine("127.0.0.1,1462457652,FAILURE,Peter.Davenport"));
        assertNull(logAnalyzer.parseLine("127.0.0.1,1462457652,FAILURE,Peter.Davenport"));
        assertNull(logAnalyzer.parseLine("127.0.0.1,1462457652,FAILURE,Peter.Davenport"));
        assertEquals("Peter.Davenport",logAnalyzer.parseLine("127.0.0.1,1462457652,FAILURE,Peter.Davenport"));
    }

    @Test
    public void parseLineTestTimer() throws Exception {
        LogAnalyzer logAnalyzer = new LogAnalyzerImpl();
        assertNull(logAnalyzer.parseLine("127.0.0.2,1462457652,FAILURE,Thomas.Peter"));
        assertNull(logAnalyzer.parseLine("127.0.0.2,1462457652,FAILURE,Thomas.Peter"));
        assertNull(logAnalyzer.parseLine("127.0.0.2,1462457652,FAILURE,Thomas.Peter"));
        assertNull(logAnalyzer.parseLine("127.0.0.2,1462457652,FAILURE,Thomas.Peter"));
        assertNull(logAnalyzer.parseLine("127.0.0.2,1475457652,FAILURE,Thomas.Peter"));
    }

    @Test
    public void parseLineCompromiseFewIP() throws Exception {
        LogAnalyzer logAnalyzer = new LogAnalyzerImpl();
        assertNull(logAnalyzer.parseLine("127.0.0.3,1462457652,FAILURE,Peter.Thomas"));
        assertNull(logAnalyzer.parseLine("127.0.0.3,1462457652,FAILURE,Peter.Thomas"));
        assertNull(logAnalyzer.parseLine("127.0.0.3,1462457652,FAILURE,Peter.Thomas"));
        assertNull(logAnalyzer.parseLine("127.0.0.3,1462457652,FAILURE,Peter.Thomas"));
        assertNull(logAnalyzer.parseLine("127.0.0.4,1462457652,FAILURE,Peter.Thomas"));
    }
}