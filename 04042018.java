81. Search in Rotated Sorted Array II
class Solution {
    public boolean search(int[] nums, int target) {
        boolean res = false;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                res = true;
            }
        }
        return res;
    }
    // We are using for loop here is b/c there will be a situation that [00000000...100000] and we trying to look for 1 in all other 0s.
    // In this scenario, we cannot use binary search, so we jsut use for loop b/c in binary search will be O(n) as well
}

153. Find Minimum in Rotated Sorted Array
class Solution {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length-1;
        int target = nums[end];
        //set target to be last element and find the first element <= target
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid] < target){
                end = mid;
            }
            //rotate here
            else{
                start = mid;
            }
        }
        if(nums[start] <= target){
            return nums[start];
        }else{
            return nums[end];
        }
    }
}
