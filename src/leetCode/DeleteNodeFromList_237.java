package leetCode;

import leetCode.common.ListNode;

public class DeleteNodeFromList_237 {
    public void deleteNode(ListNode node) {
        // step 1. copy value of node.next to node
        while (node.next != null) {
            node.val = node.next.val;
            node = node.next;
        }

        // step 2. set node to be null
        node = null;
    }

    public static void main(String[] args) {
        DeleteNodeFromList_237 myclass = new DeleteNodeFromList_237();
        ListNode head = new ListNode(0);
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(2);

        second.next = third;
        head.next = second;
        myclass.deleteNode(head);
        System.out.println("DONE");
    }
}
