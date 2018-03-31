114. Flatten Binary Tree to Linked List
class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while(cur!=null || !stack.isEmpty()){
            if(cur.right!=null){
                stack.push(cur.right);
            }      
            if(cur.left!=null){
                cur.right = cur.left;
                cur.left = null;
            }else if(!stack.isEmpty()){
                TreeNode node = stack.pop();
                cur.right = node;
            }
            cur = cur.right;
        }
    }
}


116. Populating Next Right Pointers in Each Node
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;
        while(pre.left!=null){
            cur = pre;
            while(cur!=null){
                cur.left.next = cur.right;
                if(cur.next!=null){
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            pre = pre.left;
        }
    }
}

117. Populating Next Right Pointers in Each Node II
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode temp = new TreeLinkNode(0);
        while(root!=null){
            TreeLinkNode cur = temp;
			//level
            while(root!=null){
                if(root.left!=null){
                    cur.next = root.left;
                    cur = cur.next;
                }
                if(root.right!=null){
                    cur.next = root.right;
                    cur = cur.next;
                }
                root = root.next;
            }
            root = temp.next;
            temp.next = null;
        }
    }
}

102. Binary Tree Level Order Traversal
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            int CountInLevel = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < CountInLevel; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}

113. Path Sum II
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return res;
        Stack<Integer> list = new Stack<>();
        helper(root,sum,list);
        return res;
    }
    
    public void helper(TreeNode root, int sum, Stack<Integer> list){
        list.push(root.val);
        if(root.left == null && root.right == null && root.val == sum){
            res.add(new ArrayList<Integer>(list));
        }
        if(root.left!=null) helper(root.left,sum-root.val,list);
        if(root.right!=null) helper(root.right,sum-root.val,list);
        list.pop();
    }


129. Sum Root to Leaf Numbers
class Solution {
    public int sumNumbers(TreeNode root) {
        int res = 0;
        if(root == null) return res;
        return helper(root,res);
    }
    
    public int helper(TreeNode node, int res){
        if(node == null) return 0;
        if(node.left == null && node.right == null) return res*10+node.val;
        return helper(node.left,res*10+node.val)+helper(node.right,res*10+node.val);
    }
}

199. Binary Tree Right Side View
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int countInLevel = queue.size();
            for(int i = 0; i < countInLevel; i++){
                TreeNode node = queue.poll();
                if(node.left!= null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                if(i == countInLevel-1){
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}

200. Number of Islands
//dfs
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        if(grid == null) return res;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    res++;
                    helper(grid,i,j);
                }
            }
        }
        return res;
    }
    
    public void helper(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        helper(grid,i+1,j);
        helper(grid,i-1,j);
        helper(grid,i,j+1);
        helper(grid,i,j-1);
    }
}

/*
*
*
*
*
Union Find
*
*
*
*
*/
public class UnionFind{
    private int[] id;
    private int[] size;
    public UnionFind(int n){
        id = new int[n];
        size = new int[n];
        Array.fill(size,1);
        for(int i = 0; i < n; i++){
            id[i] = i;
        }
    }
    private int root(int i){
        while(i!=id[i]){
            id[i] = id[id[i]];  //path compression
            i = id[i];
        }
    }
    private boolean find(int p, int q){
        return root(p) == root(q);
    }
    public void unite(int p, int q){
        int i = root(p);
        int j = root(q);
        if(size[i]<size[j]){
            id[i] = j;
            size[j] += size[i];
        }
        if(size[i] >= size[j]){
            id[j] = i;
            size[i] += size[j];
        }
    }
}

