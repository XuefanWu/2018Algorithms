173. Binary Search Tree Iterator
public class BSTIterator {

    Stack<TreeNode> stack;
    
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        
            push(node.right);
        
        return node.val;
    }
    
    public void push(TreeNode root){
        TreeNode cur = root;
        while(cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
    }
}

222. Count Complete Tree Nodes
class Solution {
    public int countNodes(TreeNode root) {
        int rHeight = rightHeight(root);
        int lHeight = leftHeight(root);
        if(rHeight == lHeight){
            return (1 << lHeight)-1;
        }else{
            return 1+countNodes(root.right)+countNodes(root.left);
        }
    }
    
    public int rightHeight(TreeNode root){
        int height = 0;
        while(root!=null){
            height++;
            root = root.right;
        }
        return height;
    }
    
    public int leftHeight(TreeNode root){
        int height = 0;
        while(root!=null){
            height++;
            root = root.left;
        }
        return height;
    }
    
}

226. Invert Binary Tree
class Solution {
    //Recursion
    // public TreeNode invertTree(TreeNode root) {   
    //     return helper(root);
    // }   
    // public TreeNode helper(TreeNode root){
    //     if(root == null) return root;
    //     TreeNode left = root.left;
    //     TreeNode right = root.right;
    //     root.right = helper(left);
    //     root.left = helper(right);
    //     return root;
    // }
    
    //Stack
    public TreeNode invertTree(TreeNode root){
        if(root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        return root;
    }
}

230. Kth Smallest Element in a BST
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            k--;
            if(k == 0) break;
            stack.pop();
            cur = cur.right;
        }
        return cur.val;
    }
}

235. Lowest Common Ancestor of a Binary Search Tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }
}

236. Lowest Common Ancestor of a Binary Tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root ==  null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null) return root;
        return left==null? right:left;
    }
}

404. Sum of Left Leaves
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        if(root.left!=null){
            if(root.left.left == null && root.left.right == null) res+=root.left.val;
            else{
                res+=sumOfLeftLeaves(root.left);
            }
        }
        res+=sumOfLeftLeaves(root.right);
        return res;
    }
}
