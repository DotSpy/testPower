import com.power.service.WatchingService;
import com.power.service.impl.WatchingServiceImpl;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // добавь на всякий случай проверку от дурака, а то если параметры не придут
        // словишь ошибку
        WatchingService watchingService = WatchingServiceImpl.INSTANCE;
        File dir = new File(args[0]);
        watchingService.watchDirectory(dir.toPath());
    }
}
