package leetCode;

import leetCode.common.ListNode;

/**
 * 83 Remove duplicates from sorted list
 */
public class RemoveDuplicates {
        public ListNode deleteDuplicates(ListNode head) {
//            if (head == null || head.next == null) {
//                return head;
//            }

            ListNode cur = head;
//            ListNode runner = head.next;
            ListNode runner = (head == null || head.next == null) ? null : head.next;

            // this will cause null pointer exception: for input list like "1 -> 1"
            // while (cur.next != null) {
            while (cur != null && cur.next != null) {
                if (runner.val != cur.val) {
                    cur = cur.next;
                    runner = runner.next;
                }
                else {
                    while (runner != null && runner.val == cur.val) {
                        runner = runner.next;
                    }
                    cur.next = runner;

                    cur = cur.next;
                }
            }

            return head;

        }

        public void display(ListNode head) {
            ListNode cur = head;
            while(cur != null) {
                System.out.print(cur.val + " --> ");

                cur = cur.next;
            }
        }

        public static void main(String[] args) {
            RemoveDuplicates myclass =new RemoveDuplicates();

            ListNode head = new ListNode(1);
            head.next = new ListNode(1);
//            head.next.next = new ListNode(2);


            myclass.deleteDuplicates(head);

            myclass.display(head);
        }
}
