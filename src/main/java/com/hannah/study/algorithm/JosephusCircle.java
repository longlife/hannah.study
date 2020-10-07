package com.hannah.study.algorithm;

import com.hannah.study.data.CLinkedList;
import com.hannah.study.data.SNode;

/**
 * 约瑟夫环（约瑟夫斯置换，又称“丢手绢问题”）
 * <pre>
 * N个人围成一圈，第一个人从1开始报数，报M的将被杀掉，下一个人接着从1开始报。
 * 如此反复，最后剩下一个，求最后的胜利者。
 * </pre>
 */
public class JosephusCircle {

    /**
     * 杀
     * @param n N个人（n>0）
     * @param m 报数M的将被杀死
     * @return 最后的胜利者
     */
    public static int kill(int n, int m) {
        if(n == 1) return 1;

        CLinkedList<Integer> list = createLinkedList(n);
        SNode<Integer> headNode = list.getHeadNode();
        SNode<Integer> preNode = headNode;
        while (list.size() > 1) {
            // 报数
            for (int i = 0; i < m-1; i++) {
                preNode = preNode.getNext();
                if (preNode == headNode) { // 头节点不报数
                    preNode = preNode.getNext();
                }
            }
            list.removeByPreNode(preNode);
        }
        return headNode.getNext().getItem();
    }

    /**
     * 创建一个循环单链表
     * @param n
     * @return
     */
    private static CLinkedList<Integer> createLinkedList(int n) {
        CLinkedList<Integer> list = new CLinkedList<>(n);
        // 倒序插入：每次插入头节点
        for (int i = n; i > 0; i--) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(JosephusCircle.kill(10, 5));
    }

}
