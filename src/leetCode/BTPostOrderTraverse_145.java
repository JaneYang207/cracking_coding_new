package leetCode;

import leetCode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BTPostOrderTraverse_145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }

        Stack<Operation> stack = new Stack<>();

        stack.push(new Operation(0, root));
        while (!stack.isEmpty()) {
            // step 1. pop out
            Operation out = stack.pop();

            // step 2. do stuff based on out.operator
            if (out.operator == 0) {
                stack.push(new Operation(1, out.nd));
                if(out.nd.right != null) {
                    stack.push(new Operation(0, out.nd.right));
                }
                if (out.nd.left != null) {
                    stack.push(new Operation(0, out.nd.left));
                }
            } else {
                ans.add(out.nd.val);
            }
        }
        return ans;
    }

    public class Operation {
        int operator; // 0: recf; 1: visit
        TreeNode nd;

        public Operation(int operator, TreeNode nd) {
            this.operator = operator;
            this.nd = nd;
        }
    }

    public static void main(String[] args) {
        BTPostOrderTraverse_145 myclass = new BTPostOrderTraverse_145();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        myclass.postorderTraversal(root);
    }
}
