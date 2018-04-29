//Heap
23. Merge k Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode l1, ListNode l2){
                return l1.val-l2.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for(ListNode node : lists){
            if(node!=null){
                pq.offer(node);
            }
        }

        while(!pq.isEmpty()){
            tail.next = pq.poll();
            tail = tail.next;
            if(tail.next!=null){
                pq.offer(tail.next);
            }
        }
        return dummy.next;
    }
}


215. Kth Largest Element in an Array
    class Solution {
    // public int findKthLargest(int[] nums, int k) {
    //     if(nums == null || nums.length == 0 || k > nums.length) return 0;
    //     int res = 0;
    //     PriorityQueue<Integer> pq = new PriorityQueue<>();
    //     for(int i : nums){
    //         pq.offer(i);
    //     }
    //     while((nums.length-k+1)>0 && !pq.isEmpty()){
    //         res = pq.poll();
    //         k++;
    //     }
    //     return res;
    // }
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0,nums.length-1,nums.length-k+1);
    }

    public int partition(int[] nums, int low, int high){
        int i = low, j = high, pivot = nums[low];
        while(i!=j){
            while(nums[j] >= pivot && i < j){
                j--;
            }
            while(nums[i] <= pivot && i < j){
                i++;
            }
            if(i < j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[low] = nums[i];
        nums[i] = pivot;
        return i;
    }

    //find the kth smallest
    public int quickSelect(int[] nums, int low, int high, int k){
        if(k > 0 && k <= high-low+1){
            int index = partition(nums, low, high);
            if(index - low == k-1 ){
                return nums[index];
            }
            else if (index - low > k-1){
                return quickSelect(nums,low,index-1,k);
            }else{
                return quickSelect(nums, index+1, high, k-index+low-1);
            }
        }
        return Integer.MAX_VALUE;
    }

}

239. Sliding Window Maximum
// We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

// At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

// If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

// Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

// As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0|| k <= 0) return new int[0];
        int len = nums.length;
        int[] res = new int[len-k+1];
        int resIndex = 0;
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < len; i++){
            while(!deque.isEmpty() && deque.peek() < i-k+1){
                deque.poll();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            if(i >= k-1){
                res[resIndex++] = nums[deque.peek()];
            }
        }
        return res;
    }
}

263. Ugly Number
class Solution {
    public boolean isUgly(int num) {
        if(num == 0) return false;
        for(int i = 2; i < 6 && i > 0; i++){
            while(num % i == 0)
                num /= i;
        }
        return num == 1;
    }
}
// Just divide by 2, 3 and 5 as often as possible and then check whether we arrived at 1. Also try divisor 4 if that makes the code simpler / less repetitive.

