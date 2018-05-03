 //Heap
 347. Top K Frequent Elements
 class Solution {
     //bucket Sort
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        List<Integer>[] bucket = new List[nums.length+1];
        for(int n : map.keySet()){
            int freq = map.get(n);
            if(bucket[freq] == null){
                bucket[freq] = new LinkedList<>();
            }
            bucket[freq].add(n);
        }
        List<Integer> res = new ArrayList<>();
        for(int i = bucket.length-1; i>0 && k > 0; i--){
            if(bucket[i]!=null){
                List<Integer> list = bucket[i];
                res.addAll(list);
                k -= list.size();
            }
        }
        return res;
    }

    //maxHeap O(nlgn)
    public List<Integer> topKFrequent(int[] nums, int k) {
         Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>((a,b) -> (b.getValue()-a.getValue()));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            maxHeap.add(entry);
        }
        List<Integer> res = new ArrayList<>();
        while(res.size() < k){
            Map.Entry<Integer,Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}

373. Find K Pairs with Smallest Sums
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0]+a[1]-b[0]-b[1]));
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        for(int i = 0; i < nums1.length && i < k; i++) pq.offer(new int[]{nums1[i],nums2[0],0});
        while(k > 0 && !pq.isEmpty()){
            k--;
            int[] temp = pq.poll();
            res.add(new int[]{temp[0],temp[1]});
            if(temp[2] == nums2.length-1) continue;
            pq.offer(new int[]{temp[0],nums2[temp[2]+1],temp[2]+1});
            
        }
        return res;
    }
}