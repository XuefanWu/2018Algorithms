41. First Missing Positive
class Solution {
    // The key here is to use swapping to keep constant space and also make use of the length of the array, 
    // which means there can be at most n positive integers. 
    // So each time we encounter an valid integer, find its correct position and swap. Otherwise we continue.
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while(i < nums.length){
            if(nums[i] == i+1 || nums[i]<=0 || nums[i]>nums.length) i++;
            else if(nums[nums[i]-1] != nums[i]) swap(i,nums[i]-1,nums);
            else i++;
        }
        i = 0;
        while(i < nums.length && nums[i] == i+1) i++;
        return i+1;
    }
    void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
        int i = 0;
        for(int reach = 0; i < nums.length && i <= reach; i++){
            reach = Math.max(reach,i+nums[i]);
        }
        return i == nums.length;
    }
}

48. Rotate Image
class Solution {
    public void rotate(int[][] matrix) {
        //对角线
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix[0].length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //竖着翻转
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-j-1] = temp;
            }
        }
    }
}

54. Spiral Matrix
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int rowStart = 0, rowEnd = matrix.length-1, colStart = 0, colEnd = matrix[0].length-1;
        while(rowStart <= rowEnd && colStart <= colEnd){
            //right
            for(int i = colStart; i <= colEnd; i++){
                res.add(matrix[rowStart][i]);
            }
            rowStart++;
            //down
            for(int i = rowStart; i <= rowEnd; i++){
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            //left
            if(rowStart <= rowEnd){
                for(int i = colEnd; i>=colStart;i--){
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;
            //up
            if(colStart<=colEnd){
                for(int i = rowEnd; i >= rowStart; i--){
                    res.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }
        return res;
    }
}

56. Merge Intervals
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
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        intervals.sort((i1,i2)->(i1.start-i2.start));
        int start = intervals.get(0).start, end = intervals.get(0).end;
        for(Interval i : intervals){
            if(i.start <= end){
                end = Math.max(end,i.end);
            }else{
                res.add(new Interval(start,end));
                start = i.start;
                end = i.end;
            }
        }
        res.add(new Interval(start,end));
        return res;
    }
}