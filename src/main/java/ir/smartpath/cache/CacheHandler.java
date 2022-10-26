package ir.smartpath.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class CacheHandler {
    /*static Logger logger = Logger.getLogger(String.valueOf(CacheHandler.class));*/

    public static void ehcacheManager(Object key, String value) {
        CacheManager singletonManager = CacheManager.create();
        singletonManager.addCache("Cache");


        /*CacheManager cacheManager = new CacheManager();*/

        Cache cache = cacheManager.getCache(value);
        if (cache != null) {
            Element element = new Element(key, value);
            cache.put(element);
            cache.get(key);
        }
        if (cache == null) {
            throw new CacheException("null !");
        }
    }
}


