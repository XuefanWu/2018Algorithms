//Array
562. Longest Line of Consecutive One in Matrix
//DFS
class Solution {
    public int longestLine(int[][] M) {
        if(M == null) return 0;
        int res = 0;
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M[0].length; j++){
                if(M[i][j] == 1){
                    res = Math.max(res, helper(M,i,j));
                }
            }
        }
        return res;
    }
    final int [][] dirs = new int[][]{{1,0},{0,1},{1,1},{1,-1}};
    private int helper(int[][] M, int i, int j){
        int res = 1;
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            int cnt = 1;
            while(x < M.length && x >=0 && y < M[0].length && y >=0 && M[x][y] ==1){
                x += dir[0];
                y += dir[1];
                cnt++;
            }
            res = Math.max(res,cnt);
        }
        return res;
    }
}

//DP
public int longestLine(int[][] M) {
    int n = M.length, max = 0;
    if (n == 0) return max;
    int m = M[0].length;
    int[][][] dp = new int[n][m][4];
    for (int i=0;i<n;i++) 
        for (int j=0;j<m;j++) {
            if (M[i][j] == 0) continue;
            for (int k=0;k<4;k++) dp[i][j][k] = 1;
            if (j > 0) dp[i][j][0] += dp[i][j-1][0]; // horizontal line
            if (j > 0 && i > 0) dp[i][j][1] += dp[i-1][j-1][1]; // anti-diagonal line
            if (i > 0) dp[i][j][2] += dp[i-1][j][2]; // vertical line
            if (j < m-1 && i > 0) dp[i][j][3] += dp[i-1][j+1][3]; // diagonal line
            max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
        }
    return max;
}

