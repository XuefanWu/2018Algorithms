//Heap
218. The Skyline Problem
// for each critical point c
//     c.y gets the height of the tallest rectangle over c
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        //building {left,right,height}
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] building : buildings){
            height.add(new int[]{building[0],-building[2]});
            height.add(new int[]{building[1],building[2]});
        }
        //sort height based on x-index and height
        Collections.sort(height,(a,b)->{
            if(a[0]!=b[0]){
                return a[0]-b[0];
            }
            return a[1]-b[1]; 
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));
        pq.offer(0);
        int prev = 0;
        for(int[] h : height){
            //if left boundry, add to pq, if right boundry, remove from pq
            if(h[1]<0){
                pq.offer(-h[1]);
            }else{
                pq.remove(h[1]);
            }
            //get the highest block for this index
            int cur = pq.peek();
            //if height changes, update the height and add to res
            if(prev != cur){
                res.add(new int[]{h[0],cur});
                prev = cur;
            }
        }
        return res;
    }
}

451. Sort Characters By Frequency
//bucket sort
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        List<Character>[] bucket = new List[s.length()+1];
        for(char key : map.keySet()){
            int freq = map.get(key);
            if(bucket[freq] ==null){
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = bucket.length-1; i >=0; i--){
            if(bucket[i]!=null){
                for(char c : bucket[i]){
                    int count = map.get(c);
                    while(count > 0){
                        sb.append(c);
                        count--;
                    }
                }
            }
        }
        return sb.toString();

    }
}
//PQ
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        pq.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Map.Entry e = pq.poll();
            int count = (int)e.getValue();
            while(count > 0){
                sb.append(e.getKey());
                count--;
            }
        }
        return sb.toString();

    }
}

659. Split Array into Consecutive Subsequences
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>(), append = new HashMap<>();
        for(int i : nums) map.put(i,map.getOrDefault(i,0)+1);
        for(int i : nums){
            if(map.get(i) == 0) continue;
            //can be append to some subsequence
            else if(append.getOrDefault(i,0)>0){
                append.put(i, append.get(i)-1);
                append.put(i+1, append.getOrDefault(i+1,0)+1);
            //check if can be the start of subsequence
            }else if(map.getOrDefault(i+1,0) > 0 && map.getOrDefault(i+2,0)>0){
                map.put(i+1, map.get(i+1)-1);
                map.put(i+2, map.get(i+2)-1);
                append.put(i+3, append.getOrDefault(i+3,0)+1);
            }else{
                return false;
            }
            map.put(i,map.get(i)-1);
        }
        return true;
    }
}

692. Top K Frequent Words
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();
        for(String word : words){
            freq.put(word,freq.getOrDefault(word,0)+1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a,b)-> (a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue())
        );
        //make a minHeap for frequency for size k, if size > k, poll the top, which is the smallest in pq
        for(Map.Entry<String,Integer> entry : freq.entrySet() ){
            pq.offer(entry);
            if(pq.size()>k) pq.poll();
        }
        while(!pq.isEmpty()){
            //insert to head because it will poll the smallest
            res.add(0,pq.poll().getKey());
        }
        return res;
    }
}