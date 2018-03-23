package chap2.linkedList;

/**
 * Created by jyang on 5/3/17.
 */
public class CustomSingleLinkedListNode
{
	public int value;
	public CustomSingleLinkedListNode next;

	public CustomSingleLinkedListNode(int value) {
		this.value = value;
		this.next = null;
	}

	public void display() {
		System.out.println("The linked list node is: ");
		CustomSingleLinkedListNode current = this;
		while(current != null) {
			System.out.print(current.value+" -> ");
			current = current.next;
		}
		System.out.println("");
	}

	public void append(CustomSingleLinkedListNode nd) {
		this.next = nd;
	}

	public void append(int value) {
		CustomSingleLinkedListNode newNode = new CustomSingleLinkedListNode(value);
		this.next = newNode;
	}

}
