package chap2.linkedList;

/**
 * 2.5 You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored
 * in reverse order, such that the 1's digit is at the end of the list. Write a function that adds the two numbers and
 * returns the sum as a linked list.
 * Example: (7 -> 1 -> 6) + (5 -> 9 -> 2). That's 617 + 295
 * output: 2 -> 1 - 9. That's 912.
 */
public class SumLists
{
	public static CustomSingleLinkedListNode addTwoLists(CustomSingleLinkedListNode head1, CustomSingleLinkedListNode head2) {
		CustomSingleLinkedListNode resultHead = null;

		CustomSingleLinkedListNode curr1 = head1;
		CustomSingleLinkedListNode curr2 = head2;
		CustomSingleLinkedListNode currResult = null;

		int carry = 0;
		int sum = 0;

		while (curr1 != null || curr2 != null) {

			// step1. sum values
			if (curr1 != null) {
				sum += curr1.value;
			}

			if (curr2 != null) {
				sum += curr2.value;
			}

			sum += carry;

			carry = (sum > 9) ? 1 : 0;
			sum = (sum > 9) ? sum-10 : sum;


			// step2. create new node
			CustomSingleLinkedListNode newNode = new CustomSingleLinkedListNode(sum);
			newNode.next = null;


			// step3. add new node to result list
			if (resultHead == null) {
				resultHead = newNode;
				currResult = resultHead;
			} else {
				currResult.next = newNode;
				currResult = currResult.next;
			}



			// change iteration conditions
			if (curr1 != null) {
				curr1 = curr1.next;
			}

			if (curr2 != null) {
				curr2 = curr2.next;
			}
			sum = 0;

		}

		return resultHead;
	}

	public static void main(String[] args) {
		CustomSingleLinkedList list1 = new CustomSingleLinkedList();
		list1.addAtHead(6);
		list1.addAtHead(1);
		list1.addAtHead(7);
		list1.display();

		CustomSingleLinkedList list2 = new CustomSingleLinkedList();
		list2.addAtHead(8);
		list2.addAtHead(7);
		list2.addAtHead(9);
		list2.addAtHead(5);
		list2.display();

		CustomSingleLinkedListNode resultListHead = addTwoLists(list1.head, list2.head);
		resultListHead.display();
	}
}
