//Backtracking
51. N-Queens
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board,0,res);
        return res;
    }

    private void dfs(char[][] board, int colIndex, List<List<String>> res){
        int len = board.length;
        if(colIndex == len){
            res.add(build(board));
            return;
        }
        for(int i = 0; i < len; i++){
            if(validate(board,i,colIndex)){
                board[i][colIndex] = 'Q';
                dfs(board, colIndex+1,res);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean validate(char[][] board, int x, int y){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < y; j++){
                if(board[i][j] == 'Q' && ( Math.abs(i-x) == Math.abs(j-y) || x == i )){
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> build(char[][] board){
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}

17. Letter Combinations of a Phone Number
//recursion(backtracking)
class Solution {
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {        
        List<String> res = new ArrayList<>();
        helper(res,digits,"",0);
        return res;
    }

    public void helper(List<String> res, String digits, String prefix, int pos){
        if(pos >= digits.length()){
            if(prefix!=""){
                 res.add(prefix);
            }
            return;
        }
        String letters = KEYS[(digits.charAt(pos)-'0')];
        for(int i = 0; i < letters.length(); i++){
            helper(res,digits,prefix+letters.charAt(i),pos+1); 
                        //because string is const, it is not pass by reference, so we don't need to 
                        //    add and remove(backtrack)
        }
    }
}

//Iterative(backtracking)
class Solution {
    public List<String> letterCombinations(String digits) {        
       String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
       //using a FIFO queue
       LinkedList<String> res = new LinkedList<>();
        if(digits == "") {return res;}
        res.add("");
        for(int i = 0; i < digits.length(); i++){
           int pos = digits.charAt(i)-'0';
           while(res.peek().length() == i){
               String t = res.remove();
               for(char c : KEYS[pos].toCharArray()){
                    res.add(t+c); 
               }
           }
       } 
       return res;
    }
}

22. Generate Parentheses
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(res,0,0,"",n);
        return res;
    }
    public void helper(List<String> res, int open, int close, String s, int max){
        if(s.length() == max*2){
            res.add(s);
            return;
        }
        if(open < max) helper(res,open+1,close,s+"(",max);
        if(close < open) helper(res,open, close+1,s+")",max);
    }


}