package sorting;

/**
 * Sorting of quickly method.
 */
public class QuicklySort {

    /**
     * Quickly method of sorting.
     * @param arr massive data.
     * @return sorted massive.
     */
    static Integer[] sort(final Integer[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            Integer[] pivot = new Integer[1];
            pivot[0] = arr[0];
            int countLess = countLess(arr, pivot[0]);

            Integer[] less = new Integer[countLess - 1];
            Integer[] greater = new Integer[arr.length - less.length - 1];

            int countL = 0;
            int countG = 0;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] <= pivot[0]) {
                    less[countL++] = arr[i];
                } else {
                    greater[countG++] = arr[i];
                }
            }

            Integer[] resultMass = new Integer[less.length + pivot.length + greater.length];
            System.arraycopy(sort(less), 0, resultMass, 0, less.length);
            System.arraycopy(pivot, 0, resultMass, less.length, pivot.length);
            System.arraycopy(sort(greater), 0, resultMass, less.length + pivot.length, greater.length);

            return resultMass;
        }

    }

    /**
     * Count numbers for less massive.
     *
     * @param arr   original massive
     * @param pivot number for choice.
     * @return count numbers for less massive.
     */
    private static int countLess(final Integer[] arr, final int pivot) {
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= pivot) {
                count++;
            }
        }
        return count;
    }
}
