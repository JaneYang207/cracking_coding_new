package play.ground;

/**
 * "==" operator vs. "equals()" method
 */
public class ReferenceTypeComparision {

	/**
	 * All classes in Java inherit from the Object class, directly or indirectly (See point 1 of this).
	 * The Object class has some basic methods like clone(), toString(), equals(),.. etc.
	 * We can override the equals method in our class to check whether two objects have same data or not.
	 */
	private static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) {
		TreeNode nd1 = new TreeNode(1);
		TreeNode nd2 = new TreeNode(1);

//		if(nd1 == nd2) {
//			System.out.println("nd1 and nd2 are equal");
//		} else {
//			System.out.println("Not equal");
//		}

		if(nd1.equals(nd2)) {
			System.out.println("nd1 and nd2 are equal");
		} else {
			System.out.println("Not equal");
		}

	}
}
