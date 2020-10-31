package com.hannah.study.algorithm;

import java.util.Arrays;

/**
 * 排序
 */
public class Sorter {

    /**
     * 冒泡排序
     * 时间复杂度 O(n^2)；空间复杂度 O(1)
     *
     * @param a
     * @param n
     */
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; i++) {
            boolean flag = false; // 是否发生数据交换标识
            for (int j = 0; j < n - i - 1; j++) { // 后面i位已经有序
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break; // 无数据交换说明数组已经有序，提前退出
        }
    }

    /**
     * 插入排序
     * 时间复杂度 O(n^2)；空间复杂度 O(1)
     *
     * @param a
     * @param n
     */
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        _insertionSort(a, n, 1);
    }

    private static void _insertionSort(int[] a, int n, int gap) {
        for (int i = gap; i < n; i += gap) { // 右边无序区
            int temp = a[i];
            int j = i - gap;
            for (; j >= 0; j -= gap) { // 左边有序区
                // 无序区元素<有序区元素 -> 有序区右移
                if (temp < a[j]) {
                    a[j + gap] = a[j];
                } else {
                    break;
                }
            }
            a[j + gap] = temp;// 插入
        }
    }

    /**
     * 希尔排序【不稳定】
     * 时间复杂度 O(nlog2n)；空间复杂度 O(1)
     *
     * @param a
     * @param n
     */
    public static void shellSort(int[] a, int n) {
        if (n <= 1) return;

        int gap = n / 2; // 间距
        while (gap > 0) {
            // 插入排序
            _insertionSort(a, n, gap);
            gap /= 2; // 每次减半
        }
    }

    /**
     * 选择排序【不稳定】
     * 时间复杂度 O(n^2)；空间复杂度 O(1)
     *
     * @param a
     * @param n
     */
    public static void selectionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 0; i < n; i++) { // 循环次数
            int k = i;
            // 取右边最小元素
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            // 交换
            if (k != i) {
                swap(a, i, k);
            }
        }
    }

    /**
     * 归并排序
     * 时间复杂度 O(nlog2n)；空间复杂度 O(n)
     * F(p...r) = M(F(p...q) + F(q+1...r))
     *
     * @param a
     * @param n
     */
    public static void mergeSort(int[] a, int n) {
        _mergeSort(a, 0, n - 1);
    }

    private static void _mergeSort(int[] a, int p, int r) {
        if (p >= r) return;

        int q = (p + r) / 2; // 分组
        // 分治
        _mergeSort(a, p, q);
        _mergeSort(a, q + 1, r);
        // 合并
        _merge(a, p, q, r);
    }

    private static void _merge(int[] a, int p, int q, int r) {
        int[] temp = new int[r - p + 1];
        int k = 0, i = p, j = q + 1;
        // 循环归并
        while (i <= q || j <= r) {
            if (i > q) { // p..q 边界
                temp[k++] = a[j++];
            } else if (j > r) { // q+1..r 边界
                temp[k++] = a[i++];
            } else if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 拷贝回去
        for (k = 0; k < r - p + 1; k++) {
            a[k + p] = temp[k];
        }
    }

    /**
     * 快速排序【不稳定】
     * 时间复杂度 O(nlog2n)；空间复杂度 O(1)
     * F(p...r) = F(p...q) + F(q+1...r)
     *
     * @param a
     * @param n
     */
    public static void quickSort(int[] a, int n) {
        _quickSort(a, 0, n - 1);
    }

    private static void _quickSort(int[] a, int p, int r) {
        if (p >= r) return;

        int q = _partition(a, p, r);
        _quickSort(a, p, q - 1);
        _quickSort(a, q + 1, r);
    }

    private static int _partition(int[] a, int p, int r) {
        int pivot = a[r]; // 基准元素
        int i = p; // 左边<基准；右边>基准
        for (int j = p; j < r; j++) {
            if (a[j] < pivot) {
                swap(a, i++, j);
            }
        }
        swap(a, i, r);
        return i;
    }

    /**
     * 交换元素
     *
     * @param a
     * @param i
     * @param k
     */
    private static void swap(int[] a, int i, int k) {
        if (i == k) return;

        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3, 5, 4, 1, 7, 9, 2, 6, 8};
        Sorter.quickSort(a, a.length);
        System.out.println(Arrays.toString(a));
    }

}
