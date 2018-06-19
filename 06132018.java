198. House Robber
//State: dp[i] means [0,i-1] maxValue
//Formula:  dp[i] =  max(dp[i-1],dp[i-2]+nums[i-1])
//Init: dp[0] = 0; dp[1] = nums[0]
//Ans: dp[n]
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2; i <= len; i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }
        return dp[len];
    }
}

10. Regular Expression Matching
//State: dp[i][j] means [0,i-1][0,j-1] match?
//Formula: dp[i][j] = 
// 1, If p.charAt(j-1) == s.charAt(i-1) :  dp[i][j] = dp[i-1][j-1];
// 2, If p.charAt(j-1) == '.' : dp[i][j] = dp[i-1][j-1];
// 3, If p.charAt(j-1) == '*': 
//    here are two sub conditions:
//                1   if p.charAt(j-2) != s.charAt(i-1) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
//                2   if p.charAt(j-2) == s.charAt(i-1) or p.charAt(j-2) == '.':
//                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
//                            or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
//                            or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
//Init: dp[i][0] = false; dp[0][j] = dp[0][j-2] && p.charAt(j-1) == '*' // in this case, * repeat 0 time
//Ans: dp[len1][len2]
class Solution {
    public boolean isMatch(String s, String p) {
        if(s == null || p == null) return false;
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS+1][lenP+1];
        dp[0][0] = true;
        for(int j = 1; j <= lenP; j++){
            if(p.charAt(j-1) == '*' && dp[0][j-2]){
                dp[0][j]=true;
            }
        }
        for(int i = 1; i <= lenS; i++){
            for(int j = 1; j <= lenP; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)=='.'){
                    dp[i][j] = dp[i-1][j-1];
                }
                if(p.charAt(j-1) == '*'){
                        if(s.charAt(i-1)!=p.charAt(j-2) && p.charAt(j-2)!='.'){
                            dp[i][j] = dp[i][j-2]; //* serves as 0
                        }else{
                            dp[i][j] = dp[i-1][j] || dp[i][j-1] || dp[i][j-2];
                                //      multiple     1           0
                        }
                }
                    
                
            }
        }
        return dp[lenS][lenP];
    }
}