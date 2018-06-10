300. Longest Increasing Subsequence
class Solution {
    //o(n^2)
    public int lengthOfLIS(int[] nums) {
        //dp[i] = max(1,dp[j]+1);(j = 0->i, nums[j]<nums[i])
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
        }
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <i; j++){
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    //O(NlgN)
    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int res = 1;
        //dp represent the smallest element in nums which can consist the len of increasing subsequence
        //so we find the first element in dp > x and replace with it
        // or if dp[res] < x, we append
        int[] dp = new int[nums.length+1];
        dp[1] = nums[0];
        for(int i = 1; i < nums.length; i++){
            int index = binarySearch(dp,nums[i],1,res);
            dp[index] = nums[i];
            if(index > res){
                res++;
            }
        }
        return res;
    }

    //find the first element great than target in dp
    private int binarySearch(int[] nums, int target, int start, int end){
        if(nums[end]<target) return end+1;
        while(start+1 < end){
            int mid = start+(end-start)/2;
            if(nums[mid]>=target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(nums[start]>=target) return start;
        return end;
    } 
}

