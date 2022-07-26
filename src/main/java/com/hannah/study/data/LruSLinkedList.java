package com.hannah.study.data;

import java.util.Scanner;

/**
 * 基于单链表的LRU算法
 * 时间复杂度 O(n)
 */
public class LruSLinkedList<T> extends SLinkedList<T> {

    public LruSLinkedList(int capacity) {
        super(capacity);
    }

    /**
     * 增加元素
     * @param item
     * @return
     */
    @Override
    public boolean add(T item) {
        // 删除原先的节点，再插入链表头部
        super.remove(item);
        return super.add(item);
    }

    public static void main(String[] args) {
        SLinkedList<Integer> list = new LruSLinkedList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("链表：" + list);
        System.out.println("中间：" + list.findMiddleNode().getItem());

        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            System.out.println(list);
            if (list.isPalindrome()) {
                System.out.println("链表出现回文结构！");
                break;
            }
        }
    }

}
