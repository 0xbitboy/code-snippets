package com.github.liaojiacan.heap;


import java.util.Arrays;

/**
 * 二叉堆
 * 1. 最大堆：当父节点的键值总是大于或等于任何一个子节点的键值。
 * 2.最小堆：当父节点的键值总是小于或等于任何一个子节点的键值
 *
    Parent(i) = floor(i/2)，i 的父节点下标
    Left(i) = 2i，i 的左子节点下标
    Right(i) = 2i + 1，i 的右子节点下标
 * Created by liaojiacan on 2017/5/17.
 */
public    class BigHeap {

    private final static int DEFAULT_CAPACTIY=100;

    private int capacity;

    private int size;

    private Comparable[] heap;

    public BigHeap() {
        this(DEFAULT_CAPACTIY);
    }

    public BigHeap(int capacity) {
        this.capacity = capacity;
        heap = new Comparable[capacity+1];
    }

    public BigHeap(Comparable[] a) {
        this(a.length);
        for(Comparable c: a){
            insert(c);
        }
    }
    /**
     * 入队
     * @param c
     */
    public void insert(Comparable c){
        if(size == 0){
            heap[1]=c;
        }else{
            heap[size+1]=c;
            fixUp(size);
        }
        size++;
    }

    /**
     * 出队，弹出ROOT
     * @return
     */
    public  Comparable deleteTop(){
        Comparable top = heap[1];
        swap(1,size);
        fixDown(1,--size);
        return top;
    }

    /**
     * 自底向上堆化
     * @param k
     */
    protected void fixUp(int k){
        while (k>1){
            int j= k/2;
            if(less(j,k)){
                swap(j,k);
                k=j;
            }else {
                break;
            }
        }
    }

    /**
     * 从上到下堆化
     * @param k 1
     *
     * @param n
     */
    protected void fixDown(int k,int n){
        int j ;
        while (2*k<=n){
            j=k*2;
            if (j<n && less(j,j+1)){
                j++;
            }
            if(less(j,k)) break;
            swap(k,j);
            k=j;
        }

    }


    private boolean less(int a,int b){
        return heap[a].compareTo(heap[b])<0;
    }

    private void swap(int j, int k) {
        Comparable tmp = heap[j];
        heap[j]=heap[k];
        heap[k] = tmp;
    }

    public boolean isEmpty(){
        return  size==0;
    }

    public static Comparable[]  heapSort(Comparable[] a){
        BigHeap bh = new BigHeap(a);
        System.out.println(Arrays.toString(bh.heap));
        while (!bh.isEmpty()){
            bh.deleteTop();
        }
        return bh.heap;
    }

    public static void main(String[] args) {
        //heap sort
        Comparable[] a = new Integer[]{100,12,3,55,3,2212,22,98,2,0};
        a =BigHeap.heapSort(a);
        System.out.println(Arrays.toString(a));
    }

}
