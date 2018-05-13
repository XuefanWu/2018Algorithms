//LinkedList
2. Add Two Numbers
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode head = pre;
        int carry = 0;
        while(l1!=null || l2!=null || carry != 0){
            ListNode cur = new ListNode(0);
            int sum = (l2 == null ? 0 : l2.val) + (l1 == null ? 0: l1.val) + carry;
            cur.val = sum %10;
            carry = sum/10;
            pre.next = cur;
            pre = cur;

            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }
        return head.next;
    }
}


19. Remove Nth Node From End of List
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy, fast = dummy;
        dummy.next = head;
        for(int i = 0; i <= n; i++){
            fast = fast.next;
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}

21. Merge Two Sorted Lists
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}

24. Swap Nodes in Pairs
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}

206. Reverse Linked List
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}


25. Reverse Nodes in k-Group
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k < 2) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode tail = dummy, pre = dummy, temp;
        int count;
        while(true){
            count = k;
            while(count > 0 && tail != null){
                tail = tail.next;
                count--;
            }
            if(tail == null) break;
            head = pre.next;
            //pre-temp-...-tail
            //delete temp and insert after tail
            //pre-...-tail-temp
            //dummy-1-2-3-4-
            //dummy-2-3-4-1
            //dummy-3-4-2-1
            //dummy-4-3-2-1
            while(pre.next!= tail){
                temp = pre.next;//assign
                pre.next = temp.next;//delete
                temp.next = tail.next;//insert
                tail.next = temp;//insert
            }
            tail = head;
            pre = head;
        }
        return dummy.next;
    }
}

61. Rotate List
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return head;
        }
        int len = 1;
        ListNode newHead = head;
        ListNode tail = head;
        while(tail.next!=null){
            len++;
            tail = tail.next;
        }
        tail.next = head; //creat circle
        for(int i = 0; i < len-k%len; i++){
            tail = tail.next;
        }
        newHead = tail.next;//find new head
        tail.next = null;//break circle
        return newHead;
    }
}