//Array
84. Largest Rectangle in Histogram
class Solution {
    //maintain an increasing stack, if current > top of stack, push
    //if current <= top of stack
    //pull the top, it will be the smallest block campare its right(exclude current block) and largest compare its left
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int res = 0;
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : heights[i]);
            if(s.isEmpty() || h >= heights[s.peek()]){
                s.push(i);
            }else{
                int top = s.pop();
                res = Math.max(res,hrights[top]*(s.isEmpty()?i:(i-1-s.peek())));
                i--;
            }
        }
        return res;
    }
}

//118. Pascal's Triangle
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
         List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            list.add(0,1);
            for(int j = 1; j < list.size()-1; j++){
                list.set(j,list.get(j)+list.get(j+1));
            }
            res.add(new ArrayList<>(list));
        }
        return res;
    }
}

//119. Pascal's Triangle II
class Solution {
    public List<Integer> getRow(int rowIndex) {
         List<Integer> list = new ArrayList<>();
        for(int i = 0; i < rowIndex+1; i++){
            list.add(0,1);
            for(int j = 1; j < list.size()-1; j++){
                list.set(j,list.get(j)+list.get(j+1));
            }
        }
        return list;
    }
}

126. Word Ladder II
//output all shortest path
//using BFS to build adjacency list and backtracking to go through the graph and add list to res
class Solution {
    Map<String,List<String>> map;
    List<List<String>> res;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        res = new ArrayList<>();
        Map<String,Integer> steps = new HashMap<>();
        map = new HashMap<>();
        for(String word:wordList) steps.put(word,Integer.MAX_VALUE);
        if(wordList.size() == 0 || !steps.containsKey(endWord)) return res;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        steps.put(beginWord,0);
        int len = beginWord.length();
        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            String word = queue.poll();
            int step = steps.get(word)+1;
            if(step>min) break;
            char[] chs = word.toCharArray();
            for(int i = 0; i < len; i++){
                 char temp = chs[i];
                for(char c = 'a'; c<='z'; c++){
                   if(c == temp) continue;
                    chs[i] = c;
                    String s = new String(chs);
                    if(steps.containsKey(s)){
                        if(step > steps.get(s)) continue;
                        else if(step < steps.get(s)){
                            queue.offer(s);
                            steps.put(s,step);
                        }else;
                        if(map.containsKey(s)){
                            map.get(s).add(word);
                        }else{
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(s,list);
                        }
                        if(s.equals(endWord)) min = step;
                    }

                }
                chs[i] = temp;
            }
        }
        List<String> l = new ArrayList<>();
        backTracking(endWord,beginWord,l);
        return res;
    }

    void backTracking(String word, String begin, List<String> list){
        if(word.equals(begin)){
            list.add(0,begin);
            res.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        list.add(0,word);
        if(map.get(word)!=null){
            for(String s : map.get(word)){
                backTracking(s,begin,list);
            }
        }
        list.remove(0);
    }
}


189. Rotate Array
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(0,nums.length-1,nums);
        reverse(0,k-1,nums);
        reverse(k,nums.length-1,nums);
    }
    void reverse(int start, int end, int[] nums){
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

128. Longest Consecutive Sequence
//https://leetcode.com/problems/longest-consecutive-sequence/discuss/41055/My-really-simple-Java-O(n)-solution-Accepted
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int n : nums){
            if(!map.containsKey(n)){
                int left = map.containsKey(n-1)? map.get(n-1):0;
                int right = map.containsKey(n+1)? map.get(n+1):0;
                int sum = left+right+1;
                map.put(n,sum);
                res = Math.max(res,sum);
                map.put(n-left,sum);
                map.put(n+right,sum);
            }else{
                continue;
            }
        }
        return res;
    }
}

163. Missing Ranges
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            if(lower == Integer.MAX_VALUE) return res;
            if(nums[i] < lower) continue;
            else if(nums[i] == lower){
                lower++;
                continue;
            }
            res.add(helper(lower,nums[i]-1));
            if(nums[i] == Integer.MAX_VALUE) return res;
            lower = nums[i]+1;
        }
        if(lower <= upper){
            res.add(helper(lower,upper));
        }
        return res;
    }
    public String helper(int i, int j){
        return (i==j) ? i+"" : i+"->"+j;
    }
}