package ir.smartpath.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.logging.Logger;


public class CacheHandler {
    static Logger logger = Logger.getLogger(String.valueOf(CacheHandler.class));
    static String cacheName = "name";
    static CacheManager cacheManager = new CacheManager();

    public static void ehcacheManager(String key, String value) {
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

    public static Element getElement(String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
        }
        return cache.get(key);
    }

    public static void putElement(String key, String value) {
        Cache cache = cacheManager.getCache(cacheName);
        Element elementPut = new Element(key, value);
        if (cache != null) {
            cache.put(elementPut);
        }
    }
}



