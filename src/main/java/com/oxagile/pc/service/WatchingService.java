package com.oxagile.pc.service;

import java.nio.file.Path;

/**
 * interface for watching changes in log file
 */
public interface WatchingService {

    /**
     * method for watching changes in log file
     * @param path for directory with log file
     */
    void watchDirectory(Path path);
}
