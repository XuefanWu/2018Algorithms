//Dp
70. Climbing Stairs
class Solution {
    //f(n) = f(n-1)+f(n-2)
    public int climbStairs(int n) {
        if(n == 1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i < n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
}

55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for(int i = 1; i < len; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && j + nums[j] >= i){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len-1];
    }
}

45. Jump Game II
//dp time exceeded.
class Solution {
    //f[i] represents the min jumps we will take from start point to point i
    //f[i] = min(f[j])+1 (j < i and j can jump to i)
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for(int i = 1; i < len; i++){
            int temp = Integer.MAX_VALUE;
            for(int j = 0; j <i; j++){
                
                if(dp[j]!=Integer.MAX_VALUE && j + nums[j] >= i && dp[j]<temp){
                    temp = dp[j];
                }
            }
            if(temp == Integer.MAX_VALUE){
                dp[i] = Integer.MAX_VALUE;
            }else{
                dp[i] = temp+1;
            }
            
        }
        return dp[len-1];
    }
    
}

5. Longest Palindromic Substring
class Solution {
    //dp[i][j] means [i,j] is palindrome
    //dp[i][j] = dp[i+1][j-1] && i == j
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int len = s.length();
        String res = "";
        int start = 0;
        int maxLength = 1;
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++){
           dp[i][i] = true;
        }
        for(int i = 0; i < len-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                start = i;
                maxLength = 2;
            }
        }

       for(int k = 3; k <= len; k++){
           for(int i = 0; i <= len-k; i++){
               int j = i +k-1;
               if(s.charAt(i) ==  s.charAt(j) && dp[i+1][j-1]){
                   dp[i][j] = true;
                   if(k > maxLength){
                      start = i;
                      maxLength = k;
                   }
               }
               
           }
       }
       return s.substring(start,start+maxLength);
    }
}