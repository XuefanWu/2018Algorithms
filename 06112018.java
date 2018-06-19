//DP
5. Longest Palindromic Substring
//update version from 0607
//State: dp[i][j] means [i,j] is palindrome
//Init: start = 0, maxLength = 1, dp[i][i] = true;
//Fomular: dp[i][j] is true if(c[i]==c[j] && dp[i+1][j-1]) 
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return "";
        int len = s.length();
        boolean[][] pal = new boolean[len][len];
        int maxLength = 1, start = 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < len; i++){
            pal[i][i] = true;
            for(int j = 0; j < i; j++){
                if(c[i] == c[j] && (j+1>i-1 || pal[j+1][i-1])){
                    pal[j][i] = true;
                }
                if(pal[j][i] && maxLength < (i-j+1)){
                    maxLength = i-j+1;
                    start = j;
                }
            }
        }
        return s.substring(start, start+maxLength);
    }
}


139. Word Break
//序列型
//state: dp[i] means 前i个字符 can be perfectly break
//formula: dp[i] = OR dp[j] && [j,i) is a word (0<j<i)
//init: dp[0] = true
//answer: dp[n]
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || wordDict == null || s.length() == 0 || wordDict.size() == 0) return false;
        int sLen = s.length();
        boolean[] dp = new boolean[sLen+1];
        dp[0] = true;
        Set<String> dict = new HashSet<>();
        for(String str : wordDict){
            dict.add(str);
        }
        // for(int i = 1; i <= sLen; i++){
        //     for(int j = 0; j < i; j ++){
        //         if(dp[j] && dict.contains(s.substring(j,i))){
        //             dp[i] = true;
        //             break;
        //         }
        //     }
        // }
        for(int i = 1; i <= sLen; i++){
            //this will change O(N) to O(l) which l is the longest word length in dict
            for(int lastWordLength = 1; lastWordLength <= i && lastWordLength <= maxLength; lastWordLength ++){
                if(!dp[i-lastWordLength]) continue;
                if(dict.contains(s.substring(i-lastWordLength,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[sLen];
    }
}


140. Word Break II
//Memorization + DFS
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return helper(s,wordDict,new HashMap<String,List<String>>());
    }

    private List<String> helper(String s, List<String> wordDict, Map<String,List<String>> map){
        if(map.containsKey(s)) return map.get(s);
        List<String> res = new ArrayList<>();

        if(s.length() == 0){
            res.add("");
            return res;
        }

        for(String word : wordDict){
            if(s.startsWith(word)){
                List<String> subList = helper(s.substring(word.length()),wordDict,map);
                for(String sub : subList){
                    res.add(word+(sub.isEmpty()?"":" ")+sub);
                }
            }
        }
        map.put(s,res);
        return res;
    }
}

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

//-------------------------------------
//dp[i] means [0,i-1] min cut
//初始化 dp[i] = i-1 dp[0] = 0;
//dp[i] = min(dp[j]+1) while(0 <= j < i && [j,i-1] is palindrome)
//result: dp[len]
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        char[] c = s.toCharArray();
        int[] dp = new int[len+1];
        boolean[][] pal = new boolean[len][len];
        dp[0] = 0;
        for(int i = 1; i <= len; i++){
            int res = i-1;
            for(int j = 0; j < i; j++){                
               if(c[i-1] == c[j] && ((j+1 > i-2) || pal[j+1][i-2])){
                   pal[j][i-1] = true;
                   res = j == 0? 0 : Math.min(res,dp[j]+1);
               }             
            }
            dp[i] = res;           
        }
        return dp[len];
    }
}

//---------------------
//State: dp[i] means [i,n) min cut
//Fomular: dp[i] = min(dp[j+1]+1) (i<=j <len && [i,j] is palindrome)
//Init: dp[i] = len-i-1
//Answer: dp[0] 
class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        char[] c = s.toCharArray();
        int[] dp = new int[len+1];
        boolean[][] pal = new boolean[len][len];
        for(int i = 0; i <= len ; i++){
            dp[i] = len-i-1;
        }
        for(int i = len-1; i >= 0; i--){
            for(int j = i; j < len; j++){           
               if(c[i] == c[j] && ((i+1 > j-1) || pal[i+1][j-1])){
                   pal[i][j] = true;
                   dp[i] =  Math.min(dp[i],dp[j+1]+1);
               }              
            }          
        }
        return dp[0];
    }
}

