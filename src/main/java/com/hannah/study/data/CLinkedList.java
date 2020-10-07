package com.hannah.study.data;

/**
 * 循环单链表
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

    @Override
    public T removeByPreNode(SNode<T> preNode) {
        // 前置节点：尾节点 -> 头节点
        if (isTailNode(preNode)) {
            preNode = getHeadNode();
        }
        return super.removeByPreNode(preNode);
    }

}
