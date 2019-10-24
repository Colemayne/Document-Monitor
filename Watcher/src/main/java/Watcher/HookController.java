package Watcher;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HookController {
    Gson gson = new Gson();
    DirectoryMonitor directoryMonitor = new DirectoryMonitor("/data/xml");

    @RequestMapping(value = "/start")
    public void start() {
        directoryMonitor.startWatching();
    }

    @RequestMapping(value = "/request")
    public String returnQueue(){
        ArrayList<String> allInQueue = directoryMonitor.getQueue();
        String queueJson = gson.toJson(allInQueue);
        return queueJson;
    }


}

