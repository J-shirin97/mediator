package ir.smartpath.cache;


import ir.smartpath.connection.HttpConnection;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.time.Duration;

import static com.sun.corba.se.impl.util.RepositoryId.cache;


public class CacheHandler {

/*    public static void ehcacheManager(String path ) {
        //1. Create a cache manager
        CacheManager cacheManager = CacheManager.getInstance();



        Element element = new Element(key, value);
        element.setTimeToLive(60);
        cache.put(element);


    }*/


















/*

        //2. Create a cache called "cache1"
        cacheManager.addCache("cache1");

        //3. Get a cache called "cache1"
        Cache cache = cacheManager.getCache("cache1");

        //4. Put few elements in cache
        cache.put(new Element("1","Jan"));
        cache.put(new Element("2","Feb"));
        cache.put(new Element("3","Mar"));

        //5. Get element from cache
        Element element = cache.get("1");

        //6. Print out the element
        String output = (element == null ? null : element.getObjectValue().toString());

        //7. Is key in cache?
        System.out.println(cache.isKeyInCache("1"));
        System.out.println(cache.isKeyInCache("2"));


        //8. shut down the cache manager
        cacheManager.shutdown();
*/


}

