package twolvlcache;

/**
 * Interface for realization generic caches.
 * @param <Key> Key for cache collection.
 * @param <Value> Value for cache collection.
 */
public interface Cache<Key, Value> {
    /**
     * Put method for new cache's elements.
     * @param key sign for put value on cache.
     * @param value object for cache.
     */
    void put(Key key, Value value);

    /**
     *  Pull method for cache's elements.
     * @param key sign for get value cache.
     * @return Element by key.
     */
    Value pull(Key key);

    /**
     * Delete single field from data cache (for single contract).
     * @return return true if field was deleted.
     */
    void doEviction();

    /**
     * Clear and rebuild cache with new size depend.
     * @param cacheSize the size of cache.
     */
    void restartCache(int cacheSize);


}
