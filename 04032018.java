4. Median of Two Sorted Arrays
class Solution {
    //Divide and Conquer
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int len = nums1.length+nums2.length;
    //     if(len%2==0){
    //         return (findKth(nums1,0,nums2,0,len/2)+findKth(nums1,0,nums2,0,len/2+1))/2.0;
    //     }
    //     return findKth(nums1,0,nums2,0,len/2+1);
    // }
//     public static int findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
//         if(start1>=nums1.length){
//             return nums2[start2+k-1];
//         }
//         if(start2>=nums2.length){
//             return nums1[start1+k-1];
//         }
//         if(k==1){
//             return Math.min(nums1[start1],nums2[start2]);
//         }
        
//         int halfK1 = start1+k/2-1<nums1.length? nums1[start1+k/2-1] : Integer.MAX_VALUE;
//         int halfK2 = start2+k/2-1<nums2.length? nums2[start2+k/2-1] : Integer.MAX_VALUE;
        
//         if(halfK1 < halfK2) return findKth(nums1, start1+k/2,nums2,start2, k-k/2);
//         else return findKth(nums1,start1, nums2,start2+k/2,k-k/2);
//     }
    
    
    //Binary Search
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length+nums2.length;
        if(len%2==0){
            return (findKth(nums1,nums2,len/2)+findKth(nums1,nums2,len/2+1))/2.0;
        }
        return findKth(nums1,nums2,len/2+1);
    }
    public static int findKth(int[] nums1, int[] nums2, int k){
        if(nums1.length == 0) return nums2[k-1];
        if(nums2.length == 0) return nums1[k-1];
        int start = Math.min(nums1[0],nums2[0]);
        int end = Math.max(nums1[nums1.length-1],nums2[nums2.length-1]);
        
        //find the first x that x >= k
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(countSmallerOrEqual(nums1,mid)+countSmallerOrEqual(nums2,mid)<k){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(countSmallerOrEqual(nums1,start) + countSmallerOrEqual(nums2,start) >= k){
            return start;
        }
        return end;
    }
    
    public static int countSmallerOrEqual(int[] nums, int target){
        //find the first index in nums which nums[index]>target
        int start = 0, end = nums.length-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]>target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(nums[start] > target) return start;
        if(nums[end] > target) return end;
        return nums.length;
        
    }
    
}


33. Search in Rotated Sorted Array
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
			return -1;
		}
		int start = 0, end = nums.length-1;
		while(start + 1 < end){
			int mid = start+(end-start)/2;
			if(nums[mid] == target){
				return mid;
			}
			if(nums[start] < nums[mid]){
				if(nums[start] <= target && target <= nums[mid]){
					end = mid;
				}else{
					start = mid;
				}
			}else{
				if(nums[mid] <= target && target <= nums[end]){
					start = mid;
				}else{
					end = mid;
				}
			}
		}
		if(nums[start] == target){
			return start;
		}
		if(nums[end] == target){
			return end;
		}
		return -1;
    }
}

34. Search for a Range
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
			return new int[]{-1,-1};
		}
		int start,end;
		int[] res = new int[2];
		
		//left
		start = 0;
		end = nums.length-1;	
		while(start + 1 < end){
			int mid = start+(end-start)/2;
			if(nums[mid] < target){
				start = mid;
			}else{
				end = mid;
			}
		}
		if(nums[start] == target){
			res[0] = start;
		}else if(nums[end] == target){
			res[0] = end;
		}else{
			res[0] = res[1] = -1;
			return res;
		}
		
		//right
		start = 0;
		end = nums.length-1;
		while(start + 1 < end){
			int mid = start+(end-start)/2;
			if(nums[mid] > target){
				end = mid;
			}else{
				start = mid;
			}
		}
		if(nums[end] == target){
			res[1] = end;
		}else if(nums[start] == target){
			res[1] = start;
		}else{
			res[0] = res[1] = -1;
			return res;
		}
		return res;
    }
}

50. Pow(x, n)
class Solution {
    public double myPow(double x, int n) {
        boolean isNeg = false;
		if(n<0){
			x = 1/x;
			isNeg = true;
			n = -(n+1); //avoid when n == Integer.MIN_VALUE
		}
		
		double res = 1, temp = x;
		
		while(n!=0){
			if(n%2 == 1){
				res *= temp;
			}
			temp *= temp;
			n/=2;
		}
		if(isNeg){
			res *= x;
		}
		return res;
    }
}

74. Search a 2D Matrix
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return false;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		
		int start = 0, end = row*col-1;
		
		while(start+1<end){
			int mid = start+(end-start)/2;
			int num = matrix[mid/col][mid%col];
			if(num < target){
				start = mid;
			}else{
				end = mid;
			}
		}
		if(matrix[start/col][start%col] == target) return true;
		if(matrix[end/col][end%col] == target) return true;
		return false;
    }
}
