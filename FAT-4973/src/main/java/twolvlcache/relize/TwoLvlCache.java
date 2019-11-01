package twolvlcache.relize;

import twolvlcache.Cache;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Two levels cache. First level it save data at ram and second level data saving to file.
 *
 * @param <Key>   key for object data.
 * @param <Value> caching data.
 */
public class TwoLvlCache<Key, Value extends Serializable> implements Cache<Key, Value> {
    private int sizeCache;
    private int size;
    private float evictionFactor;
    private int evictionSize;
    private LRUCache<Key, Value> lruCache;
    private MemoryCache<Key, Value> memoryCache;

    /**
     * Constructor for create default memory cache with default path for data.
     *
     * @param sizeCache      the size memory cache.
     * @param evictionFactor the eviction factory for rebuild cache.
     */
    public TwoLvlCache(final int sizeCache, final float evictionFactor) {
        this.sizeCache = sizeCache;
        this.size = 0;
        this.evictionFactor = evictionFactor;
        this.evictionSize = Math.round(evictionFactor * sizeCache);
        lruCache = new LRUCache<>(sizeCache, evictionFactor + 1);
        memoryCache = new MemoryCache<>(sizeCache, evictionFactor);
    }

    /**
     * Constructor for create cache with own directory for data.
     *
     * @param sizeCache       the size memory cache.
     * @param evictionFactor  the eviction factory for rebuild cache.
     * @param pathToDirectory the path where saving objects.
     */
    public TwoLvlCache(final int sizeCache, final float evictionFactor, final String pathToDirectory) {
        this.sizeCache = sizeCache;
        this.size = 0;
        this.evictionFactor = evictionFactor;
        this.evictionSize = Math.round(evictionFactor * sizeCache);
        lruCache = new LRUCache<>(sizeCache, evictionFactor + 1);
        memoryCache = new MemoryCache<>(sizeCache, evictionFactor, pathToDirectory);
    }

    /**
     * Put data object to cache.
     *
     * @param key   sign for put value on cache.
     * @param value object for cache.
     */
    @Override
    public void put(final Key key, final Value value) {
        if (sizeCache <= lruCache.getCacheSize()) {
            doEviction();
        }
        lruCache.put(key, value);
        size++;
    }

    /**
     * Pull data from cache.
     *
     * @param key sign for get value cache.
     * @return
     */
    @Override
    public Value pull(final Key key) {
        if (lruCache.pull(key) != null) {
            return lruCache.pull(key);
        } else if (memoryCache.pull(key) != null) {
            return memoryCache.pull(key);
        }
        return null;
    }

    /**
     * Eviction dates. If cache 1 lvl is fully, dates eviction in 2 lvl.
     */
    @Override
    public void doEviction() {
        int deleted = 0;
        Map<Key, CacheNode> byTimeAccess = lruCache.getAllCache().entrySet().stream()
                .sorted(Comparator.comparing(o -> o.getValue().getTimeAccess()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        while (byTimeAccess.entrySet().iterator().hasNext() && deleted <= evictionSize) {
            Key key = byTimeAccess.entrySet().iterator().next().getKey();
            if (byTimeAccess.get(key) != null) {
                memoryCache.put(key, (Value) byTimeAccess.get(key).getValue());
                lruCache.deleteElement(key);
                byTimeAccess.remove(key);
                deleted++;
                size--;
            }
        }
    }

    /**
     * Restart created cache.
     *
     * @param cacheSize the size of cache.
     */
    @Override
    public void restartCache(final int cacheSize) {
        lruCache.restartCache(cacheSize);
        memoryCache.restartCache(cacheSize);
        size = 0;
    }

    /**
     * Get size the cache.
     *
     * @return the size cache.
     */
    public int getSize() {
        return size;
    }
}
