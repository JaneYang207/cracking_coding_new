package play.ground;

import leetCode.common.ListNode;

public class PadWithZero {

    public void padListWithZero() {
        ListNode head = new ListNode(1);

        ListNode newNode = new ListNode(0);

        int num =  2;

        while (num > 0) {
            newNode.next = head;
            head = newNode;
            num--;
        }

        head.display();
    }

    public static void main(String[] args) {
        PadWithZero myclass = new PadWithZero();
        myclass.padListWithZero();
    }


}
