package com.oxagile.pc;

import com.oxagile.pc.service.WatchingService;
import com.oxagile.pc.service.impl.WatchingServiceImpl;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (new File(args[0]).exists()) {
            WatchingService watchingService = WatchingServiceImpl.getInstance();
            File dir = new File(args[0]);
            watchingService.watchDirectory(dir.toPath());
        }
    }
}
