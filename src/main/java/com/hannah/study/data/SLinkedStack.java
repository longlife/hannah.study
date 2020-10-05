package com.hannah.study.data;

/**
 * 基于单链表的栈
 * @param <T>
 */
public class SLinkedStack<T> implements IStack<T> {

    private SLinkedList<T> list;

    public SLinkedStack(int capacity) {
        this.list = new SLinkedList<>(capacity);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean push(T item) {
        return list.add(item);
    }

    @Override
    public T pop() {
        return list.removeByPreNode(list.getHeadNode());
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Error: 栈空间已空，无法出栈");
            return null;
        }
        SNode<T> node = list.getHeadNode().getNext();
        return node.getItem();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        IStack<Integer> stack = new SLinkedStack<>(10);
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
