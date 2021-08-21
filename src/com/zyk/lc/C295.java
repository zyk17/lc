package com.zyk.lc;

import java.util.PriorityQueue;

public class C295 {

    static class MedianFinder {

        // 把这个两个堆的大小维护在数量差值为1。这样就可以直接计算中位数
        // 1。两个堆大小一样，较小一半的最大，较大一半的最小
        // 2。两个堆大小不一样，数量多的那个堆顶。

        // 存放较小数字的大根队
        PriorityQueue<Integer> bigHeap;
        // 存放较大数字的小根队
        PriorityQueue<Integer> smallHeap;

        public MedianFinder() {
            bigHeap = new PriorityQueue<>( (a, b) -> b - a);
            smallHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(bigHeap.isEmpty()) {
                bigHeap.add(num);
                return;
            }
            if( num > bigHeap.peek() ) {
                smallHeap.add(num);
            }else {
                bigHeap.add(num);
            }
            // 将两个堆的大小维护在差值为1以内
            if( Math.abs(bigHeap.size() - smallHeap.size()) > 1 ) {
                if(bigHeap.size() > smallHeap.size()) {
                    smallHeap.add(bigHeap.poll());
                }else {
                    bigHeap.add(smallHeap.poll());
                }
            }
        }

        // 1。两个堆大小一样，较小一半的最大，较大一半的最小
        // 2。两个堆大小不一样，数量多的那个堆顶。
        public double findMedian() {
            if(bigHeap.size() == 0)
                return 0;
            return bigHeap.size() == smallHeap.size() ?
                    (double)(bigHeap.peek() + smallHeap.peek()) / 2 :
                    bigHeap.size() > smallHeap.size() ? bigHeap.peek() : smallHeap.peek();
        }
    }

    public static void main(String[] args) {

    }

}
