//LinkedList
83. Remove Duplicates from Sorted List
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
        while(node!=null){
            if(node.next==null){
                break;
            }
            if(node.val == node.next.val){
                node.next = node.next.next;
            }else{
                node = node.next;
            }
        }
        return head;
    }
}

82. Remove Duplicates from Sorted List II
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur != null){
            while(cur.next!=null && cur.val == cur.next.val) cur = cur.next; //go to the last duplicate
            if(pre.next == cur) pre = pre.next;//no dup
            else{
                pre.next = cur.next; //jump the last duplicate
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}

86. Partition List
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode firstDummy = new ListNode(0);
        ListNode secondDummy = new ListNode(0);
        ListNode cur1 = firstDummy, cur2 = secondDummy;
        
        while(head != null){
            if(head.val < x){
                cur1.next = head;
                cur1 = head;
            }else{
                cur2.next = head;
                cur2 = head;
            }
            head = head.next;
        }
        cur1.next = secondDummy.next;
        cur2.next = null;
        return firstDummy.next;
    }
}

92. Reverse Linked List II
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 0; i < m-1; i++){
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;
        for(int i = 0; i < n-m ;i++){
            //一个一个插到pre后面
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }
}

141. Linked List Cycle
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }
}

142. Linked List Cycle II
//2k -k = nr
// let n = 1, k = r;
//  |<--------s------->| |<----------------------------r------------------------>|        
//  |<--------------------r----------------------->|
//  |<------------------------------------2r--------------------------------------  
//  s+r = len
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return head;
        ListNode fast = head, slow = head;
        boolean hasCycle= false;
        while(fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                hasCycle = true;
                break;
            }
        }
        if(!hasCycle) return null;
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}

138. Copy List with Random Pointer
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode node = head, next;
        while(node!=null){
            next = node.next;
            RandomListNode copy = new RandomListNode(node.label);
            node.next = copy;
            copy.next = next;
            node = next;
        }
        node = head;
        while(node!=null){
            if(node.random!=null){
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        node = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode copy, node2 = dummy;
        while(node != null){
            next = node.next.next;
            copy = node.next;
            node2.next = copy;
            node2 = copy;
            node.next = next;
            node = next;
        }
        return dummy.next;
    }
}

143. Reorder List
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode p1 = head, p2 = head;
        //find middle point
        while(p2.next!=null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode preMiddle = p1;
        ListNode preCur = p1.next;
        //reverse second half
        while(preCur.next!=null){
            ListNode cur = preCur.next;
            preCur.next = cur.next;
            cur.next = preMiddle.next;
            preMiddle.next = cur;
        }

        p1 = head;
        p2 = preMiddle.next;
        //insert
        while(p1!=preMiddle){
            preMiddle.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = preMiddle.next;
        }

    }
}