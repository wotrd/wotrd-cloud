package com.wotrd.dubbo.common.algorithm.search;

/**
 * 二叉查找树取决于树的形状 . HashMap 的实现使用红黑树，
 * 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 * 任意节点的左、右子树也分别为二叉查找树；
 * 没有键值相等的节点。
 */
public class BinarySearchTree <T extends Comparable<T>>{
    //私有变量 根节点root
    private Node<T> root;
    public void test(){
        BinarySearchTree<Integer> bst=new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(7);
        bst.insert(3);
        bst.insert(1);
        bst.insert(9);
        bst.insert(6);
        bst.insert(4);
        System.out.println("最小值:"+bst.findMin());
        System.out.println("最大值:"+bst.findMax());
        System.out.println("查找元素9是否存在:"+bst.contains(9));
        System.out.println("查找元素8是否存在:"+bst.contains(8));
        System.out.println("遍历二叉树");
        bst.printTree();
    }
    private static class Node<T>{
        private T data;
        private Node<T> left;
        private Node<T> right;
        public Node(T data){
            this(data,null,null);
        }
        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    //无参构造函数，根节点为null
    public BinarySearchTree(){
        root=null;
    }
    //清空二叉查找树
    public void makeEmpty(){
        root=null;
    }
    //判断树是否为空
    public boolean isEmpty(){
        return root==null;
    }
    //查找集合中是否有元素vlaue，有返回true
    public boolean contains(T value){
        return contains(value,root);
    }
    private boolean contains(T value,Node<T> t){
        if(t==null){
            return false;
        }
        int result=value.compareTo(t.data);
        if(result<0){
            return contains(value,t.left);
        }else if(result>0){
            return contains(value,t.right);
        }else{
            return true;
        }
    }
    //查找集合中的最小值
    public T findMin(){
        return findMin(root).data;
    }
    private Node<T> findMin(Node<T> node){
        if (null==node) return null;
        if (null==node.left){
            return node;
        }else {
            return findMin(node.left);
        }
    }
    //查找集合中的最大值
    public T findMax(){
        return findMax(root).data;
    }
    private Node<T> findMax(Node<T> t){
        if(t!=null) {
            while(t.right!=null){
                t=t.right;
            }
        }
        return t;
    }
    //插入元素
    private void insert(T data){
        root=insert(data,root);
    }
    private Node<T> insert(T value, Node<T> t) {
        if(t==null){
            return new Node(value,null,null);
        }
        int result=value.compareTo(t.data);
        if(result<0){
            t.left=insert(value,t.left);
        }else if(result>0){
            t.right=insert(value,t.right);
        }
        return t;
    }
    //删除节点
    private Node<T> remove(T value){
        return remove(value,root);
    }
    private Node<T> remove(T value,Node<T> node){
        if (null==root) return null;
        int result = node.data.compareTo(value);
        if (result<0){
            node.left=remove(value,node.left);
        }else if (result>0){
            node.right=remove(value,node.right);
        }else if (node.left!=null && node.right!=null){//左右节点都有
            //1.当前节点值被其右子树的最小值代替
            node.data=findMin(node.right).data;
            //2删除右节点中最小的节点
            node.right=remove(node.data,node.right);
        }else {
            node=(node.left!=null)?node.left:node.right;
        }
        return node;
    }
    //中序遍历打印
    public void printTree(){
        printTree(root);
    }
    public void printTree(Node <T> node){
        if (node!=null){
            printTree(node.left);
            System.out.println(node.data);
            printTree(node.right);
        }
    }
}
