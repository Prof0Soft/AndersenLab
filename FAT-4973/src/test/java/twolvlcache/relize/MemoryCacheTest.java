package twolvlcache.relize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryCacheTest {

    private static MemoryCache<String, String> memoryCache;
    private static String testOwnObject = "My own test object ";

    @BeforeEach
    private void setUp() {
        memoryCache = new MemoryCache(15, 0.7f);
        memoryCache.put("1", testOwnObject);
    }

    @Test
    void putOneField() {
        String myObject = "My test object";
        int expectCount = memoryCache.getSize() + 1;
        memoryCache.put("5", myObject);
        Assertions.assertEquals(expectCount, memoryCache.getSize());
        Assertions.assertEquals(myObject, memoryCache.pull("5"));
    }

    @Test
    void pullOneStandardObject() {
        String result = (String) memoryCache.pull("1");
        Assertions.assertEquals(testOwnObject, result);
    }

    @Test
    void pullOneOtherObject() {
        String myObject = "HH";
        String key = "2";
        memoryCache.put(key, myObject);
        String result = (String) memoryCache.pull(key);
        Assertions.assertEquals(myObject, result);
    }

    @Test
    void pullNotFindObjectAndReturnNull() {
        String result = (String) memoryCache.pull("Not created object");
        Assertions.assertNull(result);
    }


}