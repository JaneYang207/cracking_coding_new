package leetCode;

import leetCode.common.ListNode;

import java.util.Stack;

/**
 * 455. add two lists (forward order)
 */
public class AddTwoLists {
    /**
     * use stack
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // defensive coding: handle empty list
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode ans = new ListNode(100);

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        // push all into stack
        ListNode cur1 = l1;
        while(cur1 != null) {
            s1.push(cur1.val);
            cur1 = cur1.next;
        }

        ListNode cur2 = l2;
        while(cur2 != null) {
            s2.push(cur2.val);
            cur2 = cur2.next;
        }

        // compute
        ListNode newNode;
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if(!s1.isEmpty()) {
                sum += s1.pop();
            }

            if (!s2.isEmpty()) {
                sum += s2.pop();
            }

            // value of new node should be "sum % 10";
            // add new node to ans list
            newNode = new ListNode(sum % 10);
            insertAtHead(ans, newNode);

//            System.out.println("========= ans is " + ans.val);

            // update carry
            sum = sum / 10;
        }

        // carry
        if (sum > 0) {
            insertAtHead(ans, new ListNode(sum));
        }

        return ans;
    }

    /**
     * use recursion
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode ans = null;

        // step 1. pad zero to shorter list
        int size1 = findListLength(l1);
        int size2 = findListLength(l2);

        int diff = Math.abs(size1 - size2);

        if(size1 < size2) {
            l1 = padListWithZero(l1, diff);
        } else {
            l2 = padListWithZero(l2, diff);
        }

        System.out.println("After padding: ");
        l1.display();
        l2.display();

        // step 2. calculate
        Result res = recf(l1, l2);

        // handle the last carry
        if (res.carry > 0) {
            ans = insertAtHead(res.head, new ListNode(res.carry));
        } else {
            ans = res.head;
        }

        return ans;
    }

    public Result recf(ListNode p1, ListNode p2) {
        // base condition
        if (p1 == null && p2 == null) {
            return new Result(null, 0);
        }

        Result res = recf(p1.next, p2.next);

        // compute sum
        int sum = p1.val + p2.val + res.carry;
        res.head = insertAtHead(res.head, new ListNode(sum % 10));
        res.carry = sum / 10;

        return res;
    }

    public int findListLength(ListNode list) {
        int size = 0;
        ListNode cur = list;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        return size;
    }

    public ListNode padListWithZero(ListNode head, int num) {
//        ListNode newNode = new ListNode(0);

        while (num > 0) {
            ListNode newNode = new ListNode(0);
            newNode.next = head;
            head = newNode;
            num--;
        }

        head.display();

        return head;
    }

    public ListNode insertAtHead(ListNode head, ListNode newNd) {
        if (head == null) {
            head = newNd;
        } else {
            newNd.next = head;
            head = newNd;
        }
        return head;
    }

    class Result {
        ListNode head;
        int carry;

        public Result(ListNode head, int carry) {
            this.head = head;
            this.carry = carry;
        }
    }

//    public void display(ListNode head) {
//        ListNode cur = head;
//        while(cur != null) {
//            System.out.print(cur.val + " --> ");
//
//            cur = cur.next;
//        }
//    }



    public static void main(String[] args) {
        AddTwoLists myclass = new AddTwoLists();

        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(6);
//        head1.next.next.next = new ListNode(3);

        ListNode head2 = new ListNode(0);
//        head2.next = new ListNode(6);
//        head2.next.next = new ListNode(4);

        ListNode ans = myclass.addTwoNumbers2(head1, head2);
        System.out.println("========= Ans is ============");
        ans.display();
    }
}
