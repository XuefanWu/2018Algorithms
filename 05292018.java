621. Task Scheduler
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        for(char c : tasks){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        maxHeap.addAll(map.entrySet());
        while(!maxHeap.isEmpty()){
            int k = n+1;
            List<Map.Entry> list = new ArrayList<>();
            while(k > 0 && !maxHeap.isEmpty()){
                Map.Entry<Character,Integer> cur = maxHeap.poll();
                cur.setValue(cur.getValue()-1);
                list.add(cur);
                k--;
                res++;
            }
            for(Map.Entry<Character,Integer> e : list){
                if(e.getValue()>0) maxHeap.add(e);
            }
            if(maxHeap.isEmpty()) break;
            if(k > 0){
                res+=k;
            }
        }
        return res;
    }
}

358. Rearrange String k Distance Apart
class Solution {
    //greedy, put the one with most cnt first
    //one pq to get the max count Character
    //maintain a size k queue to get make sure same character apart k position
    public String rearrangeString(String s, int k) {
        StringBuilder sb = new StringBuilder();
        Map<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }

        PriorityQueue<Map.Entry<Character,Integer>> maxHeap = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());

        Queue<Map.Entry<Character,Integer>> queue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        
        while(!maxHeap.isEmpty()){
            Map.Entry<Character,Integer> cur = maxHeap.poll();
            sb.append(cur.getKey());
            cur.setValue(cur.getValue()-1);
            queue.offer(cur);

            if(queue.size()<k){
                continue;
            }
            Map.Entry<Character,Integer> front = queue.poll();
            if(front.getValue()>0){
                maxHeap.offer(front);
            }
        }
        return sb.length() == s.length() ? sb.toString():"";
    }
}