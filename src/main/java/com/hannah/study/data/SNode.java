package com.hannah.study.data;

/**
 * 单链表节点
 */
public class SNode<T> {

    private T item;
    private SNode<T> next;

    public SNode() {
    }

    public SNode(T item) {
        this.item = item;
    }

    public SNode(T item, SNode<T> next) {
        this.item = item;
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public SNode<T> getNext() {
        return next;
    }

    public void setNext(SNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SNode{" +
                "item=" + item +
                '}';
    }
}
