package com.hannah.study.data;

/**
 * 基于数组的循环队列
 * @param <T>
 */
public class CArrayQueue<T> extends ArrayQueue<T> {

    public CArrayQueue(int capacity) {
        super(capacity);
    }

    @Override
    public int size() {
        return tail >= head ? tail - head : tail - head + capacity;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    @Override
    public boolean offer(T item) {
        if (isFull()) {
            System.out.println("Error: 队列空间已满，无法入队");
            return false;
        }
        data[tail++] = item;
        // 循环：从0重新开始
        if (tail == capacity) tail = 0;
        return true;
    }

    public static void main(String[] args) {
        IQueue<Integer> queue = new CArrayQueue<>(10);
        for (int i = 0; i < 8; i++) {
            queue.offer(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.pool());
        }
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.offer(i*10);
            System.out.println(queue);
        }
    }

}
