
59. Spiral Matrix II
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if(n==0) return matrix;
        int rowStart = 0, rowEnd = n-1, colStart = 0, colEnd = n-1;
        int num = 1;
        while(rowStart <= rowEnd && colStart <= colEnd){
            //right
            for(int i = colStart; i <= colEnd; i++){
                matrix[rowStart][i] = num++;
            }
            rowStart++;
            //down
            for(int i = rowStart; i <= rowEnd; i++){
                matrix[i][colEnd]=num++;
            }
            colEnd--;
            //left
            if(rowStart <= rowEnd){
                for(int i = colEnd; i>=colStart;i--){
                    matrix[rowEnd][i] = num++;
                }
            }
            rowEnd--;
            //up
            if(colStart<=colEnd){
                for(int i = rowEnd; i >= rowStart; i--){
                    matrix[i][colStart]=num++;
                }
            }
            colStart++;
        }
        return matrix;
    }
}


57. Insert Interval
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        while(i < intervals.size() && intervals.get(i).end < newInterval.start){
            res.add(intervals.get(i++));
        }
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval = new Interval(
                Math.min(newInterval.start, intervals.get(i).start),
                Math.max(newInterval.end,intervals.get(i).end)
            );
            i++;
        }
        res.add(newInterval);
        while(i < intervals.size()) res.add(intervals.get(i++));
        return res;
    }
}

66. Plus One
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n-1; i >=0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //10000
        int[] res = new int[n+1];
        res[0] = 1;
        return res;
    }
}

73. Set Matrix Zeroes
class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length, col0 = 1;
        //store state in the first col and row in the matrix
        //first round set state
        //second round set value to matrix
        //since col0 and row 0 may contain in the same cube matrix[0][0], use col0 to record
        for(int i = 0; i < row; i++){
            if(matrix[i][0] == 0) col0 = 0;
            for(int j = 1; j < col; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = matrix[i][0] = 0;
                }
            }
        }
        for(int i = row-1; i>=0; i--){
            for(int j = col-1; j>=1; j--){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            if(col0 == 0){
                matrix[i][0] = 0;
            }
        }
    }
}

80. Remove Duplicates from Sorted Array II
//remove dupliacte that every element appears at most twice
public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int n : nums){
            if(i < 2 || n > nums[i-2]){
                nums[i] = n;
                i++;
            } 
        }
        return i;
    }
}
