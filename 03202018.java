733. Flood Fill
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor || image == null) return image;
        helper(image,sr,sc,image[sr][sc],newColor);
        return image;
    }
    
    public void helper(int[][] image, int sr, int sc, int color, int newColor){
        if(sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        helper(image,sr+1,sc,color,newColor);
        helper(image,sr-1,sc,color,newColor);
        helper(image,sr,sc+1,color,newColor);
        helper(image,sr,sc-1,color,newColor);
    }
}

513. Find Bottom Left Tree Value
class Solution {
    //level order traversal
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            if(node.right!= null){
                queue.add(node.right);
            }
            if(node.left!= null){
                queue.add(node.left);
            }
        }
        return node.val;
    }
}

515. Find Largest Value in Each Tree Row
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        helper(root,res,0);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res, int depth){
        if(root == null) return;
        if(depth == res.size()){
            res.add(root.val);
        }else{
            res.set(depth, Math.max(res.get(depth),root.val));
        }
        helper(root.left,res,depth+1);
        helper(root.right,res,depth+1);
    }
}

94. Binary Tree Inorder Traversal
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right;
        }
        return res;
    }
}

98. Validate Binary Search Tree
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while( cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(pre!=null && pre.val >= cur.val){
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
}