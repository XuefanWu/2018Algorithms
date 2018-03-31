124. Binary Tree Maximum Path Sum
class Solution {
    int res;
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }
    public int helper(TreeNode root){
        if(root == null) return 0;
        int sum = root.val;
        int left = helper(root.left);
        int right = helper(root.right);
        if(left > 0) sum+=left;
        if(right > 0) sum += right;
        res = Math.max(sum,res);
        return Math.max(right,left) > 0 ? Math.max(right,left)+root.val : root.val;
    }
}

297. Serialize and Deserialize Binary Tree
public class Codec {
    private static final String spliter = ",";
    private static final String empty = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Encode(root,sb);
        return sb.toString();
    }
    
    public void Encode(TreeNode root, StringBuilder sb){
        if(root == null) sb.append(empty).append(spliter);
        else{
            sb.append(root.val).append(spliter);
            Encode(root.left,sb);
            Encode(root.right,sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return Decode(nodes);
    }
    
    public TreeNode Decode(Queue<String> nodes){
        String val = nodes.poll();
        if(val.equals (empty)){
            return null;
        }
        else{
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = Decode(nodes);
            node.right = Decode(nodes);
            return node;
        }
    }
}

437. Path Sum III
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return helper(root,sum)+pathSum(root.left,sum)+pathSum(root.right,sum);
    }
    
    public int helper(TreeNode root, int sum){
        if(root == null) return 0;
        return ((sum == root.val) ? 1 : 0)+ helper(root.left, sum-root.val) + helper(root.right, sum-root.val);
    }
    
}