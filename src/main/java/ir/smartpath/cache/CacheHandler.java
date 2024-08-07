package ir.smartpath.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.logging.Logger;




public class CacheHandler {
    public static Logger logger = Logger.getLogger(String.valueOf(CacheHandler.class));
    static String cacheName = "name";

    static CacheManager cacheManager = new CacheManager();

    //creating cache handler
    public static Element getElement(String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
        }
        return cache.get(key);
    }

    //input values for caching
    public static String putElement(String key, String value, long expiresTime) {
        Cache cache = cacheManager.getCache(cacheName);
        Element elementPut = new Element(key, value);
        if (cache != null) {

            //dynamiting ttl
            elementPut.setTimeToLive((int) expiresTime);
            cache.put(elementPut);
        }
        return key;
    }

}



