137. Single Number II
class Solution {
    // public int singleNumber(int[] nums) {
    //     int res = 0;
    //     for(int i = 0; i < 32; i++){
    //         int sum = 0;
    //         for(int j = 0; j < nums.length; j++){
    //             sum += (nums[j]>>i) & 1;
    //         }
    //         res |= (sum%3) << i;
    //     }
    //     return res;
    // }
    
    public int singleNumber(int[] nums){
        int one = 0, two = 0, three = 0;
        for(int i : nums){
            two |= (one & i);
            one ^= i;
            three = one & two;
            one &= ~three;
            two &= ~three;
        }
        return one;
    }
}

260. Single Number III
class Solution {
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int i : nums){
            diff ^= i;
        }
        diff &= -diff;
        //正数补码不变，负数补码=正数反码+1
        int[] res = {0,0};
        for(int i : nums){
            if((i & diff) == 0){
                res[0] ^= i;
            }else{
                res[1] ^= i;
            }
        }
        return res;
    }
}
https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C++Java-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations

318. Maximum Product of Word Lengths
class Solution {
    public int maxProduct(String[] words) {
        int[] len = new int[words.length];
        int[] masks = new int[words.length];
        for(int i = 0; i < words.length; i++){
            len[i] = words[i].length();
        }
        int res = 0;
        for(int i = 0; i < words.length; i++){
            for(char c : words[i].toCharArray()){
                masks[i] |= 1 << (c-'a');
            }
            for(int j = 0; j < i; j++){
                if((masks[i] & masks[j]) == 0){
                    res = Math.max(res, len[i]*len[j]);
                }
            }
        }
        return res;
    }
}

338. Counting Bits
//dp[i] = dp[i/2]+i%2;
class Solution {
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        for(int i = 1; i <= num; i++){
            dp[i] = dp[(i >> 1)]+(i&1);
        } 
        return dp;
    }
}

397. Integer Replacement
class Solution {
    public int integerReplacement(int n) {
        if(n == Integer.MAX_VALUE) return 32;
        int res = 0;
        while(n>1){
            if(n %2 == 0) n = n/2;
            else{
                if((n+1)%4==0 && n-1!=2) n++;
                else n--;
            }
            res++;
        }
        return res;
    }
}

405. Convert a Number to 

class Solution {
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public String toHex(int num) {
        if(num == 0) return "0";
        String res = "";
        while(num!=0){
            res = map[(num&15)]+res;
            num = num >>> 4;
        }
        return res;

    }
}
