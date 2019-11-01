package twolvlcache.relize;

import org.apache.commons.io.FileUtils;
import twolvlcache.Cache;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Class for cache data in file memory.
 *
 * @param <Key>
 */
public class MemoryCache<Key, Value> implements Cache<Key, Value> {
    private String pathToDirectory = "g:/SerTemp/";
    private int cacheSize;
    private Map<Key, String> cache;
    private Map<Key, Long> cacheTime;
    private int size = 0;
    private float evictionFactor;
    private int evictionSize;

    /**
     * Constructor create and initialize cache.
     *
     * @param cacheSize      size buffer cache.
     * @param evictionFactor the contract for eviction data.
     */
    public MemoryCache(final int cacheSize, final float evictionFactor) {
        this.cacheSize = cacheSize;
        this.evictionFactor = evictionFactor;
        this.evictionSize = (int) (cacheSize * evictionFactor);
        this.cache = new ConcurrentHashMap<>(cacheSize);
        this.cacheTime = new ConcurrentHashMap<>(cacheSize);

        //if folder not created, make it
        File dir = new File(pathToDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }

        //clear directory from file
        try {
            FileUtils.cleanDirectory(new File(pathToDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor create and initialize cache.
     *
     * @param cacheSize      size buffer cache.
     * @param evictionFactor the contract for eviction data.
     */
    public MemoryCache(final int cacheSize, final float evictionFactor, final String pathToLocateCache) {
        this.cacheSize = cacheSize;
        this.evictionFactor = evictionFactor;
        this.evictionSize = (int) (cacheSize * evictionFactor);
        this.cache = new ConcurrentHashMap<>(cacheSize);
        this.cacheTime = new ConcurrentHashMap<>(cacheSize);
        this.pathToDirectory = pathToLocateCache;

        //if folder not created, make it
        File dir = new File(pathToDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }

        //clear directory from file
        try {
            FileUtils.cleanDirectory(new File(pathToDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put for elements.
     *
     * @param key sign for put value on cache.
     */
    public synchronized void put(final Key key, final Value value) {
        if (key == null) {
            return;
        }

        // if size is full push out old data on cache
        if (size >= cacheSize) {
            doEviction();
        }

        String pathToSaveObject = pathToDirectory + key.hashCode() + ".txt";
        try (FileOutputStream outputStream = new FileOutputStream(pathToSaveObject);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cache.put(key, pathToSaveObject);
        cacheTime.put(key, System.currentTimeMillis());
        size++;
    }

    /**
     * Get size the cache.
     *
     * @return count of size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Delete single field from data cache. Searching old date of value and push out is it.
     */
    public synchronized void doEviction() {
        if (size < cacheSize) {
            return;
        }

        Map<Key, Long> byTimeAccess = cacheTime.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        int deleted = 0;
        while (byTimeAccess.entrySet().iterator().hasNext() && deleted <= evictionSize) {
            Key key = byTimeAccess.entrySet().iterator().next().getKey();
            if (cache.get(key) != null) {
                cache.remove(key);
                cacheTime.remove(key);

                File deleteFile = new File(pathToDirectory + key.hashCode() + ".txt");
                deleteFile.delete();
                byTimeAccess.remove(key);
                size--;
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
        Value result = null;
        if (cache.get(key) == null) {
            return result;
        }

        String pathToSaveObject = pathToDirectory + key.hashCode() + ".txt";
        try (FileInputStream fileInputStream = new FileInputStream(pathToSaveObject);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            result = (Value) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;

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
        size = 0;

        //clear directory from file
        try {
            FileUtils.cleanDirectory(new File(pathToDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for (Map.Entry<Key, String> entry : cache.entrySet()) {
            result += "Key = " + entry.getKey() + " -> Value{" + entry.getValue() + "}\r\n";
        }
        return result;
    }

}
