package twolvlcache.relize;


import twolvlcache.Cache;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Class for relize cache.
 *
 * @param <Key>   key for object
 * @param <Value> value by object.
 */
public class LRUCache<Key, Value extends Serializable> implements Cache<Key, Value> {

    private int cacheSize;
    private Map<Key, CacheNode> cache;
    private float evictionFactor;
    private int evictionSize;

    /**
     * Constructor create and initialize cache.
     *
     * @param cacheSize      size buffer cache.
     * @param evictionFactor the contract for eviction data.
     */
    public LRUCache(final int cacheSize, final float evictionFactor) {
        this.cacheSize = cacheSize;
        this.evictionFactor = evictionFactor;
        this.evictionSize = (int) (cacheSize * evictionFactor);
        this.cache = new ConcurrentHashMap<>(cacheSize);
    }

    /**
     * Put for elements.
     *
     * @param key   sign for put value on cache.
     * @param value object for cache.
     */
    public synchronized void put(final Key key, final Value value) {
        if (key == null) {
            return;
        }

        // if value is created, update information about value.
        if (cache.get(key) != null) {
            cache.get(key).updateData(value);
            return;
        }

        // if size is full push out old data on cache
        if (cache.size() >= cacheSize) {
            doEviction();
        }

        cache.put(key, new CacheNode(key, value));
    }

    public Map<Key, CacheNode> getAllCache() {
        return cache;
    }

    /**
     * Delete single field from data cache. Searching old date of value and push out is it.
     */
    public synchronized void doEviction() {
        if (cache.size() < cacheSize) {
            return;
        }
        int deleted = 0;

        Map<Key, CacheNode> byTimeAccess = cache.entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getTimeAccess()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        while (byTimeAccess.entrySet().iterator().hasNext() && deleted <= evictionSize) {
            Key key = byTimeAccess.entrySet().iterator().next().getKey();
            byTimeAccess.remove(key);
            if (cache.get(key) != null) {
                cache.remove(key);
                deleted++;
            }
        }
    }

    /**
     * Pull for element.
     *
     * @param key sign for get value cache.
     * @return the element by key.
     */
    public synchronized Value pull(final Key key) {
        if (cache.get(key) == null) {
            return null;
        }

        cache.get(key).setTimeAccess(System.currentTimeMillis());
        return (Value) cache.get(key).getValue();
    }

    /**
     * Clear and rebuild cache with new size depend.
     *
     * @param cacheSize the size of cache.
     */
    public void restartCache(final int cacheSize) {
        cache.clear();
        this.evictionSize = (int) (cacheSize * evictionFactor);
        cache = new ConcurrentHashMap<>(cacheSize);
        this.cacheSize = cacheSize;
    }

    public int getCacheSize() {
        return cache.size();
    }

    public void deleteElement(final Key key) {
        cache.remove(key);
    }

    /**
     * Setter for eviction factor variable.
     *
     * @param evictionFactor the variable for eviction data from cache.
     */
    public void setEvictionFactor(final float evictionFactor) {
        this.evictionFactor = evictionFactor;
    }

    @Override
    public String toString() {
        String result = "Cache -> Size = " + cache.size() + "\r\n";
        for (Map.Entry<Key, CacheNode> entry : cache.entrySet()) {
            result += "Key = " + entry.getKey() + " -> Value{" + entry.getValue() + "}\r\n";
        }
        return result;
    }


}
