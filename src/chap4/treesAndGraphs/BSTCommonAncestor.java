package chap4.treesAndGraphs;


/**
 * 4.8 Given a binary search tree, find LCA (lowest common ancestor) of two nodes
 */
public class BSTCommonAncestor {
	/**
	 * Go down from root to leaves
	 * Check if p, q are on different side of a given node
	 */
	public TreeNode func(TreeNode root, int p, int q) {
		TreeNode cur = root;
		TreeNode parent = null;

		while (cur != null) {
			// case 1. if p, q are on different side of current node, then current node is the LCA
			if ( (cur.value > p && cur.value < q) || (cur.value < p && cur.value > q)) {
				return cur;
			}

			// special case: if the current node is p or q, then parent of current node is the LCA
			if (cur.value == p || cur.value == q) {
				return parent;
			}

			// case 2. if both smaller, go left
			if (cur.value > p && cur.value > q) {
				parent = cur;
				cur = cur.left;
			}

			// case 3. if both larger, go right
			else {
				parent = cur;
				cur = cur.right;
			}
		}

		return cur;

	}

	public static void main(String[] args) {
		MinimalTreeFromSortedArray minimalTreeFromSortedArray = new MinimalTreeFromSortedArray();
		int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
		TreeNode root = minimalTreeFromSortedArray.create(arr);

		BSTCommonAncestor myclass = new BSTCommonAncestor();
		TreeNode ans = myclass.func(root, 5, 7);

		System.out.println("LCA is " + ans.value);
	}
}
