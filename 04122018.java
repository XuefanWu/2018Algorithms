483. Smallest Good Base
// n = x^m + x^m-1 + ... + x + 1
// n-1 = x(x^m-1 + x^m-2 + ... + x + 1)
// n - x^m = x^m-1 + ... + x + 1
// => n-1 = x(n-x^m)
// n(x-1) = x^m+1-1
// n(x-1) > x^m+1 -1 ==> x is to big
// because of the range of n, m can only < 60
import java.math.*;
class Solution {
    public String smallestGoodBase(String nn) {
        long n = Long.parseLong(nn);
        long res = 0;
        for(int m = 60; m >=2; m--){
            long start = 2, end = n;
            while(start < end){
                long mid = start+(end-start)/2;
                BigInteger right = BigInteger.valueOf(mid);
                right = right.pow(m).subtract(BigInteger.ONE);
                BigInteger left = BigInteger.valueOf(n).multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));
                int compare = right.compareTo(left);
                if(compare < 0){
                    start = mid+1;
                }else if(compare > 0){
                    end = mid;
                }else{
                    res = mid;
                    break;
                }
            }
            if(res!=0) break;
        }
        return res+"";
    }
}

668. Kth Smallest Number in Multiplication Table
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int start = 1, end = m*n;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(!enough(mid,m,n,k)){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(enough(start,m,n,k)) return start;
        else return end;
    }

    public boolean enough(int x, int m, int n, int k){
        int cnt = 0;
        for(int i = 1; i <= m; i++){
            cnt += Math.min(x/i,n);
        }
        return cnt >= k;
    }
}

//Two Pointers
18. 4Sum
// class Solution {
//     public List<List<Integer>> fourSum(int[] nums, int target) {
//         List<List<Integer>> res = new ArrayList<>();
//         if(nums.length < 4) return res;
//         Arrays.sort(nums);
//         for(int i = 0; i < nums.length-3; i++){
//             if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3] > target) break; //first candidate too large
//             if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3] < target) continue; //first candidate too small
//             if(i > 0 && nums[i] == nums[i-1]) continue; //prevent duplicates
//             for(int j = i+1; i < nums.length-2; j++){
//                 if(nums[i]+nums[j]+nums[j+1]+nums[j+2] > target) break; //second candidate too large
//                 if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2] < target)continue; //second candidate too small
//                 if(j > i+1 && nums[j] == nums[j-1])continue; //prevents duplicate results in ans list
//                 int low = j+1, high = nums.length-1;
//                 while(low < high){
//                     int sum = nums[i]+nums[j]+nums[low]+nums[high];
//                     if(sum == target){
//                         res.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
//                         while(low < high && nums[low] == nums[low+1]) low++;
//                         while(low < high && nums[high] == nums[high-1]) high--;
//                         low++;
//                         high--;
//                     }
//                     else if(sum < target) low++;
//                     else high--;
//                 }
//             } 
//         }
//         return res;
//     }
// }

//cannot find where wrong, but upper code cannot be accepted while lower one can

public class Solution {
public List<List<Integer>> fourSum(int[] num, int target) {
    ArrayList<List<Integer>> ans = new ArrayList<>();
    if(num.length<4)return ans;
    Arrays.sort(num);
    for(int i=0; i<num.length-3; i++){
        if(num[i]+num[i+1]+num[i+2]+num[i+3]>target)break; //first candidate too large, search finished
        if(num[i]+num[num.length-1]+num[num.length-2]+num[num.length-3]<target)continue; //first candidate too small
        if(i>0&&num[i]==num[i-1])continue; //prevents duplicate result in ans list
        for(int j=i+1; j<num.length-2; j++){
            if(num[i]+num[j]+num[j+1]+num[j+2]>target)break; //second candidate too large
            if(num[i]+num[j]+num[num.length-1]+num[num.length-2]<target)continue; //second candidate too small
            if(j>i+1&&num[j]==num[j-1])continue; //prevents duplicate results in ans list
            int low=j+1, high=num.length-1;
            while(low<high){
                int sum=num[i]+num[j]+num[low]+num[high];
                if(sum==target){
                    ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
                    while(low<high&&num[low]==num[low+1])low++; //skipping over duplicate on low
                    while(low<high&&num[high]==num[high-1])high--; //skipping over duplicate on high
                    low++; 
                    high--;
                }
                //move window
                else if(sum<target)low++; 
                else high--;
            }
        }
    }
    return ans;
}
}

567. Permutation in String
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if(len1 > len2) return false;
        int[] arr = new int[26];
        for(int i = 0; i < len1; i++){
            arr[s1.charAt(i)-'a']++;
            arr[s2.charAt(i)-'a']--;
        }
        if(isZero(arr)) return true;
        for(int i = 0; i < len2-len1;i++){
            arr[s2.charAt(i)-'a']++;
            arr[s2.charAt(i+len1)-'a']--;
            if(isZero(arr)) return true;
        }
        return false;
    }

    public boolean isZero(int[] arr){
        for(int i = 0; i < 26; i++){
            if(arr[i]!=0) return false;
        }
        return true;
    }
}
713. Subarray Product Less Than K
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int product = 1;
        for(int i = 0, j = 0; i < nums.length; i++){
            product *= nums[i];
            while(j <= i && product >= k){
                product /= nums[j];
                j++;
            }
            res += i-j+1; // this is add a new element will add how many counts
        }
        return res;
    }
}

