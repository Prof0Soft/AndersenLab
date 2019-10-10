package sorting;

public class SortChoise {

    private int smallest(final int[] arr) {
        int small = arr[0];
        int indexSmall = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < small) {
                small = arr[i];
                indexSmall = i;
            }
        }
        return indexSmall;
    }

    public int[] sortChoise(final int[] arr) {
        int[] resultArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            resultArr[i] = arr[smallest(arr)];
        }
        return resultArr;
    }
}
