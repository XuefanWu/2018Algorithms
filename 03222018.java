109. Convert Sorted List to Binary Search Tree
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return helper(head,null);
    }
    
    public TreeNode helper(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head == tail) return null;
        while(fast.next!=tail && fast.next.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head,slow);
        root.right = helper(slow.next,tail);
        return root;
    }
}

133. Clone Graph
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node,new HashMap<Integer,UndirectedGraphNode>());
    }
    
    public UndirectedGraphNode helper(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map){
        if(node == null) return null;
        if(map.containsKey(node.label)){
            return map.get(node.label);
        }
        else{
            UndirectedGraphNode temp = new UndirectedGraphNode(node.label);
            map.put(node.label,temp);
            for(int i = 0; i < node.neighbors.size(); i++){
                temp.neighbors.add(helper(node.neighbors.get(i),map));
            }
            return temp;
        }
    }
}

332. Reconstruct Itinerary
class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(int i = 0; i < tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                map.put(tickets[i][0],new PriorityQueue<String>());
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while(!stack.isEmpty()){
            while(map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()){
                stack.push(map.get(stack.peek()).poll());
            }          
            res.add(0,stack.pop());  
        }
        return res;
    }
}

337. House Robber III    //dp(dfs)+memorization
class Solution {
    public int rob(TreeNode root) {
        return helper(root,new HashMap<TreeNode,Integer>());
       
    }
    
    public int helper(TreeNode root, Map<TreeNode, Integer> map){
        int res = 0;
        if(root == null) return res;
        
        if(map.containsKey(root)){
            return map.get(root);
        }
        
        if(root.left!=null){
            res += helper(root.left.left,map);
            res += helper(root.left.right,map);
        }
        if(root.right!=null){
            res += helper(root.right.left,map);
            res += helper(root.right.right,map);
        }
        
        
        res = Math.max(root.val+res, helper(root.left,map)+helper(root.right,map));
        map.put(root,res);
        return res;
    }
}


