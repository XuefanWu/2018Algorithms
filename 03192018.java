104. Maximum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}

695. Max Area of Island
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                res = Math.max(res,helper(grid,i,j));
            }
        }
        return res;
    }
    
        public int helper(int[][] grid, int i, int j){
        if(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j]== 1){
            grid[i][j] = 0;
            return 1 + helper(grid,i+1,j)+helper(grid,i-1,j)+helper(grid,i,j+1)+helper(grid,i,j-1);
        }
            return 0;
    }
}

690. Employee Importance
/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        List<Integer> sub = new ArrayList<>();
        int res = 0;
        for(Employee e : employees){
            if(e.id == id){
                res += e.importance;
                sub = e.subordinates;
                break;
            }
        }
        if(sub.size() == 0){
            return res;
        }else{
            for(int e : sub){
            res += getImportance(employees,e);
        }
        }
        
        return res;
    }
}

100. Same Tree
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
        }
        return false;
    }
}

108. Convert Sorted Array to Binary Search Tree
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
       TreeNode res = null; 
       if(nums == null || nums.length == 0) return res;
        res = helper(0,nums.length-1, nums);
        return res;
    }
    
    public TreeNode helper(int start, int end, int[] nums){
        if(start > end) return null;
        int mid = (start+end)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(start,mid-1,nums);
        node.right = helper(mid+1,end,nums);
        return node;
    }
}

101. Symmetric Tree
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left,right.right) && helper(left.right,right.left);
    }
}

110. Balanced Binary Tree
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(height(root.left)-height(root.right))<=1 && isBalanced(root.right) && isBalanced(root.left);
    }
    
    public int height(TreeNode node){
        if(node == null) return 0;
        return Math.max(height(node.left),height(node.right))+1;
    }
}

112. Path Sum
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
       if(root == null) return false;
        if(root.left == null && root.right == null && sum-root.val == 0) return true;
        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
    }
}

111. Minimum Depth of Binary Tree
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return helper(root);
    }
    
    public int helper(TreeNode root){
        if(root == null) return Integer.MAX_VALUE;
        if(root.left == null && root.right == null) return 1;
        return Math.min(helper(root.left),helper(root.right))+1;
    }
}

257. Binary Tree Paths
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null) return res;
        helper(root,"",res);
        return res;
    }
    
    public void helper(TreeNode root, String path, List<String> res){
        if(root.left == null && root.right == null){
            path += root.val;
            res.add(path);
        }
        if(root.left != null) helper(root.left, path+root.val+"->",res);
        if(root.right != null) helper(root.right, path+root.val+"->",res);
    }
}
