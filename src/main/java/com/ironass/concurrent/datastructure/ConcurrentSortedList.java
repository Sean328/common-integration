package com.ironass.concurrent.datastructure;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发环境下 线程安全的 支持链表插入后并有序排列的List
 * 大概原理是 支持并发插入，只锁next 下一个node
 * @author lixin
 * @date 2019-01-31 11:48
 **/
public class ConcurrentSortedList {

    private final Node head;
    private final Node tail;

    public ConcurrentSortedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void insert(int value) {
        Node current = head;
        current.lock.lock();
        Node next = current.next;
        try {

            while (true) {
                next.lock.lock();
                try {
                    if (next == tail || next.value < value) {
                        Node node = new Node(value, current, next);
                        next.prev = node;
                        current.next = node;
                        return;
                    }
                } finally {
                    current.lock.unlock();
                }

                current = next;
                next = current.next;
            }

        } catch (Exception e) {

        } finally {
            next.lock.unlock();
        }
    }

    public int size() {
        Node current = tail;
        int count = 0;
        while (current.prev != head) {
            ReentrantLock lock = current.lock;
            lock.lock();

            try {
                ++count;
                current = current.prev;
            } finally {

                lock.unlock();

            }

        }
        return count;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

}
