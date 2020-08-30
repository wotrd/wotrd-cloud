package com.wotrd.dubbo.common.algorithm;

public class QueueImp<Item> {
    private int size;
    private Node first;
    private Node last;
    private class Node{
        Item item;
        Node next;
    }
    public void enqueue(Item item){
        Node oldLast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if (isEmpty()) first=last;
        oldLast.next=last;
        size++;
    }
    public Item dequeue(){
        Item item=first.item;
        first=first.next;
        if (isEmpty()) last=null;
        size--;
        return item;
    }
    public boolean isEmpty() {
        return first==null;
    }
    public int size() {
        return size;
    }
}
