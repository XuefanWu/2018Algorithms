152. Maximum Product Subarray
//State: max[i] means [0,i-1] max value, min[i] means [0,i-1] min value 
//Formula: if(nums[i-1] < 0) this will make max smaller, and min bigger
            //                 max[i] = max(nums[i-1],min[i-1]*num[i-1])
            //                 min[i] = min(nums[i-1],max[i-1]*num[i-1])
            // if(nums[i-1] > 0) this will make max bigger, and min smaller
            //                 max[i] =  max(nums[i-1],max[i-1]*num[i-1])
            //                 min[i] =  min(nums[i-1],min[i-1]*num[i-1])
//Init: dp[1] = num[0];
//Ans: keep a gobal max to track
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] max = new int[nums.length+1];
        int[] min = new int[nums.length+1];
        max[1]=min[1]=nums[0];
        int res = nums[0];
        for(int i = 2; i <= nums.length; i++){
            if(nums[i-1]<0){
                max[i] = Math.max(nums[i-1],nums[i-1]*min[i-1]);
                min[i] = Math.min(nums[i-1],nums[i-1]*max[i-1]);
            }else{
                max[i] = Math.max(nums[i-1],nums[i-1]*max[i-1]);
                min[i] = Math.min(nums[i-1],nums[i-1]*min[i-1]);
            }
            res = Math.max(max[i],res);
        } 
        return res;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int max,min,res;
        max=min=res=nums[0];
        for(int i = 2; i <= nums.length; i++){
            int temp = max;
            if(nums[i-1]<0){
                max = Math.max(nums[i-1],nums[i-1]*min);
                min = Math.min(nums[i-1],nums[i-1]*temp);
            }else{
                max = Math.max(nums[i-1],nums[i-1]*max);
                min = Math.min(nums[i-1],nums[i-1]*min);
            }
            res = Math.max(max,res);
        } 
        return res;
    }
}

96. Unique Binary Search Trees
//State: dp[i] means for n nodes, how many unique BSTs
//Formula: dp[i] = dp[j-1]*dp[i-j] which 1<=j<=i
//Init: dp[0] = dp[1] = 1;
//Ans: dp[n]
//For n node, we can choose [1,n] as root
//dp[n] will be the sum of the count of each node as root
//and for each node as root, it is the multiply of each left subtree and right subtree
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <=n; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[i-j]*dp[j-1];
            }
        }
        return dp[n];
    }
}


85. Maximal Rectangle
//对于每一行，更新最左边和最右边，高度
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int row = matrix.length; 
        int col = matrix[0].length;
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right,col);
        int[] height = new int[col];
        int res = 0;
        for(int i = 0; i < row; i++){
            int cur_left = 0, cur_right = col;
            for(int j = 0; j < col; j++){
                if(matrix[i][j] = '1'){
                    left[j] = Math.max(cur_left,left[j]);
                }
                else{
                    left[j] = 0; cur_left = j+1;
                }
            }
            for(int j = col-1; j >=0; j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(cur_right,right[j]);
                }else{
                    cur_right = j;
                    right[j] = col;
                }
            }
            for(int j = 0; j < col; j++){
                if(matrix[i][j] == '1'){
                    height[j]++;
                }else{
                    height[j] = 0;
                }
            }
            for(int j = 0; j < col; j++){
                res = Math.max(height[j]*(right[j]-left[j]));
            }
        }
        return res;
    }
}


221. Maximal Square
//dp[i][j] means the size if max square can achieve at [i,j]
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 ) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int max = 0;
        for(int i = 0; i < row; i++){
            if(matrix[i][0] == '1')
            dp[i][0] = 1;
            max = Math.max(max,dp[i][0]);
        }
        for(int j = 0; j < col; j++){
            if(matrix[0][j] == '1'){
                dp[0][j] = 1;
                max =  Math.max(0,dp[0][j]);
            }
        }
        for(int i = 1; i < row; i++){
            for(int j =1; j < col; j++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1])+1;
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max*max;
    }
}