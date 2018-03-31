 207. Course Schedule
 //BFS
 public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        
        for(int[] p : prerequisites){
            indegree[p[0]]++;
        }
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int cur = queue.poll();
            numCourses--;
            for(int[] p : prerequisites){
                if(p[1] == cur){
                    indegree[p[0]]--;
                    if(indegree[p[0]] == 0){
                        queue.offer(p[0]);
                    }
                }
            }
        }
        return numCourses == 0;
    }

210. Course Schedule II
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int[] res = new int[numCourses];
        int len = numCourses;
        for(int[] p : prerequisites){
            indegree[p[0]]++;
        }
        for(int i = 0; i < len; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        } 
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            numCourses--;
            res[len-numCourses-1] = cur;
            for(int[] p : prerequisites){
                if(p[1] == cur){
                    indegree[p[0]]--;
                    if(indegree[p[0]] == 0){
                    queue.offer(p[0]);
                    }
                }    
            }
        }
        return numCourses == 0? res : new int[0];
    }
}


394. Decode String
class Solution {
    public String decodeString(String s) {
        int len = s.length();
        Stack<String> res = new Stack<>();
        Stack<Integer> count = new Stack<>();
        int index = 0;
        String result = "";
        while(index < len){
            if(Character.isDigit(s.charAt(index))){
                int cnt = 0;
                while(Character.isDigit(s.charAt(index))){
                    cnt = cnt*10 + (s.charAt(index)-'0');
                    index++;
                }
                count.push(cnt);
            }
            else if(s.charAt(index) == '['){
                res.add(result);
                result = "";
                index++;
            }
            else if(s.charAt(index) == ']'){
                String temp = res.pop();
                int cnt = count.pop();
                for(int i = 0; i < cnt; i++){
                    temp+=result;
                }
                result = temp.toString();
                index++;
            }
            else{
                result += s.charAt(index);
                index++;
            }
        }
        return result;
    }
}


96. Unique Binary Search Trees
	//take 1-n as root
    //1: f(0) * f(n-1)
    //2: f(1) * f(n-2)
    //n: f(n-1) * f(0)
    //f(n) = f(0)*f(n-1)+f(1)*f(n-2)+...+f(n-2)*f(1)+f(n-1)*f(0)
    //f(3) = f(0)*f(2)+f(1)*f(1)+f(2)*(0)
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <=i; j++){
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}

95. Unique Binary Search Trees II
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new ArrayList<TreeNode>();
        return helper(1,n);
    }
    
    public List<TreeNode> helper(int start, int end){
        List<TreeNode> list = new ArrayList<>();
        if(start>end){
            list.add(null);
            return list;
        }
        if(start == end){
            list.add(new TreeNode(start));
            return list;
        }
        List<TreeNode> left, right;
        for(int i = start; i<=end; i++){
            left = helper(start,i-1);
            right = helper(i+1,end);
            for(TreeNode l : left){
                for(TreeNode r : right){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }
        return list;
    }
}