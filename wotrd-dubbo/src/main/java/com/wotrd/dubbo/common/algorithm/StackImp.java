package com.wotrd.dubbo.common.algorithm;
/**
 * 数据结构栈的实现
 */
public class StackImp<Item> {
    private Node first;     //栈顶
    private int size;
    private class Node{
        private Item item;
        private Node next;
    }
    public void push(Item item){
        Node oldFirst=first;
        first=new Node();
        first.item=item;
        first.next=oldFirst;
        size++;
    }
    public Item pop(){
        Item item=first.item;
        first=first.next;
        size--;
        return item;
    }
    public boolean isEmpty() {
         return null==first;
    }
    public int size() {
        return size;
    }
}
