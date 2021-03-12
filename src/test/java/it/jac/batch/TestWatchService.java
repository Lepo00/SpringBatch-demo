package it.jac.batch;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.core.io.ClassPathResource;

public class TestWatchService {

	public static void main(String[] args) throws Exception {
		WatchService watcher = FileSystems.getDefault().newWatchService();
		Path dir = Paths.get(System.getProperty("java.io.tmpdir")+"\\images");
		    WatchKey key = dir.register(watcher, ENTRY_CREATE);
		    while(true) {
		        try {
		        	System.out.println("Attendo inserimento file");
		        	System.out.println();
		            key = watcher.take();
		        } catch (InterruptedException x) {
		            return;
		        }
		        System.out.println("Inserimento file eseguito");
		        for (WatchEvent<?> event: key.pollEvents()) {
		            WatchEvent.Kind<?> kind = event.kind();
		            if (kind == OVERFLOW) {
		                continue;
		            }
		            WatchEvent<Path> ev = (WatchEvent<Path>)event;
		            Path filename = ev.context();
		            System.out.println("Filename: "+filename);
		        }
		        boolean valid = key.reset();
		        if (!valid) {
		            break;
		        }
		    }
	}

}
