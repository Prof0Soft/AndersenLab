package sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class QuicklySortTest {

    @Test
    public void sort() {
        Integer[] arr = {3, 8, 1, 5, 10};
        Integer[] expectResult = {1, 3, 5, 8, 10};
        Integer[] result = QuicklySort.sort(arr);
        Assert.assertArrayEquals(expectResult, result);
    }
}
