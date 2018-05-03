//Heap
264. Ugly Number II
class Solution {
    public int nthUglyNumber(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        int p2 = 1, p3 = 1, p5 = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = Math.min(2*dp[p2],Math.min(3*dp[p3],5*dp[p5]));
            if(dp[i] == 2*dp[p2]) p2++;
            if(dp[i] == 3*dp[p3]) p3++;
            if(dp[i] == 5*dp[p5]) p5++;
        }
        return dp[n];
    }
}

295. Find Median from Data Stream
class MedianFinder {
    PriorityQueue<Integer> min = new PriorityQueue();
    PriorityQueue<Integer> max = new PriorityQueue(1000,Collections.reverseOrder());
    /** initialize your data structure here. */
    // public MedianFinder() {
        
    // }
    boolean even = true;
    public void addNum(int num) {
        // max.offer(num);
        // min.offer(max.poll());
        // if(max.size()<min.size()){
        //     max.offer(min.poll());
        // }
        if(even){
            max.offer(num);
            min.offer(max.poll());
        }else{
            min.offer(num);
            max.offer(min.poll());
        }
        even = !even;
    }
    
    public double findMedian() {
        // if(max.size() == min.size()) return (max.peek()+min.peek())/2.0;
        // else return max.peek();
        if(even) return (max.peek()+min.peek())/2.0;
        else return min.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

