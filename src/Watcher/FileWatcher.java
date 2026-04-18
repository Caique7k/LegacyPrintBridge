package watcher;

import java.io.File;
import java.nio.file.*;

public class FileWatcher {

    public interface FileHandler {
        void handle(File file);
    }

    public void watch(String path, FileHandler handler) throws Exception {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Paths.get(path).register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

        System.out.println("Monitorando: " + path);

        while (true) {
            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                Path filePath = Paths.get(path).resolve((Path) event.context());

                Thread.sleep(500); // espera terminar escrita

                handler.handle(filePath.toFile());
            }

            key.reset();
        }
    }
}