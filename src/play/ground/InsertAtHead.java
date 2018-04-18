package play.ground;

import leetCode.common.ListNode;

public class InsertAtHead {
    public void caller() {
        ListNode ans = new ListNode(0);

        for (int i=1; i<5; i++) {
            callee(ans, new ListNode(i));
        }

        System.out.println();
        System.out.println();
        System.out.println("============= Inside caller: When finished, ans is ============");
        ans.display();
    }

    public void callee(ListNode head, ListNode newNd) {
        System.out.println();
        System.out.println("============ Inside callee ============");
        System.out.print("Before insert: head is ");
        head.display();

        if (head == null) {
            newNd = head;
        } else {
            newNd.next = head;
            head = newNd;
        }

        System.out.print("After insert: head is ");
        head.display();

    }

    public static void main(String[] args) {
        InsertAtHead myclass = new InsertAtHead();
        myclass.caller();
    }
}
