package Watcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import static java.nio.file.StandardWatchEventKinds.*;

public class DirectoryMonitor {
	
    private String watchDirectory = "/data/xml";
    public DirectoryScanner directoryScanner = new DirectoryScanner(watchDirectory);    
    
	
	public DirectoryMonitor(String watchDirectory) {
		this.watchDirectory = watchDirectory;

	}
	
	public String getWatchDirectory() {
		return watchDirectory;
	}
	public void setWatchDirectory(String watchDirectory) {
		this.watchDirectory = watchDirectory;
	}
	
	
    public void startWatching() {
        try {
            // Creates a instance of WatchService.
            WatchService watcher = FileSystems.getDefault().newWatchService();
            directoryScanner.scanDirectory();

            // Registers the logDir below with a watch service.
            Path logDir = Paths.get(watchDirectory);
            logDir.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

            // Monitor the logDir at listen for change notification.
            while (true) {
                WatchKey key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (ENTRY_CREATE.equals(kind)) {
                        directoryScanner.scanDirectory();
                    } else if (ENTRY_MODIFY.equals(kind)) {
                    	directoryScanner.scanDirectory();
                    } else if (ENTRY_DELETE.equals(kind)) {
                    	directoryScanner.scanDirectory();
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getQueue(){
        ArrayList<String> queueOfFiles = new ArrayList<String>();
        queueOfFiles = directoryScanner.returnQueue();
        return queueOfFiles;

    }


}
