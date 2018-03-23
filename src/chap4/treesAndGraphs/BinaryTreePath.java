package chap4.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths
 */
public class BinaryTreePath
{
	public List<String> findAllPaths(TreeNode root) {
		if (root == null) {
			return null;
		}
		List<String> result = new ArrayList<>();
		doFindPath(root, Integer.toString(root.value), result);
		return result;
	}

	public void doFindPath(TreeNode nd, String path, List<String> result) {
		if (nd.left == null && nd.right == null) {
			result.add(path);
		}
		if (nd.left != null) {
			doFindPath(nd.left, path + " -> " + nd.left.value, result);
		}
		if (nd.right != null) {
			doFindPath(nd.right, path + " -> " +nd.right.value, result);
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		MinimalTreeFromSortedArray myClass = new MinimalTreeFromSortedArray();

		TreeNode root = myClass.create(0, 9, arr);

		BinaryTreePath  thisClass = new BinaryTreePath();
		List<String> result = thisClass.findAllPaths(root);

	}
}
