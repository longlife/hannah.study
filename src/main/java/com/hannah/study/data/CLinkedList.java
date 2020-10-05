package com.hannah.study.data;

/**
 * 单循环链表
 */
public class CLinkedList<T> extends SLinkedList<T> {

    public CLinkedList(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean isTailNode(SNode<T> node) {
        return node.getNext() == getHeadNode();
    }

    @Override
    protected SNode<T> getTailNextNode() {
        return getHeadNode();
    }
}
