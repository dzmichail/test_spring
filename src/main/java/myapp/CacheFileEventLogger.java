package myapp;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    int cacheSize;
    List<Event> cache;

    public CacheFileEventLogger(String filename, int cacheSize){
//        super(filename);
        this.cacheSize=cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    private void writeEventsFromCache() {
        this.cache.stream().forEach(super::logEvent);
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);

        if (cache.size()==this.cacheSize){
            this.writeEventsFromCache();
            cache.clear();
        }
    }

    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }
}
