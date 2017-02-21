import com.power.service.WatchingService;
import com.power.service.impl.WatchingServiceImpl;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        WatchingService watchingService = WatchingServiceImpl.INSTANCE;
        File dir = new File("D:\\test");
        watchingService.watchDirectory(dir.toPath());
    }
}
