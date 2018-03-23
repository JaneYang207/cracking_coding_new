package chap4.treesAndGraphs;


/**
 * 4.5 implement a function to determine if a binary tree is a BST
 * Method 1: in-order-traverse
 */
public class ValidateBST
{
	Integer previsitedValue = null;

	/**
	 * Method 1. in-order-traverse: when visit node, compare it with previously visited node
	 * NOTE: this is wrong if definition of BST allows duplicates.
	 */
	public boolean isBST(TreeNode nd) {
		if (nd == null) return true;
		return isBST(nd.left) && isLargerThanPrevious(nd) && isBST(nd.right);
	}

	/**
	 * Check if current node is larger than previous node
	 */
	protected boolean isLargerThanPrevious(TreeNode nd) {
		boolean ans = (previsitedValue == null) || nd.value > previsitedValue.intValue();
		previsitedValue = nd.value;
		return ans;
	}

	/**
	 * Method 2: in-order traverse: when visit node, check if node.left <= node < node.right
	 * NOTE: This is correct even BST allows duplicates
	 *
	 * In this method, in-order-traverse, pre-order-traverse, or post-order-traverse will also work.
	 */
	public boolean isBST2(TreeNode nd) {
		if (nd == null) return true;
		return isBST2(nd.left) && isCurrentNodeBST(nd) && isBST2(nd.right);
	}

	protected boolean isCurrentNodeBST(TreeNode nd) {
		if (nd.left == null && nd.right == null )
			return true;
		else if (nd.left != null && nd.right == null)
			return nd.left.value <= nd.value;
		else if (nd.left == null && nd.right != null)
			return nd.right.value > nd.value;
		else
			return nd.right.value > nd.value && nd.left.value <= nd.value;
	}

	/**
	 * Method 3: pre-order-traverse
	 * @param nd
	 * @return
	 */
	public boolean isBST3(TreeNode nd) {
		if (nd == null) return true;
		return isCurrentNodeBST(nd) && isBST3(nd.left) && isBST3(nd.right);
	}

	public boolean isBST4 (TreeNode nd) {
		if (nd == null) {
			return true;
		}

		return (nd.left == null || nd.left.value <= nd.value ) &&
			(nd.right == null || nd.right.value > nd.value ) &&
			isBST4(nd.left) && isBST4(nd.right);
	}

	public static void main(String[] args) {
		MinimalTreeFromSortedArray minimalTreeFromSortedArray = new MinimalTreeFromSortedArray();
		int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
		TreeNode root = minimalTreeFromSortedArray.create(arr);

		TreeNode root2 = new TreeNode(2);
		root2.left = new TreeNode(3);
		root2.right = new TreeNode(4);

		ValidateBST validateBST = new ValidateBST();
		if (validateBST.isBST4(root)) {
			System.out.println("Is BST");
		} else {
			System.out.println("Not BST");
		}
	}

}
