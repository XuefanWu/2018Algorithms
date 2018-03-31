99. Recover Binary Search Tree
class Solution {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        helper(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    public void helper(TreeNode node){
        if(node == null) return;
        helper(node.left);
        if(first == null && pre.val >= node.val){
            first = pre;
        }
        if(first != null && pre.val >= node.val){
            second = node;
        }
        pre = node;
        helper(node.right);
    }
   
}

103. Binary Tree Zigzag Level Order Traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        boolean isZigZag = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int countInLevel = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < countInLevel; i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right!= null) queue.offer(node.right);
                if(isZigZag){
                    level.add(0,node.val);
                }else{
                    level.add(node.val);
                }
            }
            res.add(level);
            isZigZag = !isZigZag;
        }
        return res;
    }
}

107. Binary Tree Level Order Traversal II
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int countInLevel = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < countInLevel; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            res.add(0,level);
        }
        return res;
    }
}

105. Construct Binary Tree from Preorder and Inorder Traversal
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i],i);
        }
        return helper(preorder,0,preorder.length-1, inorder, 0, inorder.length-1,map);
    }
    
    public TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer,Integer> map){
        if(preStart>preEnd || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = map.get(preorder[preStart]);
        int gap = index-inStart;
        root.left = helper(preorder, preStart+1,preStart+gap,inorder, inStart, index-1, map);
        root.right = helper(preorder, preStart+gap+1, preEnd, inorder, index+1, inEnd, map);
        return root;
    }
}

106. Construct Binary Tree from Inorder and Postorder Traversal
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map  = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i],i);
        }
        return helper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1,map);
    }
    
    public TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer,Integer> map){
        if(inStart > inEnd || postStart > postEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = map.get(postorder[postEnd]);
        int gap = index-inStart;
        root.left = helper(inorder, inStart, index-1,postorder, postStart, postStart+gap-1,map);
        root.right = helper(inorder, index+1, inEnd, postorder, postStart+gap, postEnd-1,map);
        return root;
    }
}

144. Binary Tree Preorder Traversal
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node!=null){
                res.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return res;
    }
}

145. Binary Tree Postorder Traversal
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode temp = stack.peek();
            if(temp.right!=null && temp.right!=pre){
                cur = temp.right;
            }else{
                res.add(temp.val);
                pre = temp;
                stack.pop();
            }
        }
        return res;
    }
}
