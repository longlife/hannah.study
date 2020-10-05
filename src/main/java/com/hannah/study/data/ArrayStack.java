package com.hannah.study.data;

import java.util.Arrays;

/**
 * 基于数组的栈
 * @param <T>
 */
public class ArrayStack<T> implements IStack<T> {

    private T[] data; // 数组内容
    private int capacity; // 数组容量
    private int length; // 数组长度

    public ArrayStack(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean push(T item) {
        if (length >= capacity) {
            System.out.println("Error: 栈空间已满，无法入栈");
            return false;
        }
        data[length++] = item;
        return true;
    }

    @Override
    public T pop() {
        T item = peek();
        data[length-1] = null;
        length--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Error: 栈空间已空，无法出栈");
            return null;
        }
        return data[length-1];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        IStack<Integer> stack = new ArrayStack<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
        System.out.println(stack);
    }

}
