package com.hannah.study.data;

/**
 * 单链表
 */
public class SLinkedList<T> {

    private SNode<T> headNode; // 头节点【哨兵】
    private int capacity; // 容量
    private int length; // 长度

    public SLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.headNode.setNext(getTailNextNode());
        this.capacity = capacity;
        this.length = 0;
    }

    public SNode<T> getHeadNode() {
        return headNode;
    }

    public int size() {
        return length;
    }

    /**
     * 是否尾节点
     * @param node
     * @return
     */
    protected boolean isTailNode(SNode<T> node) {
        return node.getNext() == null;
    }

    /**
     * 尾节点的后置节点
     * @return
     */
    protected SNode<T> getTailNextNode() {
        return null;
    }

    /**
     * 插入节点
     * @param item
     * @return
     */
    public boolean add(T item) {
        // 超过容量，删除尾节点
        if (length >= capacity) {
            removeTailNode();
        }
        return add(headNode, item);
    }

    /**
     * 插入节点
     * @param node
     * @param item
     * @return
     */
    public boolean add(SNode<T> node, T item) {
        if (length >= capacity) {
            System.out.println("Error: 链表空间已满，无法插入");
            return false;
        }
        SNode<T> next = node.getNext();
        node.setNext(new SNode<T>(item, next));
        length++;
        return true;
    }

    /**
     * 查找前置节点
     * @param item
     * @return
     */
    public SNode<T> findPreNode(T item) {
        SNode<T> node = headNode;
        while (!isTailNode(node)) {
            if (item.equals(node.getNext().getItem())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除节点
     * @param preNode 前置节点
     * @return
     */
    public T removeByPreNode(SNode<T> preNode) {
        // 尾节点：直接返回
        if (isTailNode(preNode)) return null;

        SNode<T> temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        length--;
        return temp.getItem();
    }

    /**
     * 删除尾节点
     */
    public T removeTailNode() {
        SNode<T> node = findPreNodeOfTail();
        if (node == null) return null;
        return removeByPreNode(node);
    }

    /**
     * 查找尾节点的前置节点
     * @return
     */
    public SNode<T> findPreNodeOfTail() {
        // 空链表：直接返回
        if (size() == 0) return null;

        SNode<T> node = headNode;
        // 尾节点的前置节点
        while (!isTailNode(node.getNext())) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * 获取中间节点
     * @return
     */
    public SNode<T> findMiddleNode() {
        // 空链表：直接返回
        if (size() == 0) return null;

        SNode<T> slow = headNode.getNext(); // 慢指针：每次走一步
        SNode<T> fast = headNode.getNext().getNext(); // 快指针：每次走两步
        while (fast != getTailNextNode() && !isTailNode(fast)) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        // 偶数位：slow指针往前走一步
        boolean isEven = fast != getTailNextNode();
        return isEven ? slow : slow;
    }

    /**
     * 是否回文结构
     * @return
     */
    public boolean isPalindrome() {
        // 空链表：直接返回
        if (size() == 0) return true;

        SLinkedList<T> reversedList = new SLinkedList<>(capacity/2 + 1);
        SNode<T> slow = headNode.getNext(); // 慢指针：每次走一步
        SNode<T> fast = headNode.getNext().getNext(); // 快指针：每次走两步
        while (fast != getTailNextNode() && !isTailNode(fast)) {
            reversedList.add(slow.getItem());
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        reversedList.add(slow.getItem());

        // 偶数位：slow指针往前走一步
        boolean isEven = fast != getTailNextNode();
        if (isEven) {
            slow = slow.getNext();
        }

        // 对比reversedList和原链表后半部分
        SNode<T> reversed = reversedList.headNode.getNext();
        while (reversed != null) {
            if (!reversed.getItem().equals(slow.getItem())) {
                return false;
            }
            reversed = reversed.getNext();
            slow = slow.getNext();
        }
        return true;
    }

    @Override
    public String toString() {
        String items = "";
        SNode<T> node = headNode.getNext();
        while (node != getTailNextNode()) {
            if (items.length() > 0)
                items += ",";
            items += node.getItem();
            node = node.getNext();
        }
        return items;
    }

}
