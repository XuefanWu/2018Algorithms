718. Maximum Length of Repeated Subarray
class Solution {
    //State: dp[i][j] means that ending with [i-1]and[j-1] subarray length
    //Fomular: dp[i][j] = dp[i-1][j-1] +1 if A[i-1] == B[j-1]
    //Init: first col = 0, first row = 0
    //Ans: keep a global max to track
    public int findLength(int[] A, int[] B) {
        if(A == null || B == null || A.length == 0 || B.length == 0) return 0;
        int lenA = A.length;
        int lenB = B.length;
        int max = 0;
        int[][] dp = new int[lenA+1][lenB+1];
        for(int i = 1; i <= lenA; i++){
            for(int j = 1; j <= lenB; j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }
}

698. Partition to K Equal Sum Subsets
class Solution {
    //input is a set, sequence does not matter, so not use dp
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0  || k == 0){
            return false;
        }
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        boolean[] visited = new boolean[nums.length];
        return helper(nums, visited,k,sum/k,0,0,0);
    }
    
    private boolean helper(int[] nums, boolean[] visited, int k, int target, int startIndex, int sum, int countNum){
        if(k == 0) return true;
        if(sum == target && countNum > 0) return helper(nums,visited,k-1,target,0,0,0);
        for(int i = startIndex; i < nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                if(helper(nums,visited,k,target,i+1,sum+nums[i],countNum+1)) return true;
                visited[i] = false;
            }
        }
        return false;
    } 
}


72. Edit Distance
//State: dp[i][j] means [0,i-1] [0,j-1] need min steps
//Formular: dp[i][j] = dp[i-1][j-1]                                  if word1[i-1]==word2[j-1]
//                   = min(dp[i-1][j-1],dp[i-1][j], dp[i][j-1])+1    if word1[i-1]!= word2[j-1]
// 如果将c替换成d，则f[i][j]=f[i-1][j-1]+1；
// 如果在c后面添加一个d，则f[i][j]=f[i][j-1]+1；
// 如果将c删除，则f[i][j]=f[i-1][j]+1
//Init: dp[i][0] = i, dp[0][j] = j
//Ans: dp[len1][len2]
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null ) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 1; i <= len1; i++){
            dp[i][0] = i;
        }
        for(int j = 1; j <= len2; j++){
            dp[0][j] = j;
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j<= len2; j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1]; 
                else{
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
                
            }
        } 
        return dp[len1][len2];
    }
}

//Space O(n) 滚动数组
// (i-1,j-1) (i-1,j)           pre       dp[j] <-- this value will be update later, so we need a temp to store it
// (i,j-1)   (i,j)     ---->   dp[j-1]   dp[j]

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null ) return 0;
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2+1];
        for(int j = 1; j <= len2; j++){
            dp[j] = j;
        }
        for(int i = 1; i <= len1; i++){
            int pre = dp[0];
            dp[0] = i; // 相当于之前初始化第一列 
            for(int j = 1; j<= len2; j++){
                //存下要更新之前的值
                int temp = dp[j];
                if(word1.charAt(i-1)==word2.charAt(j-1)) dp[j] = pre; 
                else{
                    dp[j] = Math.min(pre,Math.min(dp[j-1],dp[j]))+1;
                }
                //update
                pre = temp;
            }
        } 
        return dp[len2];
    }
}


115. Distinct Subsequences
//State: dp[i][j] means for [0,i-1] [0,j-1] the number of equal string
//Fomular: dp[i][j] = dp[i-1][j]+dp[i-1][j-1] if s[i-1] == t[j-1]
//                  = dp[i-1][j]              if s[i-1] != t[j-1]
//Init: dp[i][0] = 1 we can delete to be equal, so this is 1, dp[0][j] = 0;
//Ans: dp[len1][len2]
class Solution {
    public int numDistinct(String s, String t) {
        if(s==null || t == null) return 0;
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i = 0; i <=len1; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <=len2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-1];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len1][len2];
    }
}

97. Interleaving String
//State: dp[i][j] means wether s1[0,i-1]+s2[0,j-1] can consist s3[0,i+j-1]
//Formular: dp[i][j] = (s3[i+j-1]==s2[j-1] && dp[i][j-1]) || (s3[i+j-1]==s1[i-1] && dp[i-1][j])
//Init: dp[i][0] dp[0][j] compare with s3[0,i-1/j-1] && dp[i-1][0]/dp[0][j-1]
//Ans: dp[len1][len2]
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1 == null || s2 == null || s3 == null) return false;
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if(len1+len2!=len3) return false;
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++){
            if(s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0]) dp[i][0] = true;
        }
        for(int j = 1; j <= len2; j++){
            if(s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1]) dp[0][j] = true;
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[len1][len2];
    }
}


53. Maximum Subarray
// State: dp[i] means maxSum in [0,i-1]
// Formular: dp[i] = dp[i-1]+nums[i] if dp[i-1]>0
//                 = nums[i]         if dp[i-1]<=0
// Init: dp[0] = 0
// Ans: keep a global max
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len+1];
        int res = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++){
            if(dp[i-1]>0) dp[i] = dp[i-1]+nums[i-1];
            else dp[i] = nums[i-1];
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}


121. Best Time to Buy and Sell Stock
//State: dp[i] means [0,i-1] max profit
//Formula: dp[i] = dp[i-1]+nums[i-1]-nums[i-2] < 0? 0:dp[i-1]+nums[i-1]-nums[i-2]
//Init: dp[0] = 0, dp[1] = 0
//Ans: global max
class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int[] dp = new int[len+1];
        if(len == 1) return 0;
        int res = Integer.MIN_VALUE;
        for(int i = 2; i <= len; i++){
            dp[i] = Math.max(0,dp[i-1]+prices[i-1]-prices[i-2]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}