package chap4.treesAndGraphs;

/**
 *  Basic: get level of a specific node in a binary tree
 *
 *   eg.           A       ---- level 0
 *                /\
 *               B C       ---- level 1
 *              /  /\
 *             D  E F      ---- level 2
 *             \
 *             G           ---- level 3
 *
 *   if height = -1 , means the node doesn't exist in the tree
 */
public class GetLevel {
	public int recf(TreeNode nd, int value, int level) {
		if (nd == null) {
			return -1;
		}

		// found the node
		if (nd.value == value) {
			return level;
		}

		// go left
		int left = recf(nd.left, value, level + 1);

		if (left != -1) {
			return left; 		// found in left
		}

		// go right
		else {
			return recf(nd.right, value, level + 1);
		}
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

		GetLevel myclass = new GetLevel();
		int ans = myclass.recf(root, 3, 0);

		System.out.println("ans = " + ans);
	}
}
