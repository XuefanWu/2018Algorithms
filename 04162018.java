//Backtracking
47. Permutations II
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        helper(nums,res,new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    public void helper(int[] nums, List<List<Integer>> res, List<Integer> list, boolean[] used){
        if(list.size() == nums.length) res.add(new ArrayList<Integer>(list));
        else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || (i>0 && nums[i] == nums[i-1] && !used[i-1])) continue; 
                //we can only use duplicate element when its previous element has already been used
                used[i] = true;
                list.add(nums[i]);
                helper(nums,res,list,used);
                list.remove(list.size()-1);
                used[i] = false;
            }
        }
    }
}

77. Combinations
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n <= 0 || k <= 0) return res;
        helper(n,k,res,new ArrayList<Integer>(),1);
        return res;
    }
    public void helper(int n, int k, List<List<Integer>> res, List<Integer> list, int start){
        if(list.size() == k){
            res.add(new ArrayList<Integer>(list));
            return;
        } 
        for(int i = start; i <= n; i++ ){
            list.add(i);
            helper(n,k,res,list,i+1);
            list.remove(list.size()-1);
        }
    }
}

78. Subsets
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        helper(res,new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start){
        res.add(new ArrayList<Integer>(list));
        for(int i = start; i < nums.length; i++){
            list.add(nums[i]);
            helper(res, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}

90. Subsets II
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        helper(res,new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start){
        res.add(new ArrayList<Integer>(list));
        for(int i = start; i < nums.length; i++){
            if(i>start && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            helper(res, list, nums, i+1);
            list.remove(list.size()-1);
        }
    }
}

131. Palindrome Partitioning
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() == 0) return res;
        helper(res, new ArrayList<String>(), s, 0);
        return res;
    }

    public void helper(List<List<String>> res, List<String> list, String s, int start){
        if(start == s.length()){
            res.add(new ArrayList<String>(list));
            return;
        }
        for(int i = start; i < s.length(); i++){
            if(isPalindrome(s,start,i)){
                list.add(s.substring(start,i+1));
                helper(res,list,s,i+1);
                list.remove(list.size()-1);
            }
        }
        
    }
    public boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++)!=s.charAt(end--)) return false;
        }
        return true;
    }
}

52. N-Queens II
class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2*n];
        boolean[] d2 = new boolean[2*n];
        helper(cols,d1,d2,n,0);
        return count;
    }
    public void helper(boolean[] cols, boolean[] d1, boolean[] d2, int n, int row){
        if(row == n) count++;
        for(int col = 0; col < n; col++){
            int id1 = col-row+n;
            int id2 = col+row;
            // 45 degree line is y = x + b
            // 135 degree line is y = -x + b
            // in another word, 45 degree y - x is constant, and 135 degree y + x is constant.
            // Here b is shifted to [0~2n), so y-x and y+x can be trackable.           
            if(cols[col] || d1[id1] || d2[id2]) continue;
            cols[col] = true; d1[id1] = true; d2[id2] = true;
            helper(cols,d1,d2,n,row+1);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
}

79. Word Search
class Solution {
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < borad[0].length; j++){
                if(helper(board,word,i,j,0)) return true;
            }
        }
        return false;
    }
    public boolean helper(char[][] board, String word, int i, int j, int start){
        if(start == word.length()) return true;
        if(i < 0 || j < 0 || i == board.length || j == board[0].length) return false;
        if(board[i][j] != word.charAt(start)) return false;
        char temp = board[i][j];
        board[i][j] = '.';
        boolean ex = helper(board,word,i+1,j,start+1) 
                    || helper(board,word,i-1,j,start+1) 
                    || helper(board,word,i,j+1,start+1) 
                    || helper(board,word,i,j-1,start+1);
        boead[i][j] = temp;
        return ex;
    }
}

89. Gray Code
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for(int i = 0; i < n; i++){
            int size = res.size();
            for(int j = size-1; j>=0; j--){
                res.add(res.get(j)|1<<i);
            }
        }
        return res;
    }
}

class Solution {
    public List<Integer> grayCode(int n) {
        if(n == 0){
            List<Integer> res = new ArrayList<>(); 
            res.add(0);
            return res;
        }
        List<Integer> res = grayCode(n-1);
        int addNum = 1 << (n-1);
        int originSize = res.size();
        for(int i = originSize-1; i >= 0; i--){
            res.add(addNum + res.get(i));
        }
        return res;
    }
}