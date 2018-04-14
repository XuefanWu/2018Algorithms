//backtracking
37. Sudoku Solver
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        helper(board);
    }

    public boolean helper(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(board, i, j, c)){
                            board[i][j] = c;
                            if(helper(board)){
                                return true;
                            }else{
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row , int col, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] != '.' && board[row][i] == c) return false;
            if(board[i][col] != '.' && board[i][col] == c) return false;
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' 
             && board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] == c){
                 return false;
             }
        }
        return true;
    }
}

// Start loop from next possible position rather than from the very beginning every time.
// Simplify isValid() and next position calculation (simply j + 1).
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        helper(board,0,0);
    }

    public boolean helper(char[][] board, int row, int col){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] != '.') continue;
                for(char c = '1'; c <= '9'; c++){
                    if(isValid(board, i, j, c)){
                        board[i][j] = c;
                        if(helper(board,i,j+1)){
                            return true;
                        }else{
                            board[i][j] = '.';
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean isValid(char[][] board, int row , int col, char c){
        for(int i = 0; i < 9; i++){
            if(board[row][i] != '.' && board[row][i] == c) return false;
            if(board[i][col] != '.' && board[i][col] == c) return false;
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' 
             && board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] == c){
                 return false;
             }
        }
        return true;
    }
}


39. Combination Sum
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        helper(candidates, target, 0, res, new ArrayList<Integer>());
        return res;
    }

    public void helper(int[] candidates, int remain, int start, List<List<Integer>> res, List<Integer> list){
        if(remain < 0) return;
        else if(remain == 0) res.add(new ArrayList<Integer>(list));
        else{
            for(int i = start; i < candidates.length; i++){
                list.add(candidates[i]);
                helper(candidates,remain-candidates[i],i,res,list); //can reuse
                list.remove(list.size()-1);
            }
        }
    }
}

40. Combination Sum II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); //sort to skip dup
        if(candidates == null || candidates.length == 0) return res;
        helper(candidates, target, 0, res, new ArrayList<Integer>());
        return res;
    }

    public void helper(int[] candidates, int remain, int start, List<List<Integer>> res, List<Integer> list){
        if(remain < 0) return;
        else if(remain == 0) res.add(new ArrayList<Integer>(list));
        else{
            for(int i = start; i < candidates.length; i++){
                if(i > start && candidates[i] == candidates[i-1]) continue; // skip dup
                list.add(candidates[i]);
                helper(candidates,remain-candidates[i],i+1,res,list); //can't reuse the same 
                list.remove(list.size()-1);
            }
        }
    }
}

46. Permutations
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        helper(nums,res,new ArrayList<Integer>());
        return res;
    }

    public void helper(int[] nums, List<List<Integer>> res, List<Integer> list){
        if(list.size() == nums.length) res.add(new ArrayList<Integer>(list));
        else{
            for(int i = 0; i < nums.length; i++){
                if(list.contains(nums[i])) continue;
                list.add(nums[i]);
                helper(nums,res,list);
                list.remove(list.size()-1);
            }
        }
    }
}

