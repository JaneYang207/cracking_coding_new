package leetCode.common;

public class ListNode {
    public int val;
    public ListNode next;


    public ListNode(int val) {
        this.val = val;
    }

    public void display() {
        ListNode cur = this;
        while(cur != null) {
            System.out.print(cur.val + " --> ");

            cur = cur.next;
        }
        System.out.println("");
    }

}
