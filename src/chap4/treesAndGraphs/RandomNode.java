package chap4.treesAndGraphs;

import java.util.Random;

/**
 * 4.11 Return a random node in a BST
 */
public class RandomNode {

  public static void main(String[] args) throws Exception {
    RandomNode myClass = new RandomNode();

    // instantiate an inner class
    MyBST bst = myClass.new MyBST();

    bst.insertInOrder(20);
    bst.insertInOrder(10);
    bst.insertInOrder(30);
    bst.insertInOrder(5);
    bst.insertInOrder(15);
    bst.insertInOrder(3);
    bst.insertInOrder(7);
    bst.insertInOrder(17);

    TreeNode nd = bst.findRandomNode();
    if (nd == null) {
      System.out.println("Unable to find random node.");
    } else {
      System.out.println("Found random node: " + nd.value);
    }
  }

  public class MyBST {
    TreeNode root;
    int size;
    int visit_counter;

    public MyBST() {
      this.size = 0;
      this.visit_counter = -1;
    }

    public void insertInOrder(int value) {
      TreeNode newNode = new TreeNode(value);

      // first node (root)
      if (this.root == null) {
        this.root = newNode;
        this.size++;
        return;
      }

      TreeNode cur = root;
      while (cur != null) {
        if (value > cur.value) {
          if (cur.right == null) {
            cur.right = newNode;
            size++;
            return;
          } else {
            cur = cur.right;
          }
        }
        else {
          if (cur.left == null) {
            cur.left = newNode;
            size++;
            return;
          } else {
            cur = cur.left;
          }
        }
      }
    }

    public TreeNode findRandomNode() {
      // Step 1. generate random number between [0, size)
      Random random = new Random();
      int targetIndex = random.nextInt(this.size);
      System.out.println("Generated random number: " + targetIndex);

      // Step 2. traverse BST until reach the targetIndex

//      Integer currentIndex = -1;
//      return findTargetNodeWrong(this.root, targetIndex, currentIndex);

//      visit_counter = -1;
//      return findTargetNode(this.root, targetIndex);

      int[] currentIndex = {-1};
      return findTargetNode2(this.root, targetIndex, currentIndex);
    }


    /**
     * Wrong solution: Cannot modify immutable object "Integer currentIndex"
     */
    public TreeNode findTargetNodeWrong(TreeNode nd, int targetIndex, Integer currentIndex) {
      if (nd == null) {
        return null;
      }

      // visit node
      currentIndex++;
      if (currentIndex == targetIndex) {
        return nd;
      }


      TreeNode left = findTargetNodeWrong(nd.left, targetIndex, currentIndex);

      // no need to go left if already found
      if (left != null) {
        return left;
      }

      TreeNode right = findTargetNodeWrong(nd.right, targetIndex, currentIndex);

      return right;
    }

    /**
     * Correct solution: modify class properties "this.visit_counter"
     */
    public TreeNode findTargetNode(TreeNode nd, int targetIndex) {
      if (nd == null) {
        return null;
      }

      // visit node
      visit_counter++;
      if (visit_counter == targetIndex) {
        return nd;
      }

      // recursion
      TreeNode left = findTargetNode(nd.left, targetIndex);

      // no need to go right if already found in left
      if (left != null) {
        return left;
      }

      TreeNode right = findTargetNode(nd.right, targetIndex);

      // return the non-null one
      return left == null ? right : left;
    }

    /**
     * Correct solution: modify non-immutable object "int[] currentIndex"
     */
    public TreeNode findTargetNode2(TreeNode nd, int targetIndex, int[] currentIndex) {
      if (nd == null) {
        return null;
      }

      // visit node
      currentIndex[0]++;
      if (currentIndex[0] == targetIndex) {
        return nd;
      }

      // recursion
      TreeNode left = findTargetNode2(nd.left, targetIndex, currentIndex);

      // no need to go left if already found
      if (left != null) {
        return left;
      }

      TreeNode right = findTargetNode2(nd.right, targetIndex, currentIndex);

      return right;
    }
  }

  public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
      this.value = value;
    }
  }


}
