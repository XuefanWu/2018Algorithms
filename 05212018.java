//Array
560. Subarray Sum Equals K
class Solution {
    //return number of continuous subarray sum == k
    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        //sum(i,j) = sum(0,j)-sum(0,i-1) = k
        Map<Integer,Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(preSum.containsKey(sum-k)){
                res+=preSum.get(sum-k);
            }
            preSum.put(sum,preSum.getOrDefault(sum,0)+1);
            
        }
        return res;
    }
}

561. Array Partition I
https://leetcode.com/problems/array-partition-i/discuss/102170/Java-Solution-Sorting.-And-rough-proof-of-algorithm.
public class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
            result += nums[i];
        }
        return result;
    }
}