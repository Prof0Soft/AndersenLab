package sorting;

import org.junit.Assert;
import org.junit.Test;


public class SortChoiceTest {

    @Test
    public void sortChoice() {
        Integer[] arr = {12, 5, 6, 8, 4, 1};
        Integer[] expectArr = {1, 4, 5, 6, 8, 12};
        SortChoice sortChoice = new SortChoice();
        Assert.assertArrayEquals(expectArr, sortChoice.sort(arr));
    }
}
