279. Perfect Squares
//State: dp[i] means min squares to get i
//Formula: dp[i] = min(dp[i-j*j])+1  i-j*j >= 0
//Init: dp[0] = 0, dp[1] = 1
//Ans: dp[n]
class Solution {
    public int numSquares(int n) {
        if(n == 0) return 0;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            int temp = Integer.MAX_VALUE;
            for(int j = 1; j*j<=i; j++){
                temp = Math.min(temp,dp[i-j*j]);
            }
            dp[i] = temp+1;
        }
    }
    return dp[n];
}

312. Burst Balloons
//State: dp[i][j] means max in [i,j]
//Formula: dp[i][j] = max(dp[i][k-1]+dp[k+1][j]+nums[k]*nums[i-1]*nums[j+1])  i<=k<=j
//we take as the last burst item in [i,j] so we need to add left range and right range and k multiply with its adjacency
//Init: insert 1 at end and start of nums
//Ans: dp[1][len]
class Solution {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] newNums = new int[len+2];
        newNums[0] = newNums[len+1] = 1;
        for(int i = 1; i <= len; i++){
            newNums[i] = nums[i-1];
        }
        int[][] dp = new int[len+2][len+2];
        for(int length = 1; length <= len; length++){
            for(int left = 1; left <= len-length+1; left++){
                int temp = -1;
                int right = left+length-1;
                for(int k = left; k <= right; k++){
                    temp = Math.max(temp,dp[left][k-1]+dp[k+1][right]+newNums[k]*newNums[left-1]*newNums[right+1]);
                }
                dp[left][right] = temp;
            }
        }
        return dp[1][len];
    }
}
121. Best Time to Buy and Sell Stock - can only complete one transaction

122. Best Time to Buy and Sell Stock II - can complete as many transactions as you like - Greedy
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }
}


123. Best Time to Buy and Sell Stock III - can complete at most 2 transactions
//把数组分成两段，分别求最大值
//最后结果就是max(dp1[j]+dp2[j])
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int[] dp1 = new int[len];
        int[] dp2 = new int[len];
        int res = 0;
        for(int i = 1, valley = prices[0]; i < len; i++){
            valley = Math.min(prices[i],valley);
            dp1[i] = Math.max(dp1[i-1],prices[i]-valley);
        }
        for(int i = len-2, peak = prices[len-1]; i>= 0; i--){
            peak = Math.max(peak,prices[i]);
            dp2[i] = Math.max(dp2[i+1],peak-prices[i]);
        }
        for(int i = 0; i<len; i++){
            res = Math.max(dp1[i]+dp2[i],res);
        }
        return res;
    }
}


303. Range Sum Query - Immutable
class NumArray {
    private int[] sum;
    public NumArray(int[] nums) {
        int len = nums.length;
        sum = new int[len+1];
        for(int i = 1; i <= len; i++){
            sum[i] = sum[i-1]+nums[i-1];
        }

    }
    
    public int sumRange(int i, int j) {
        if(i == 0) return sum[j+1];
        else return sum[j+1]-sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */