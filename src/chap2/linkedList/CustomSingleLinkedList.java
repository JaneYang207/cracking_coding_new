package chap2.linkedList;

/**
 * Created by jyang on 5/3/17.
 */
public class CustomSingleLinkedList
{
	CustomSingleLinkedListNode head;

	public CustomSingleLinkedList() {

	}


	public void addAtHead(int value) {
		CustomSingleLinkedListNode newNode = new CustomSingleLinkedListNode(value);
		newNode.next = head;
		head = newNode;
	}

	public void display() {
		CustomSingleLinkedListNode current = head;
		System.out.println("The linked list is: ");
		while(current != null) {
			System.out.print(current.value+" -> ");
			current = current.next;
		}
		System.out.println("");
	}

}
