package chap4.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.8 Given a binary tree, find lowest common ancestor of two nodes
 */
public class BTCommonAncestor {

	public int solution2(TreeNode root, int p, int q) {
		List<Integer> path1 = new ArrayList<>();
		List<Integer> path2 = new ArrayList<>();

		// both p and q exist in the tree
		if ( findPath(root, p, path1) && findPath(root, q, path2)){
			for (int i = path1.size()-1, j = path2.size()-1; i >= 0 && j >= 0; i--, j--) {
				if (path1.get(i) == path2.get(j)) {
					return path1.get(i);
				}
			}
			return Integer.MAX_VALUE;
		} else {
			System.out.println("Error: Not all nodes exist in the tree");
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Find path from specific node "nd" to "val"
	 * @return true if there is a path (means val exist in the tree); return false, if val doesn't exist in the tree
	 */
	public boolean findPath(TreeNode nd, int val, List<Integer> ans) {
		// base condition
		if (nd == null) {
			return false;
		}

		if (nd.value == val || findPath(nd.left, val, ans) || findPath(nd.right, val, ans)) {
			// post-order visit: add to ans list after recursion is down, so the path would start from leave, end at root
			// If we want the ans list to start from root, end at leaves, need to do pre-order visit, but that one needs back-tracking (need to
			// remove the node from ans list if the node is not actually in the path)
			ans.add(nd.value);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * find node p or q in Tree nd
	 *
	 * if find none, return null
	 * if only find p, return p
	 * if only find q, return q
	 * if find both p and q, return nd itself (nd is the lowest common ancestor)
	 * @param nd
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode recf(TreeNode nd, int p, int q) {
		// base condition
		if (nd == null) {
			return null;
		}

		if (nd.value == p || nd.value == q) {
			return nd;
		}

		// recursion
		// find p or q in the left subtree
		TreeNode left = recf(nd.left, p, q);

		// The following is nice to have. Without it, function still works, just not efficient enough
		// if already found LCA in left children, no need to go right.
		// eg. 9, 8 LCA is 2
		if (left != null && left.value != p && left.value != q) {
			return left;
		}

		// find p or q in the right subtree
		TreeNode right = recf(nd.right, p, q);

		// post-order:
		// case 1. both null, return null
		if (left == null && right == null) {
			return null;
		}
		// case 2. both not null, indicates that current nd is the ancestor, return current nd
		else if (left != null && right != null) {
			return nd;
		}
		// case 3. only one is null, return the non-null one
		else {
			return (left != null) ? left : right;
		}
	}

	public void display(int p, int q, TreeNode ans) {
		System.out.println("LCA of " + p + " and " + q + " is " + ans.value);
	}

	public void display(int p, int q, int ans) {
		System.out.println("LCA of " + p + " and " + q + " is " + ans);
	}

	/**
	 *                1
	 *               / \
	 *              2   3
	 *             /\   /\
	 *            4 5  11 6
	 *           / /\
	 *          9 7 8
	 *            \
	 *            10
	 */
	public static void main(String[] args) {
		BTCommonAncestor myclass = new BTCommonAncestor();

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.left.left.left = new TreeNode(9);

		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(8);

		root.left.right.left.right = new TreeNode(10);


		root.right.left = new TreeNode(11);
		root.right.right = new TreeNode(6);


		int p1 = 9;
		int q1 = 6;
//		myclass.display(p1, q1, myclass.recf(root, p1, q1));
		myclass.display(p1, q1, myclass.solution2(root, p1, q1));


		int p2 = 9;
		int q2 = 8;
//		myclass.display(p2, q2, myclass.recf(root, p2, q2));
		myclass.display(p2, q2, myclass.solution2(root, p2, q2));


		int p3 = 7;
		int q3 = 8;
//		myclass.display(p3, q3, myclass.recf(root, p3, q3));
		myclass.display(p3, q3, myclass.solution2(root, p3, q3));


		// Todo: Bug 1 here:(see fix above)
		int p4 = 9;
		int q4 = 2;
//		myclass.display(p4, q4, myclass.recf(root, p4, q4));
		myclass.display(p4, q4, myclass.solution2(root, p4, q4));


		// Todo: Bug here, what if node not exist in the tree
		int p5 = 7;
		int q5 = 13;
//		myclass.display(p5, q5, myclass.recf(root, p5, q5));
		myclass.display(p5, q5, myclass.solution2(root, p5, q5));
	}
}
