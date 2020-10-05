package com.hannah.study.data;

/**
 * 栈
 * @param <T>
 */
public interface IStack<T> {

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
     * 入栈
     * @param item
     * @return
     */
    boolean push(T item);

    /**
     * 出栈
     * @return
     */
    T pop();

    /**
     * 一瞥：取栈顶数据但不出栈
     * @return
     */
    T peek();

}
