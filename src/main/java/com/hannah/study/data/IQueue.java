package com.hannah.study.data;

/**
 * 队列
 */
public interface IQueue<T> {

    /**
     * 数量
     * @return
     */
    int size();

    /**
     * 空
     * @return
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    boolean offer(T item);

    /**
     * 出队
     * @return
     */
    T pool();

    /**
     * 一瞥：取队头数据但不出队
     * @return
     */
    T peek();

}
