package numbercollection.realisation;

import numbercollection.NumberListCollection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberListTest {
    private int kElements = 5;
    private NumberListCollection<Integer> list1 = new NumberList<>();

    @Before
    public void createList1() {
        for (int i = 0; i < kElements; i++) {
            list1.add(i * 2);
        }
    }

    @Test
    public void getSize() {
        Assert.assertEquals(kElements, list1.size());
    }

    @Test
    public void getById() {
        int i = 2;
        Integer expectedResult = i * 2;
        Assert.assertEquals(expectedResult, list1.get(i));
    }

    @Test
    public void isEmptyListIsTrue() {
        NumberListCollection<Integer> list = new NumberList<>();
        Assert.assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyListIsFalse() {
        NumberListCollection<Integer> list = new NumberList<>();
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void containsObjectIsTrue() {
        Integer o = 0;
        Assert.assertTrue(list1.contains(o));
    }

    @Test
    public void containsObjectIsFalse() {
        Integer o = 100;
        Assert.assertFalse(list1.contains(o));
    }

    @Test
    public void indexOf() {
        Integer o = 0;
        Assert.assertEquals(0, list1.indexOf(o));
    }

    @Test
    public void max() {
        Integer maxVal = 100;
        list1.add(maxVal);
        Assert.assertEquals(maxVal, list1.max());
    }

    @Test
    public void min() {
        Integer minVal = -1;
        list1.add(minVal);
        Assert.assertEquals(minVal, list1.min());
    }

    @Test
    public void averageInteger() {
        NumberListCollection<Integer> list = new NumberList<>();
        Integer expectedResult = 5;
        list.add(expectedResult);
        list.add(expectedResult);
        list.add(expectedResult);
        list.add(expectedResult);
        Assert.assertEquals(expectedResult, list.average());
    }

    @Test
    public void averageDouble() {
        NumberListCollection<Double> list = new NumberList<>();
        Double expectedResult = 5.0;
        list.add(expectedResult);
        list.add(expectedResult);
        list.add(expectedResult);
        list.add(expectedResult);
        Assert.assertEquals(expectedResult, list.average());
    }

    @Test
    public void averageLong() {
        NumberListCollection<Long> list = new NumberList<>();
        Long expectedResult = 2L;
        list.add(2L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        Assert.assertEquals(expectedResult, list.average());
    }

    @Test
    public void removeById() {
        int oldSize = list1.size();
        int deleteIndex = 1;
        Integer oldValueByIndex = list1.get(deleteIndex);
        list1.remove(deleteIndex);
        Assert.assertTrue(oldSize > list1.size());
        Assert.assertNotEquals(oldValueByIndex, list1.get(deleteIndex));
    }

    @Test
    public void addMoreCapacity(){
        NumberListCollection<Double> list2 = new NumberList<>();
        for (int i = 0; i < 50; i++) {
            list2.add(4D);
        }
    }

}