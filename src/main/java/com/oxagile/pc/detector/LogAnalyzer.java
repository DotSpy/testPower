package com.oxagile.pc.detector;

/**
 * interface for analyzing login line
 */
public interface LogAnalyzer {

    /**
     *
     * @param line last file line
     * @return null if no intrusion else return login
     */
    String parseLine(String line);
}
