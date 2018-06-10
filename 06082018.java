//Dp

132. Palindrome Partitioning II
//state: dp[i] means min cut to get all palindromes in substring(0,i)
//fomula: dp[i] = min(dp[j]+1) which  0 <j < i && [j,i] is palindrome
//for check [j+1,i] is palindrome, we can call the function isPalindrome to check or we can use a 2d matrix to store the state of each substring
//init: dp[i] = i 0->len-1
//result: dpp[len-1]
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        char[] c = s.toCharArray();
        int[] dp = new int[len];
        boolean[][] pal = new boolean[len][len];
        for(int i = 0; i < len; i++){
            int res = i;
            for(int j = 0; j <= i; j++){
                
               if(c[i] == c[j] && ((j+1 > i-1) || pal[j+1][i-1])){
                   pal[j][i] = true;
                   res = j == 0? 0 : Math.min(res,dp[j-1]+1);
               }
            }
            dp[i] = res;
        }
        return dp[len-1];
    }
}