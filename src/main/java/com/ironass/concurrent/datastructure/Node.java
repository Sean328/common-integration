package com.ironass.concurrent.datastructure;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lixin
 * @date 2019-01-31 14:30
 **/
public class Node {
    int value;
    Node prev;
    Node next;
    protected final ReentrantLock lock = new ReentrantLock();

    Node() {
    }

    Node(int value, Node prev, Node next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

}
