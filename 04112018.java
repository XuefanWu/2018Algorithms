378. Kth Smallest Element in a Sorted Matrix
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int low = matrix[0][0];
        int high = matrix[matrix.length-1][matrix[0].length-1]+1;
        while(low < high){
            int mid = low+(high-low)/2;
            int count = 0, j = matrix[0].length-1;
            for(int i = 0; i < matrix.length; i++){
                while(j >=0 && matrix[i][j] > mid) j--;
                count+=(j+1);
            }
            if(count < k) low = mid+1;
            else high = mid;
        }
        return low;
    }
}

//heap solution comes with HEAP

436. Find Right Interval
//1. sort starts
//2. using binary search to get start for each end
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> starts = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            map.put(intervals[i].start,i);
            starts.add( intervals[i].start);
        }
        Collections.sort(starts);
        int[] res = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            int end = intervals[i].end;
            int start = binarySearch(starts,end);
            if(start < end){
                res[i] = -1;
            }else{
                res[i] = map.get(start);
            }
        }
        return res;
    }
    public int binarySearch(List<Integer> arr, int target){
        int start = 0, end = arr.size()-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(arr.get(mid) < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(arr.get(start) == target){
            return arr.get(start);
        }
        else{
            return arr.get(end);
        }
    }
}

441. Arranging Coins
class Solution {
    public int arrangeCoins(int n) {
        long lnN = (long)n;
        long start = 0, end = lnN;
        while(start+1<end){
            long mid = start+(end-start)/2;
            long cal = (mid+mid*mid)/2;
            if(cal <= n){
                start = mid;
            }else{
                end = mid;
            }
        }
        if((end+end*end)/2 <= n) return (int)end;
        else return (int)start;
    }
}

454. 4Sum II
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < B.length; j++){
                int sum = A[i]+B[j];
                if(map.containsKey(sum)){
                    map.put(sum,map.get(sum)+1);
                }else{
                    map.put(sum,1);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < C.length; i++){
            for(int j = 0; j < D.length; j++){
                int sum = -(C[i]+D[j]);
                if(map.containsKey(sum)){
                    res+=map.get(sum);
                }
            }
        }
        return res;
    }
}

475. Heaters
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = Integer.MIN_VALUE;
        for(int house : houses){
            int index = Arrays.binarySearch(heaters,house);
            //Arrays.binarySearch will return -index - 1 if target not exit, index is the insertion point of target
            if(index < 0) index = -(index+1);
            int dis1 = index-1 >= 0 ? house-heaters[index-1] : Integer.MAX_VALUE;
            int dis2 = index < heaters.length? heaters[index] -house : Integer.MAX_VALUE;
            res = Math.max(res,Math.min(dis1,dis2));
        }
        return res;
    }
}

658. Find K Closest Elements
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int index = binarySearch(arr,x);
        //int index = Arrays.binarySearch(arr,x);
        if(index < 0) index = -(index+1);
        int i = index-1, j = index;
        List<Integer> res = new ArrayList<>();
        while(k > 0){
            if(i < 0 || (j < arr.length && Math.abs(arr[i]-x) > Math.abs(arr[j]-x))) j++;
            else i--;
            k--;
        }
        for(int temp = i+1; temp < j; temp++){
            res.add(arr[temp]);
        }
        return res;
    }
    
    public int binarySearch(int[] arr, int target){
        int start = 0, end = arr.length-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(arr[mid] < target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(arr[start] > target) return start;
        else return end;
    }
}