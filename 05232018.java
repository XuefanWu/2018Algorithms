//Array
565. Array Nesting
class Solution {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            int size = 0;
            for(int k = i; nums[k]>=0; size++){
                int next = nums[k];
                nums[k] = -1; //if visited, set to -1
                k = next;
            }
            res = Math.max(res,size);
        }
        return res;
    }
}

581. Shortest Unsorted Continuous Subarray
class Solution {
    //https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/#approach-5-without-using-extra-space-accepted
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < nums[i-1]) flag = true;
            if(flag){
                min = Math.min(min,nums[i]);
            }
        }
        flag = false;
        for(int i = nums.length-2; i >= 0; i--){
            if(nums[i] > nums[i+1]) flag = true;
            if(flag){
                max = Math.max(max,nums[i]);
            }
        }
        int left, right;
        for(left = 0; left < nums.length; left++){
            if(min < nums[left]) break; 
        }
        for(right = nums.length-1; right>=0; right-- ){
            if(max > nums[right]) break;
        }
        return right-left < 0?0: right-left+1;
    }
}

605. Can Place Flowers
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int res = 0;
        for(int i = 0; i < flowerbed.length && res < n; i++){
            if(flowerbed[i] == 0){
                int next = (i == flowerbed.length-1)? 0 : flowerbed[i+1];
                int pre = (i == 0)?0:flowerbed[i-1];
                if(next == 0 && pre == 0){
                    flowerbed[i] = 1;
                    res++;
                }
            }
        }
        return res == n;
    }
}

611. Valid Triangle Number
//return triplets in array which can consist a triangle
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0, len = nums.length;
        for(int i = len-1; i >=2; i--){
            int l = 0, r = i-1;
            while(l < r){
                if(nums[l]+nums[r]> nums[i]){
                    res+=(r-l);
                    r--;
                }
                else l++;
            }
        }
        return res;
    }
}
