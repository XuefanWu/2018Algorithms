154. Find Minimum in Rotated Sorted Array II
class Solution {
    //worst scenario o(n)
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0, end = nums.length-1;
        while(start+1 < end){
            int mid = start+(end-start)/2; // if using (end+start)/2 may cause Integer overflow
            if(nums[mid] == nums[end]){
                end--;
            }else if(nums[mid] < nums[end]){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(nums[start] <= nums[end]){
            return nums[start];
        }else{
            return nums[end];
        }
    }
}

162. Find Peak Element
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length-1;
        while(start<end){
            int mid = start+(end-start)/2;
            if(nums[mid] < nums[mid+1]){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        return start;
    }
}

167. Two Sum II - Input array is sorted
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        if(numbers == null || numbers.length == 0) return res;
        int start = 0, end = numbers.length-1;
        while(start < end){
            int value = numbers[start] + numbers[end];
            if(value == target){
                res[0] = start+1;
                res[1] = end+1;
                break;
            }else if(value<target){
                start++;
            }else{
                end--;
            }
        }
        return res;
    }
}


29. Divide Two Integers
class Solution {
    // https://leetcode.com/problems/divide-two-integers/discuss/13407/Detailed-Explained-8ms-C++-solution
    // e.g. dividend = 15, division = 3,
    // 15-3 > 0
    // 15-6 > 0
    // 15-12 > 0 && 15-24 < 0
    // res += 4(12 = 3^4), we use a mul = 1 to keep track
    // dvd = 15-12 and recursively do the steps above
    public int divide(int dividend, int divisor) {
        if(divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int res = 0;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1:1; //^ is XOR, will return 1 if two are different
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        while(dvs <= dvd){
            long temp = dvs, mul = 1;
            while(dvd >= temp<<1){
                temp <<= 1;
                mul <<= 1;
            }
            dvd -= temp;
            res += mul;
        }
        return sign == 1? res:-res;

    }
}

209. Minimum Size Subarray Sum
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int i = 0, j = 0;
        int res = Integer.MAX_VALUE, sum = 0;
        while(j < nums.length){
            sum += nums[j];
            j++;
            while(sum >= s){
                res = Math.min(res, j -i);
                sum -= nums[i++];
            }
        }
        return res == Integer.MAX_VALUE? 0 : res;
    }
}

240. Search a 2D Matrix II
//start from right top corner
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int col = n-1, row = 0;
        while(col >=0 && row <= m-1){
            if(target == matrix[row][col]){
                return true;
            }else if(target < matrix[row][col]){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}

