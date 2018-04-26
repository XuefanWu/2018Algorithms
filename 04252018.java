Bit Manipulation
190. Reverse Bits
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if(n == 0) return 0;
        int res = 0;
        for(int i = 0; i < 32; i++){
            res <<= 1;
            if((n&1) == 1) res += 1;
            n >>= 1;
        }
        return res;
    }
        // public int reverseBits(int n) {
        // if(n == 0) return 0;
        // int res = 0;
        // int mask = 1 <<< 31; // in java, we don't have unsigned_int, so this will be -...
        // for(int i = 0; i < 32; i++){
        //     if((n&1)==1) res |= mask;
        //     n >>= 1;
        //     mask >>= 1;
        // }
        // return res;
    //}
}

169. Majority Element
class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0], count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                count ++;
                major = nums[i];
            }else if(major == num[i]){
                count ++;
            }else count--;
        }
        return major;
    }
}

187. Repeated DNA Sequences
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> res = new ArrayList<>();
        char[] map = new char[26];
        map['C'-'A'] = 1;//01
        map['G'-'A'] = 2;//10
        map['T'-'A'] = 3;//11

        for(int i = 0; i < s.length()-9; i++){
            int val = 0;
            for(int j = i; j < i+10; j++){
                val <<= 2;
                val |= map[s.charAt(j)-'A'];
            }
            if(!words.add(val) && doubleWords.add(val)){
                res.add(s.substring(i,i+10));
            }
        }
        return res;
    }
}

201. Bitwise AND of Numbers Range
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int move = 0;
        while(m!=n){
            m >>= 1;
            n >>= 1;
            move++;
        }
        return m << move;
    }
}

461. Hamming Distance
class Solution {
    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int res = 0;
        while(diff!=0){
            diff = diff & (diff-1);
            res++;
        }
        return res;
    }
}

389. Find the Difference
class Solution {
    public char findTheDifference(String s, String t) {
        int len = t.length();
        char res = t.charAt(len-1);
        for(int i = 0; i < len-1; i++){
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }
}