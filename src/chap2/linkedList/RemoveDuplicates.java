package chap2.linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * 2.1 Remove duplicated from unsorted linked list
 */
public class RemoveDuplicates
{

	/**
	 * Remove duplicates in a list by using set.
	 * Time O(N), Memory O(N)
	 * @param list
	 * @return
	 */
	public static CustomSingleLinkedList removeDuplicateByUsingSet(CustomSingleLinkedList list) {
		if (list.head == null) {
			System.out.println("the list is empty");
			return list;
		}

		Set<Integer> set = new HashSet<Integer>();
		set.add(list.head.value);  // NOTE: don't forget this. When you do delete by COMPARING CURRENT.NEXT, need to handle the head separately

		CustomSingleLinkedListNode current = list.head;

		CustomSingleLinkedListNode originalCurrentNext;
		while(current.next != null) {

			// remove if already exist in hashset
			if(set.contains(current.next.value)) {
				originalCurrentNext = current.next;
				current.next = current.next.next;
				originalCurrentNext.next = null;
			} else {
				set.add(current.next.value);

				// NOTE: should only increase current when not equal. Because When current.next.value = comparedTo.value, the original
				// current.next was removed, so the current's new next is actually the next of the current's original next, thus no need
				// to increase current when current.next.value = comparedTo.value ;
				current = current.next;
			}

			// NOTE: This is completely wrong. It fails when there are continuous duplicates. eg. 1 -> 2 -> 3 -> 2 -> 2 -< 4
//			if (current.next != null) { // NOTE: don't forget this!!!! otherwise, will get exception when the duplicate is at the
//				                        // end of the list. eg. 1 -> 2 -> 1
//				current = current.next;
//			}
		}

		return list;
	}

	/**
	 * Remove by define a runner
	 * @param list
	 * @return
	 */
	public static CustomSingleLinkedList removeDuplicatesByUsingRunner(CustomSingleLinkedList list) {
		if (list.head == null) {
			System.out.println("the list is empty");
			return list;
		}

		CustomSingleLinkedListNode compareTo = list.head;
		CustomSingleLinkedListNode current;

		CustomSingleLinkedListNode originalCurrentNext;

		while(compareTo.next != null) {
			current = compareTo;

			while (current.next != null) {

				if (current.next.value == compareTo.value) {
					originalCurrentNext = current.next;
					current.next = current.next.next;
					originalCurrentNext.next = null;
				} else {
					current = current.next;
				}

//				if (current.next != null) {
//					current = current.next;
//				}
			}

			compareTo = compareTo.next;
		}

//		while(compareTo != null) {
//			current = compareTo;
//
//			while (current.next != null) {
//
//				if (current.next.value == compareTo.value) {
//					originalCurrentNext = current.next;
//					current.next = current.next.next;
//					originalCurrentNext.next = null;
//				} else {
//					current = current.next;
//				}
//			}
//
//			compareTo = compareTo.next;
//		}

		return list;
	}

	public static void main(String[] args) {
		CustomSingleLinkedList list = new CustomSingleLinkedList();
		list.addAtHead(2);
		list.addAtHead(2);
		list.addAtHead(3);
		list.addAtHead(2);
		list.addAtHead(1);
		list.addAtHead(2);
		list.addAtHead(5);
		list.display();

		RemoveDuplicates.removeDuplicateByUsingSet(list);
//		RemoveDuplicates.removeDuplicatesByUsingRunner(list);
		list.display();

	}
}
