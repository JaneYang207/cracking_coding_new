package leetCode;

import leetCode.common.ListNode;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {

        // create empty "before" list, used to store all nodes smaller than x
        ListNode beforeStart = null;
        ListNode beforeEnd = null;

        // create empty "after" list, used to store all nodes larger or equal to x
        ListNode afterStart = null;
        ListNode afterEnd = null;

        ListNode cur = head;
        ListNode curNext;
        while (cur != null) {

            // Note: back up cur.next before cut off the link
            curNext = cur.next;

        // cut off current node from rest of other nodes, so we can put only this one node into "before" or "after" list
        cur.next = null;

        if (cur.val < x) { // go into "before" list
            if(beforeStart == null) {
                beforeStart = cur;
                beforeEnd = cur;
            } else {
                beforeEnd.next = cur;
                beforeEnd = cur;
            }

        } else { // go into "after" list
            if(afterStart == null) {
                afterStart = cur;
                afterEnd = cur;
            } else {
                afterEnd.next = cur;
                afterEnd = cur;
            }
        }
        // this is wrong: cur.next has already be set to null
        // cur = cur.next;

        cur = curNext;
    }

    // connect "before" list and "after" list
        if (beforeStart != null && beforeEnd != null) { // if all nodes are larger than x, "before" list is empty
        beforeEnd.next = afterStart;
        return beforeStart;
    } else {
        return afterStart;
    }
}

    public void display(ListNode head) {
        ListNode cur = head;
        while(cur != null) {
            System.out.print(cur.val + " --> ");

            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        PartitionList myclass =new PartitionList();

        ListNode head = new ListNode(1);
        head.next = new ListNode(1);


        myclass.partition(head, 0);

        myclass.display(head);
    }
}
