//DP
62. Unique Paths
class Solution {
    //dp[i][j] = dp[i-1][j]+dp[i][j-1];
    //dp[i][j] means how many ways to get to point[i,j]
    //初始化： 两条边都是1
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i<m; i++){
            dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}

63. Unique Paths II
class Solution {
    //初始化两条边都是1， 碰到obstacle， break
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){          
            if(obstacleGrid[i][0]== 0){
                dp[i][0] = 1;
            }else{
                break;
            }
        }
        for(int i = 0; i < n; i++){
            if(obstacleGrid[0][i] == 0){
                dp[0][i] = 1;
            }else{
                break;
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}

64. Minimum Path Sum
class Solution {
    //初始化 : 两条边累加过去
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < row; i++){
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for(int j = 1; j < col ;j++){
            dp[0][j] = dp[0][j-1]+grid[0][j];
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
}

120. Triangle
class Solution {
    //dp[i][j] = min(dp[i+1][j],dp[i+1][j+1]) + triangle[i][j]
    //for each node, we need to assume that we know the value from the next layer so that we can get the smaller one
    //for layer i
    //dp[j] = min(dp[j],dp[j+1])+triangle[i][j]
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0 ) return 0;
        int m = triangle.size();
        int[] dp = new int[m+1];
        for(int i = m-1; i >=0; i--){
            for(int j = 0; j < triangle.get(i).size(); j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];

    }
}