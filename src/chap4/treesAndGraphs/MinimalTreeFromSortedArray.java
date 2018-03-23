package chap4.treesAndGraphs;

/**
 * Created by jyang on 8/29/17.
 */
public class MinimalTreeFromSortedArray
{
	public TreeNode create(int[] arr) {
		return create(0, arr.length-1, arr);
	}

	public TreeNode create(int lowerBound, int uppperBound, int[] arr) {
		if (lowerBound > uppperBound) {
			return null;
		}
//		else if (lowerBound == uppperBound) {
//			return new TreeNode(arr[lowerBound]);
//		}
		else {
			int middleIndex = (lowerBound + uppperBound) / 2;
			TreeNode nd = new TreeNode(arr[middleIndex]);
			nd.left = create(lowerBound, middleIndex-1, arr);
			nd.right = create(middleIndex + 1, uppperBound, arr);
			return nd;
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		MinimalTreeFromSortedArray myClass = new MinimalTreeFromSortedArray();

		TreeNode root = myClass.create(0, 9, arr);

		System.out.println("done");

	}
}
