661. Image Smoother
class Solution {
    public int[][] imageSmoother(int[][] M) {
        if(M == null) return null;
        int rows = M.length;
        if(rows == 0) return new int[0][];
        int cols = M[0].length;
        int res[][] = new int[rows][cols];
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{1,1},{-1,1},{1,-1},{0,0}};
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int cnt = 0, sum = 0;
                for(int[]d : dirs){
                    int x = i+d[0], y = j+d[1];
                    if(isValid(x,y,rows,cols)){
                        cnt++;
                        sum += M[x][y];
                    }
                }
                res[i][j] = sum/cnt;
            }
        }
        return res;
    }
    private boolean isValid(int i, int j, int rows, int cols){
        if(i >=0 && i < rows && j >= 0 && j < cols) return true;
        return false;
    }
}

667. Beautiful Arrangement II
//https://leetcode.com/problems/beautiful-arrangement-ii/discuss/106948/C++-Java-Clean-Code-4-liner
class Solution {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        for(int i = 0, l = 1, r = n; l <= r; i++){
            if(k>1){
                if(k--%2 != 0){
                    res[i] = l++;
                }else{
                    res[i] = r--;
                }
            }else{
                res[i] = l++;
            }
        }
        return res;
    }
}


526. Beautiful Arrangement
class Solution {
    int res = 0;
    public int countArrangement(int N) {
        if(N == 0) return 0;
        helper(N,1,new int[N+1]);
        return res;
    }
    public void helper(int N, int pos, int[] used){
        if(pos > N){
            res++;
            return;
        }
        for(int i = 1; i <= N; i++){
            if(used[i] == 0 &&(i%pos == 0 || pos % i==0)){
                used[i] = 1;
                helper(N,pos+1,used);
                used[i] = 0;
            }
        }
    }
}

697. Degree of an Array
class Solution {
    public int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Map<Integer, int[] > map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new int[]{1,i,i});
            }else{
                int[] temp = map.get(nums[i]);
                temp[0]++;
                temp[2] = i;
            }
        }
        //array pass by reference
        int degree = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
        for(int[] value : map.values()){
            if(value[0] > degree){
                degree = value[0];
                res = value[2]-value[1]+1;
            }else if(value[0] == degree){
                res = Math.min(value[2]-value[1]+1,res);
            }
        }
        return res;
    }
}

717. 1-bit and 2-bit Characters
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        int i = 0;
        while(i < len-1){
            if(bits[i] == 0) i++;
            else i+=2;
        }
        return i == len-1;
    }
}

766. Toeplitz Matrix
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows-1; i++){
            for(int j = 0; j < cols-1; j++){
                if(matrix[i][j]!=matrix[i+1][j+1]) return false;
            }
        }
        return true;
    }
}