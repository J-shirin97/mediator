package ir.smartpath.cache;


import net.sf.ehcache.*;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;

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
    public final Ehcache createDefaultCache() throws CacheException {
        Configuration configuration = new Configuration();
        CacheConfiguration cacheConfiguration = configuration.getDefaultCacheConfiguration();
        if (cacheConfiguration == null) {
            return null;
        } else {
            cacheConfiguration.setName(Cache.DEFAULT_CACHE_NAME);
            return createCache(cacheConfiguration);
        }
    }

    private Ehcache createCache(CacheConfiguration cacheConfiguration) {

        return (Ehcache) cacheConfiguration;
    }

}



