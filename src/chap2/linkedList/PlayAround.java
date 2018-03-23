package chap2.linkedList;

/**
 * Created by jyang on 8/12/17.
 */
public class PlayAround
{

	public static void main(String[] args)
	{
		CustomSingleLinkedListNode node1 = new CustomSingleLinkedListNode(1);
		CustomSingleLinkedListNode node2 = new CustomSingleLinkedListNode(1);
		CustomSingleLinkedListNode node3 = node1;

		CustomSingleLinkedListNode newNode = new CustomSingleLinkedListNode(2);
//		node1.append(2); node1.display();
//		node2.append(2); node2.display();

		node1.append(newNode); node1.display();
		node2.append(newNode); node2.display();

//		determineIfSame(node1, node2);
		determineIfSame(node1, node3);

		double a = 0.45;
		System.out.printf(" %.20f", 1.0/10);

	}

	public static void determineIfSame(CustomSingleLinkedListNode nd1,CustomSingleLinkedListNode nd2 ){
//		if(nd1.value == nd2.value && nd1.next == nd2.next){
		if(nd1 == nd2) {
			System.out.println("The two node are the same");
		} else {
			System.out.println("The two node are different");
		}
	}
}
