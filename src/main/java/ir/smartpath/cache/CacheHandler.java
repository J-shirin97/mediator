package ir.smartpath.cache;


import ir.smartpath.connection.HttpConnection;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.logging.Logger;


public class CacheHandler {
    static Logger logger = Logger.getLogger(String.valueOf(CacheHandler.class));

    public static void ehcacheManager(String key, String value) {

        CacheManager cacheManager = new CacheManager();

        Cache cache = cacheManager.getCache(value);
        if (cache == null) {
            cacheManager.addCache(value);
            cache = cacheManager.getCache(value);
        }

        Element elementt = cache.get(key);
        if (elementt == null) {
            Element element = new Element(key, value);
            element.setTimeToLive(10);
            cache.put(element);



        }

    }
}


