628. Maximum Product of Three Numbers
class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for(int n : nums){
            if(n > max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }else if(n > max2){
                max3 = max2;
                max2 = n;
            }else if(n > max3){
                max3 = n;
            }

            if(n < min1){
                min2 = min1;
                min1 = n;
            }else if(n < min2){
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }
}

643. Maximum Average Subarray I
//window size k
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        long max = sum;
        for(int i = k; i < nums.length; i++){
            sum += nums[i]-nums[i-k];
            max = Math.max(max,sum);
        }
        return max/1.0/k;
    }
}

644. Maximum Average Subarray II
//window size >= k
https://leetcode.com/problems/maximum-average-subarray-ii/discuss/105480/Java-solution-O(nlogM)-Binary-search-the-answer?page=1
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double left = Integer.MIN_VALUE, right = Integer.MAX_VALUE;
        while(right - left > 0.000004){
            double mid = (left+right)/2;
            if(check(nums,k,mid)){
                left = mid;
            }else{
                right = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int k, double x){
        int len = nums.length;
        double[] a = new double[len];
        for(int i = 0; i < len; i++) a[i] = nums[i]-x;
        double now = 0, last = 0;
        for(int i = 0; i < k; i++) now += a[i];
        if(now >= 0) return true;
        for(int i = k; i < len; i++){
            now += a[i];
            last += a[i-k];
            if(last < 0){
                now-=last;
                last = 0;
            }
            if(now>= 0) return true;
        }
        return false;
    }
}

