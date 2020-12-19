package com.hannah.study.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 查找
 */
public class Searcher {

    /**
     * 二分查找法
     * 时间复杂度 O(logn)；空间复杂度 O(1)
     * @param a 有序数组
     * @param n
     * @param value
     * @return
     */
    public static int binarySearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1); // 除2可以用位运算优化
            if (a[mid] < value) {
                low = mid + 1; // mid位<value：取右边继续
            } else if (a[mid] > value) {
                high = mid - 1; // mid位>value：取左边继续
            } else {
                return mid;
                /**
                 * 存在重复元素的情况
                 * 查找第一个
                 * if ((mid == 0) || (a[mid - 1] != value)) return mid;
                 * else high = mid - 1;
                 * 查找最后一个
                 * if ((mid == n - 1) || (a[mid + 1] != value)) return mid;
                 * else low = mid + 1;
                 */
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        }
        System.out.println("原数组：" + Arrays.toString(a));
        Sorter.quickSort(a, a.length);
        System.out.println("新数组：" + Arrays.toString(a));

        int value = random.nextInt(100);
        int index = Searcher.binarySearch(a, a.length, value);
        System.out.println(String.format("查找值[%s]所在位置：%s", value, index));
    }
}
