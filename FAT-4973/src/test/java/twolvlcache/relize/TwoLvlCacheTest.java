package twolvlcache.relize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

class TwoLvlCacheTest {

    private TwoLvlCache<String, String> twoLvlCache;
    private String pathToDirectory = "g:/SerTemp/";
    private int sizeCache = 10;
    private float evictionFactor = 0.7f;

    @BeforeEach
    private void setUp() {
        twoLvlCache = new TwoLvlCache(sizeCache,evictionFactor, pathToDirectory);
    }

    @Test
    void putOneElement() {
        String testObject = "Hello world";
        String testKeyObject = "1";
        twoLvlCache.put(testKeyObject, testObject);
        Assertions.assertEquals(1, twoLvlCache.getSize());
        Assertions.assertEquals(testObject, twoLvlCache.pull(testKeyObject));
    }

    @Test
    void putFiveElement() {
        String testObject = "Hello world";
        String testKeyObject = "1";
        for (int i = 0; i < 5; i++) {
            twoLvlCache.put(testKeyObject + i, testObject + i);
        }
        Assertions.assertEquals(5, twoLvlCache.getSize());
        Assertions.assertEquals(testObject + 2, twoLvlCache.pull(testKeyObject + 2));
    }


    @Test
    void doEviction_firstEvictionInMemoryCache() {
        String testObject = "Hello world";
        String testKeyObject = "1";
        int expectedResult = Math.round(sizeCache * evictionFactor) + 1;

        for (int i = 0; i < 12; i++) {
            twoLvlCache.put(testKeyObject + i, testObject + i);
        }
        Assertions.assertEquals(expectedResult, getCountCreatedFiles());
    }

    @Test
    void pullDatesFromCache2lvl() {
        String testObject = "Hello world";
        String testKeyObject = "1";

        for (int i = 0; i < 12; i++) {
            twoLvlCache.put(testKeyObject + i, testObject + i);
        }

        String expectedResult = "Hello world1";
        Assertions.assertEquals(expectedResult, twoLvlCache.pull("11"));
    }

    private int getCountCreatedFiles() {
        File dir = new File(pathToDirectory);
        Optional<Integer> result = Optional.of(dir.list().length);
        return result.orElse(0);
    }
}