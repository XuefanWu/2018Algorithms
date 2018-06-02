495. Teemo Attacking
//Input: list of poison time and duration, OutPut: total poison time
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries==null || timeSeries.length == 0) return 0;
        int res = 0, start = timeSeries[0], end = timeSeries[0] +duration;
        for(int i = 1; i < timeSeries.length; i++){
            if(timeSeries[i] > end){
                res += end-start;
                start = timeSeries[i];
            }
            end = timeSeries[i]+duration;
        }
        res += end-start;
        return res;
    }
}

531. Lonely Pixel I
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] row = new int[m], col = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B'){
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(picture[i][j] == 'B' && row[i] == 1&& col[j] == 1) res++;
            }
        }
        return res;
    }
}

532. K-diff Pairs in an Array
class Solution {
    public int findPairs(int[] nums, int k) {
        int res = 0;
        if(nums == null || nums.length == 0|| k < 0) return res;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i,0)+1);
        }
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(k == 0){
                if(entry.getValue() >= 2){
                    res++;
                }
            }else{
                if(map.containsKey(entry.getKey()+k)){
                    res++;
                }
            }
        }
        return res;
    }
}
