package com.hannah.study.data;

import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 跳表
 * @see ConcurrentSkipListMap
 */
public class SkipList<T> {

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1; // 层高
    private Random rd = new Random();

    private Node<T> headNode; // 头节点【哨兵】
    private int length; // 长度

    public SkipList() {
        headNode = new Node<>(MAX_LEVEL);
    }

    public Node<T> getHeadNode() {
        return headNode;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public int size() {
        return length;
    }

    /**
     * 是否尾节点
     * @param node
     * @return
     */
    protected boolean isTailNode(Node<T> node) {
        return node.getNexts() == null;
    }

    /**
     * 查找节点
     * @param item
     * @return
     */
    public Node<T> find(T item) {
        Node<T> p = headNode;
        // 从最大层开始一层层往下找
        for (int i = levelCount - 1; i >= 0; --i) {
            // 当前item>下一节点item，继续找
            while (p.nexts[i] != null && compare(item, (T) p.nexts[i].getItem()) > 0) {
                p = p.nexts[i];
            }
        }

        if (p.nexts[0] != null && compare(item, (T) p.nexts[0].getItem()) == 0) {
            return p.nexts[0];
        } else {
            return null;
        }
    }

    /**
     * 插入节点
     * @param item
     * @return
     */
    public boolean add(T item) {
        int level = size() == 0 ? 1 : randomLevel();
        // 每次只增加一层，如果条件满足
        if (level > levelCount) {
            level = ++levelCount;
        }

        Node<T> newNode = new Node<>(level, item);
        Node<T> p = headNode; // 从head节点开始
        // 从最大层开始一层层往下找
        for (int i = levelCount - 1; i >= 0; --i) {
            // 当前item>下一节点item，继续找
            while (p.nexts[i] != null && compare(item, (T) p.nexts[i].getItem()) > 0) {
                p = p.nexts[i];
            }
            // levelCount 会 > level，所以加上判断
            if (level > i) {
                if (p.nexts[i] == null) {
                    p.nexts[i] = newNode;
                } else {
                    Node next = p.nexts[i];
                    p.nexts[i] = newNode;
                    newNode.nexts[i] = next;
                }
            }
        }

        length++;
        return true;
    }

    private int compare(T item1, T item2) {
        Comparable comp1 = (Comparable) item1;
        return comp1.compareTo(item2);
    }

    /**
     * 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
     *     50%的概率返回 1
     *     25%的概率返回 2
     *   12.5%的概率返回 3 ...
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (rd.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    /**
     * 打印所有数据
     */
    public void printAll() {
        Node p = headNode;
        Node[] c = p.nexts;
        Node[] d = c;
        int maxLevel = c.length;
        for (int i = maxLevel - 1; i >= 0; i--) {
            do {
                System.out.print((d[i] != null ? d[i].getItem() : "N") + ":" + i + "---");
            } while (d[i] != null && (d = d[i].nexts)[i] != null);
            System.out.println();
            d = c;
        }
    }

    /**
     * 跳表的节点，记录了当前节点数据和下一个节点所有层的数据
     * @param <T>
     */
    public class Node<T> extends SNode<T> {

        /**
         * 表示当前节点位置的下一个节点所有层的数据，数组下标即为层数level
         */
        private Node<T>[] nexts;

        public Node(int level) {
            this.nexts = new Node[level];
        }

        public Node(int level, T item) {
            this.nexts = new Node[level];
            super.setItem(item);
        }

        public Node<T>[] getNexts() {
            return nexts;
        }

        public void setNexts(Node<T>[] nexts) {
            this.nexts = nexts;
        }
    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(2);
        list.add(9);
        list.printAll();
        System.out.println(list.find(5));
        System.out.println(list.find(4));
    }
}
