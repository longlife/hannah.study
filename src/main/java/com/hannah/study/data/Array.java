package com.hannah.study.data;

import java.util.Arrays;

/**
 * 数组
 * 操作：插入、删除、根据下标随机访问
 */
public class Array<T> {

    private T[] data; // 数组内容
    private int capacity; // 数组容量
    private int length; // 数组长度

    /**
     * 构造方法
     * @param capacity
     */
    public Array(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    public int size() {
        return length;
    }

    /**
     * 根据下标随机访问
     * @param index
     * @return 越界：null
     */
    public T find(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Error: 数组越界");
            return null;
        }
        return this.data[index];
    }

    /**
     * 插入
     * @param index
     * @param value
     * @return
     */
    public boolean insert(int index, T value) {
        if (length >= capacity) {
            System.out.println("Error: 数组空间已满，无法插入");
            return false;
        }
        if (index < 0 || index > length) {
            System.out.println("Error: 数组越界");
            return false;
        }
        // >index的所有元素往后移一位
        for (int i = length; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = value;
        length++;
        return true;
    }

    /**
     * 删除
     * @param index
     * @return
     */
    public boolean delete(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Error: 数组越界");
            return false;
        }
        // >index的所有元素往前移一位
        for (int i = index + 1; i < length; i++) {
            this.data[i - 1] = this.data[i];
        }
        this.data[--length] = null;
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        Array<Integer> array = new Array(5);
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.insert(2, 99);
        System.out.println("插入后：" + array);
        System.out.println("第3个数据：" + array.find(3));
        array.delete(3);
        System.out.println("删除后：" + array);
    }

}
