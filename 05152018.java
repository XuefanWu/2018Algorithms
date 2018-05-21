//Array
217. Contains Duplicate
class Solution {
    //O(n^2) Space O(1) TLE
    public boolean containsDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    //O(NlgN) Spcae O(1)
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            
            if(nums[i] == nums[i-1]) return true;
            
        }
        return false;
    }

    //O(N) Space O(N)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            if(set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }

}

219. Contains Duplicate II
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}

228. Summary Ranges
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        int lower = nums[0], upper = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i-1]+1) upper = nums[i];
            else{
                res.add(helper(lower, upper));
                lower = upper = nums[i];
            }
        }
        res.add(helper(lower, upper));
        return res;
    }
    public String helper(int i, int j){
        return i == j? i+"" : i+"->"+j;
    }
}

238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for(int i = 1; i < len; i++){
            res[i] = res[i-1]*nums[i-1];
        }
        int right = 1;
        for(int i = len-1; i >=0; i--){
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}

243. Shortest Word Distance
class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                p1 = i;
            }
            if(words[i].equals(word2)){
                p2 = i;
            }
            if(p1!=-1 && p2!=-1){
                res = Math.min(res,Math.abs(p1-p2));
            }
        }
        return res;
    // // }
    // public int shortestDistance(String[] words, String word1, String word2) {
    //     int res = Integer.MAX_VALUE;
    //     HashMap<String,List<Integer>> map = new HashMap<>();
    //     for(int i = 0; i < words.length; i++){
    //         if(map.containsKey(words[i])){
    //             List<Integer> list = map.get(words[i]);
    //             list.add(i);
    //             map.put(words[i], list);
    //         }else{
    //             List<Integer> list = new ArrayList<>();
    //             list.add(i);
    //             map.put(words[i],list);
    //         }
    //     }
    //     List<Integer> l1 = map.get(word1);
    //     List<Integer> l2 = map.get(word2);
    //     for(int i : l1){
    //         for(int j : l2){
    //             res = Math.min(res, Math.abs(i-j));
    //         }
    //     }
    //     return res;
    // }
}

245. Shortest Word Distance III
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals( word1)){
                p1 = i;
            }
            if(words[i].equals(word2)){
                if(word1.equals(word2)){
                    p1 = p2;
                }
                p2 = i;
            }
            if(p1!=-1 && p2 != -1){
                res = Math.min(res,Math.abs(p1-p2));
            }
        }
        return res;
    }
}

259. 3Sum Smaller
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0; i < len-2; i++){
            int left = i+1, right = len-1;
            while(left < right){
                if(nums[i]+nums[right]+nums[left]<target){
                    res += right-left; //for all elments from left to right will also satisfy
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res;
    }
}

277. Find the Celebrity
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i < n; i++){
            if(knows(candidate,i)) candidate = i;
        }
        for(int i = 0; i < n; i++){
            if(i != candidate && (!knows(i,candidate) || knows(candidate,i))){
                return -1;
            }
        }
        return candidate;
    }
}

280. Wiggle Sort
class Solution {
    public void wiggleSort(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(i%2==1){
                if(nums[i-1]> nums[i]) swap(i,i-1,nums);
            }else if(i!=0 && nums[i-1]<nums[i]) swap(i,i-1,nums);
        }
    }
    public void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

283. Move Zeroes
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j]!=0){
                nums[i++] = nums[j];
            }
        }
        while(i < nums.length){
            nums[i++] = 0;
        }
    }
}
