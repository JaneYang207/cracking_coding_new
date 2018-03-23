package chap4.treesAndGraphs;

/**
 * Basic: get height of a specific node in a binary tree
 *
 * eg.             A
 *                /\
 *               B C
 *              /  /\
 *             D  E F
 *             \
 *             G
 *
 *   G height: 1
 *   B height: 3
 *   C height: 2
 *   if height = 0, means the node doesn't exist in the tree
 */
public class GetHeight {

	public int recf(TreeNode nd, int target) {

		if (nd == null) {
			return 0;
		}

		// already found the target node
		if (nd.value == target) {
			return getHeight(nd);
		}

		// try to find target node in left
		int left = recf(nd.left, target);

		if (left != 0)  // target node is found in left
			return left;
		else {
			return recf(nd.right, target); // try to find node in right if it's not found in left
		}

	}


	public int getHeight(TreeNode nd) {
		if (nd == null) {
			return 0;
		}

		return Math.max(getHeight(nd.left), getHeight(nd.right)) + 1;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);

		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(3);
		root.left.right.left = new TreeNode(4);

		root.right.right = new TreeNode(6);
		root.right.right.left = new TreeNode(8);

		GetHeight myclass = new GetHeight();
		int ans = myclass.recf(root, 10);

		System.out.println("ans = " + ans);
	}


}
