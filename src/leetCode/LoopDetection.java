package leetCode;

import leetCode.common.ListNode;

/**
 * 141. Linked List Cycle
 */
public class LoopDetection {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode faster = head;
        ListNode slower = head;

        while (faster.next != null) {
            // Wrong answer: faster and slower are always the same at the beggining, both equals to head
            // if (faster == slower) {

            // Infinite Loop: when input is like:
            if (faster != head && faster == slower) {
                return true;
            } else {
                faster = faster.next.next;
                slower = slower.next;
            }
        }

        // The following will throw null pointer exception: eg. when input 1 -> 2
        // while (faster.next != null) {
        while (faster != null && faster.next != null) {

            faster = faster.next.next;
            slower = slower.next;

            if (faster == slower) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        LoopDetection myclass = new LoopDetection();

        ListNode head = new ListNode(1);
        head.next = head;
      //  head.next = new ListNode(2);

        boolean ans = myclass.hasCycle(head);

        System.out.println("ans is " + ans);
    }
}
