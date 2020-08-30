package com.wotrd.dubbo.common.algorithm.search;



/**
 * 顺序查找，通过链表实现。实现插入，查找和删除功能。
 */
public class SenquentialSearch <Key,Value>{
    private Node first;
    private int size;
    private class Node{
        Node next;
        Key key;
        Value value;
        public Node(Node next,Key key,Value value){
            this.next=next;
            this.key=key;
            this.value=value;
        }
    }
    public void put(Key key,Value value){
        for (Node x=first;x!=null;x=x.next){
            if (x.key.equals(key)){
                x.value=value;
            }else {
                first=new Node(first,key,value);
                size++;
            }
        }
    }
    public Value get(Key key){
        for (Node node=first;node!=null;node=node.next){
            if (node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }
    public boolean delete(Key key){
        if (first.key.equals(key)) {
            first=first.next;
            return true;
        }
        for (Node node=first;node!=null;node=node.next){
            if (null==node.next)return false;
            if (node.next.key.equals(key)){
               node.next=node.next.next;
               size--;
               return true;
            }
        }
        return false;
    }
    public int getSize(){
        return size;
    }
}
