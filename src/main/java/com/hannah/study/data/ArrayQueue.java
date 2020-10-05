package com.hannah.study.data;

import java.util.Arrays;

/**
 * 基于数组的队列
 * @param <T>
 */
public class ArrayQueue<T> implements IQueue<T> {

    protected T[] data; // 数组内容
    protected int capacity; // 数组容量
    protected int head; // 队头下标
    protected int tail; // 队尾下标

    public ArrayQueue(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public int size() {
        return tail - head;
    }

    public boolean isFull() {
        return size() >= capacity;
    }

    @Override
    public boolean offer(T item) {
        if (isFull()) {
            System.out.println("Error: 队列空间已满，无法入队");
            return false;
        }
        // 尾下标已超出
        if (tail == capacity) {
            defragment();
        }
        data[tail++] = item;
        return true;
    }

    /**
     * 整理
     */
    private void defragment() {
        // 整体移动到数组首位【0下标】
        for (int i = head; i < tail; i++) {
            data[i - head] = data[i];
        }
        // 清空tail后面的元素
        int j = tail - head;
        for (; j < tail; j++) {
            data[j] = null;
        }
        tail -= head;
        head = 0;
    }

    @Override
    public T pool() {
        T item = peek();
        data[head++] = null;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Error: 队列空间已空，无法出队");
            return null;
        }
        return data[head];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

}
