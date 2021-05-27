package com.ironass.algorithm.find;

public class BinarySearch {

    public static void main(String[] args) {

        int[] arr = new int[]{0, 2, 4, 5, 6, 7, 10};
        int num = 6;
        System.out.println(binarySearch(arr, num));
    }

    private static int binarySearch(int[] a, int num) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        while (low < high) {
//            int mid = (high + low) / 2;
            //优化，防止整形溢出  low+((high-low)/2)
//            int mid = low + (high - low) / 2;
            //再次优化，使用位运算
            int mid = low + ((high - low) >> 1);
            if (num < a[mid]) {
                high = mid + 1;
            } else if (num > a[mid]) {
                low = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int binarySearch02(int[] a, int low, int high, int value) {
        if (a == null || a.length == 0 || low > high) {
            return -1;
        }

        int mid = low + ((high - low)>> 2);
        if(value > a[mid]){
            return binarySearch02(a, mid + 1, high, value);
        } else if( value < a[mid]){
            return  binarySearch02(a, low, mid - 1, value);
        } else {
            return mid;
        }
    }
}
