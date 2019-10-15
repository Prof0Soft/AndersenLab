package sorting;

/**
 * Class for use sorting of choice.
 */
public class SortChoice {

    /**
     * Search smaller index in massive;
     *
     * @param arr - massive for search index.
     * @return smaller index of number massive.
     */
    private int smallest(final Integer[] arr) {
        int small = arr[0];
        int indexSmall = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (arr[i] < small) {
                    small = arr[i];
                    indexSmall = i;
                }
            }
        }
        return indexSmall;
    }

    /**
     * Sorting data in massive.
     * @param arr - massive for sorting.
     * @return sorted massive.
     */
    public Integer[] sort(final Integer[] arr) {
        Integer[] resultArr = new Integer[arr.length];
        int indexSmall;

        for (int i = 0; i < arr.length; i++) {
            indexSmall = smallest(arr);
            resultArr[i] = arr[indexSmall];
            arr[indexSmall] = null;
        }
        return resultArr;
    }


}
