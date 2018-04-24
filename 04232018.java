677. Map Sum Pairs
class MapSum {

    private TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode temp = root;
        for(char c : key.toCharArray()){
            TrieNode next = temp.children.get(c);
            if(next == null){
                next = new TrieNode();
                temp.children.put(c,next);
            }
            temp = next;
        }
        temp.isWord = true;
        temp.val = val;
    }
    
    public int sum(String prefix) {
        TrieNode temp = root;
        for(char c : prefix.toCharArray()){
            TrieNode next = temp.children.get(c);
            if(next == null) return 0;
            temp = next;
        }
        return dfs(temp);
    }

    class TrieNode{
        Map<Character,TrieNode> children;
        boolean isWord;
        int val;
        public TrieNode(){
            children = new HashMap<Character, TrieNode>();
            isWord = false;
            val = 0;
        }
    }

    public int dfs(TrieNode root){
        int sum = 0;
        for(char c : root.children.keySet()){
            sum += dfs(root.children.get(c));
        }
        return sum + root.val;
    }

   
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */



 //Bit Manipulation
 136. Single Number
 class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
}

// Let’s say we have an array - [2,1,4,5,2,4,1].
// What we are doing is essentially this-

// => 0 ^ 2 ^ 1 ^ 4 ^ 5 ^ 2 ^ 4 ^ 1

// => 0^ 2^2 ^ 1^1 ^ 4^4 ^5 (Rearranging, taking same numbers together)

// => 0 ^ 0 ^ 0 ^ 0 ^ 5

// => 0 ^ 5

// => 5 :)

191. Number of 1 Bits
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0){
            n = n & (n-1); //remove the most right 1
            res++;
        }
        return res;
    }
}

371. Sum of Two Integers
class Solution {
    public int getSum(int a, int b) {
        if(a == 0) return b;
        if(b == 0) return a;
        while(b!=0){
            int carry = a&b;
            a = a^b; //add two numbers without carry
            b = carry << 1; //carry will have 1 on ith bit if a and b have 1 on that bit, then we need to move left 1 bit and add to a 
        }
        return a;
    }
}
class Solution {
    public int getSum(int a, int b) {
        return b==0? a : getSum(a^b,(a&b)<<1);
    }
}

268. Missing Number
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res^nums.length;
    }
}
//Use ^ to remove even exactly same numbers and save the odd, or save the distinct bits and remove the same.
//So we go through index and nums[i] and we will find the missing one


231. Power of Two
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) return false;
        //only 1 bit can be 1 so that it is power of 2
        if((n&(n-1))!=0) return false;
        return true;
    }
}


342. Power of Four
class Solution {
    public boolean isPowerOfFour(int n) {
        return (n > 0) && (n &(n-1)) == 0 && (n & 0x55555555)!=0;
        //F = 0x55555555 = 01010101010101010101010101010101
        //so we check if n only has 1 1bit and it is not on even index
    }
}