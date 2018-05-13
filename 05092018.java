//linkedList
147. Insertion Sort List
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        ListNode pre = dummy;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            while(pre.next!=null && pre.next.val < cur.val){
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = dummy;
            cur = next;
        }
        return dummy.next;
    }
}

148. Sort List
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return  head;
        }
        ListNode pre = null, slow = head, fast = head;
        while(fast!=null && fast.next!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return merge(l1,l2);
    }
    ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0), cur = dummy;
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1!=null){
            cur.next = l1;
        }
        if(l2!=null){
            cur.next = l2;
        }
        return dummy.next;
    }
}

328. Odd Even Linked List
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return head;
        ListNode odd = head, even = head.next, evenHead = even;
        while(even!=null && even.next!=null){
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}

//array/string/math
1. Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target-nums[i])){
                res[1] = i;
                res[0] = map.get(target-nums[i]);
                return res;
            }
            map.put(nums[i],i);
        }
        return res;
    }
}

11. Container With Most Water
class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length-1;
        int res = 0;
        while(left < right){
            int h = Math.min(height[left],height[right]);
            res = Math.max(res, h * (right-left));
            while(height[left]<=h && left < right) left++;
            while(height[right]<=h && left < right) right--;
        }
        return res;
    }
}

26. Remove Duplicates from Sorted Array
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if(len < 2) return len;
        int res = 1;
        for(int i = 1; i < len ; i++){
            if(nums[i]!= nums[i-1]){
                nums[res++] = nums[i];
            }
        }
        return res;
    }
}

27. Remove Element
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int res = 0;
        for(int i = 0; i < len ; i++){
            if(nums[i]!= val){
                nums[res++] = nums[i];
            }
        }
        return res;
    }
}

31. Next Permutation
class Solution {
    public void nextPermutation(int[] nums) {
        int k = -1;
        if(nums == null || nums.length <2) return;
        for(int i = nums.length-2; i>=0; i--){
            if(nums[i]<nums[i+1]){
                k = i;
                break;
                
            }
        }
        if(k == -1){
            reverse(0, nums.length-1, nums);
            return;
        }
        int l = -1;
        for(int i = nums.length-1; i>k; i--){
            if(nums[i]>nums[k]){
                l = i;
                break;
               
            }
        }
        swap(k,l,nums);
        reverse(k+1,nums.length-1,nums);
    }
    void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }
    void reverse(int i, int j, int[] arr){
        if(i > j){
            return;
        }
        for(int k = i; k <=(i+j)/2; k++){
            swap(k,i+j-k,arr);
        }
    }
}
