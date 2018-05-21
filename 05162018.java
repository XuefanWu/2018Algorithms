287. Find the Duplicate Number
class Solution {
    //like Linked List Cycle II
    //https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length <=1) return -1;
        int slow = nums[0], fast = nums[nums[0]];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

289. Game of Life
//https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
class Solution {
    public void gameOfLife(int[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int lives = getLives(board,i,j,m,n);
                if(board[i][j] == 1 && lives >= 2 && lives <= 3){//01 -> 11 
                    board[i][j] = 3;
                }
                if(board[i][j] == 0 && lives == 3){//00 -> 10
                    board[i][j] = 2;
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                board[i][j] >>= 1;
            }
        }
    }

    public int getLives(int[][] board, int i, int j, int m, int n){
        int res = 0;
        for(int x = Math.max(i-1,0); x <= Math.min(i+1,m-1); x++){
            for(int y = Math.max(j-1,0); y <= Math.min(j+1,n-1);y++){
                res += board[x][y] & 1;
            }
        }
        res -= board[i][j]&1;
        return res;
    }
}

370. Range Addition
class Solution {
    //record inc in startIndex and -inc in endIndex+1 to stop the increasement
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        if(updates == null) return res;
        for(int[] update : updates){
            int startIndex = update[0];
            int endIndex = update[1];
            int inc = update[2];

            res[startIndex]+=inc;
            if(endIndex < length-1) res[endIndex+1]-=inc;
        }
        for(int i = 0; i < length-1; i++){
            res[i+1] += res[i];
        }
        return res;
    }
}

414. Third Maximum Number
class Solution {
    public int thirdMax(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }
}

442. Find All Duplicates in an Array
class Solution {
    //flip the value of nums[index] to minus, which index = Math.abs(nums[i])-1
    //b/c all original values in nums >=1 , so check if the value of one specific index < 0, mean that value appears twice
    //and also b/c we may filp the value before, we need to get Math.min(value)
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        for(int i : nums){
            int index = Math.abs(i) - 1;
            if(nums[index] < 0) res.add(Math.abs(i));
            nums[index] = -nums[index];
        }
        return res;
    }
}

448. Find All Numbers Disappeared in an Array
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        for(int i : nums){
            int index = Math.abs(i)-1;
            if(nums[index] > 0) nums[index] = -nums[index];
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0) res.add(i+1);
        }
        return res;
    } 
}

485. Max Consecutive Ones
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0, cnt = 0;
        for(int i : nums){
            if(i == 1){
                cnt++;
                res = Math.max(res,cnt);
            }else{
                cnt = 0;
            }

           // res = Math.max(res, cnt = i == 0? 0 : cnt+1);
            
        }
        return res;
    }
}