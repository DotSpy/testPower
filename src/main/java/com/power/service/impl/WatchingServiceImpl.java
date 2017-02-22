package com.power.service.impl;

import com.power.detector.LogAnalyzer;
import com.power.detector.impl.LogAnalyzerImpl;
import com.power.service.WatchingService;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.*;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatchingServiceImpl implements WatchingService {

    /* ты если делаешь уже синглетон или инстанс холдер,
     * то делай до конца =)
     *
     */
    public final static WatchingServiceImpl INSTANCE = new WatchingServiceImpl();

    private WatchingServiceImpl() {
    }

    @Override
    public void watchDirectory(Path path) {
        LogAnalyzer logAnalyzer = new LogAnalyzerImpl();
        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("Path: " + path
                        + " is not a folder");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        FileSystem fs = path.getFileSystem();
        try (WatchService service = fs.newWatchService()) {
            path.register(service, ENTRY_MODIFY);
            WatchKey key;
            while (true) {
                key = service.take();
                WatchEvent.Kind<?> kind;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (ENTRY_MODIFY == kind) {
                        // тут ворнинг идея высвечивает, ничего нельзя сделать?
                        Path modifiedPath = ((WatchEvent<Path>) watchEvent).context();
                        logAnalyzer.parseLine(tail(Paths.get(path.toString(), modifiedPath.toString()).toFile()));
                    }
                }
                if (!key.reset()) {
                    break;
                }
            }
        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
    }

    private String tail(File file) {
        RandomAccessFile fileHandler = null;
        // можно сделать через try-with-resources
        try {
            fileHandler = new RandomAccessFile(file, "r");
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();
            for (long filePointer = fileLength; filePointer != -1; filePointer--) {
                fileHandler.seek(filePointer);
                int readByte = fileHandler.readByte();
                if (readByte == 0xA) {
                    if (filePointer == fileLength) {
                        continue;
                    }
                    break;
                } else if (readByte == 0xD) {
                    if (filePointer == fileLength - 1) {
                        continue;
                    }
                    break;
                }
                sb.append((char) readByte);
            }
            return sb.reverse().toString();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null) {
                try {
                    fileHandler.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
