127. Word Ladder
//BFS
class Solution {
 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>();
		for(String word:wordList) dict.add(word);
		if(!dict.contains(endWord)){
			return 0;
		}
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		
		int len = beginWord.length();
		int steps = 0;
		
		while(!queue.isEmpty()){
			steps++;
			for(int s = queue.size(); s > 0; s--){
				String w = queue.poll();
				char[] chs = w.toCharArray();
				for(int i = 0; i < len; i++){
					char ch = chs[i];
					for(char c = 'a'; c <= 'z'; c++){
						if(c == ch) continue;
						chs[i] = c;
						String temp = new String(chs);
						if(temp.equals(endWord)) return steps+1;
						if(!dict.contains(temp)) continue;
						dict.remove(temp);
						queue.offer(temp);
					}
					chs[i] = ch;
				}
			}
		}
		return 0;
         
    }
}

//Bidirection BFS
class Solution {
 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>();
		for(String word : wordList) dict.add(word);
		if(!dict.contains(endWord)) return 0;
		
		Set<String> beginSet = new HashSet<>();
		Set<String> endSet = new HashSet<>();
		beginSet.add(beginWord);
		endSet.add(endWord);
		
		int len = beginWord.length();
		int steps = 0;
		
		while(!beginSet.isEmpty() && !endSet.isEmpty()){
			steps++;
			if(beginSet.size() > endSet.size()){
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}
			Set<String> set = new HashSet<>();
			for(String s : beginSet){
				char[] chs = w.toCharArray();
				for(int i = 0; i < len; i++){
					char ch = chs[i];
					for(char c = 'a'; c <= 'z'; c++){
						chs[i] = c;
						String t = new String(chs);
						if(endSet.contains(t)) return steps + 1;
						if(!dict.contains(t)) continue;
						dict.remove(t);
						set.add(t);
					}
					chs[i] = ch;
				}
			}
			beginSet = set;
		}
		return 0;
    }
}


35. Search Insert Position
public int searchInsert(int[] nums, int target) {
        //find the first position >= target
        if(nums == null || nums.length == 0) return 0;
        int start = 0, end = nums.length-1;
        while(start+1 < end){
            int mid = start+(end-start)/2;
            if(target== nums[mid]){
                return mid;
            }else if(target>nums[mid]){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(nums[start] >=target){
            return start;
        }else if(nums[end] >= target){
            return end;
        }else{
            return end+1;
        }

    }
	
69. Sqrt(x)
	public int mySqrt(int x) {
        long start = 1, end = x;
        while(start + 1 < end){
            long mid = start+(end-start)/2;
            if(mid*mid <= x){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(end*end <= x){
            return (int) end;
        }
        return (int)start;
    }