package leetCode;

import leetCode.common.ListNode;

public class ReverseList_206 {
    public ListNode reverseList(ListNode head) {
        ListNode ans = null;
        return recf(head, ans);
    }
    private ListNode recf(ListNode cur, ListNode ans) {
        if(cur == null) {
            return ans;
        }

        ListNode newNd = new ListNode(cur.val);
        if(ans == null) {
            ans = newNd;
        } else {
            newNd.next = ans;
            ans = newNd;
        }

        return recf(cur.next, ans);
    }

    public static void main(String[] args) {
        ReverseList_206 myclass = new ReverseList_206();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        ListNode ans = myclass.reverseList(head);

        System.out.println("Calculated");
    }
}
