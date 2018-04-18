package chap4.treesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4.12 Paths with sum:
 */
public class PathSum {

    /**
     * If want to count number of paths:
     * @param nd
     * @param map key: running sum; value: count of the running sum
     * @param runningSum previous running sum before hit the node
     * @param targetSum
     * @return
     */
    public int recf (TreeNode nd, Map<Integer, Integer> map, int runningSum, int targetSum) {
        // Base condition:
        if(nd == null) {
            return 0;
        }

        // Pre-order visit:
        // step 1. compute running sum, store it in the map
        int runningSumNew = nd.value + runningSum;
        if(map.get(runningSumNew) == null) {
            map.put(runningSumNew, 1);
        } else {
            map.put(runningSumNew, map.get(runningSum)+1);
        }

        // step 2. determine the target previous running sum which satisfy the following: runningSum - targetRunningSum = targetSum
        int targetRunningSum = runningSumNew - targetSum;
        // look up target running sum in the hashmap
        int count = ( map.get(targetRunningSum)== null ) ? 0 : map.get(targetRunningSum);


        // Recursion:
        int ans = count + recf(nd.left, map, runningSumNew, targetSum) + recf(nd.right, map, runningSumNew, targetSum);

        // Post Order: when finish visiting certain node (including all its children),
        // decrease the value of the <key, value> pair, (if value = 0 after decrease, remove the pair)
        if (map.get(runningSumNew) > 1) {
            map.put(runningSumNew, map.get(runningSum)-1);
        } else {
            map.remove(runningSumNew);
        }

        return ans;
    }


    /**
     * If want to print out all paths:
     * @param nd
     * @param map key: running sum; value: list of corresponding  (one running sum, multiple nodes)
     * @param runningSum previous running sum before hit the node
     * @param targetSum
     * @param ans
     */
    public void recf (TreeNode nd, Map<Integer, List<TreeNode>> map, int runningSum, int targetSum, List<Result> ans) {
        // Base condition:
        if(nd == null) {
            return;
        }

        // Pre-order visit:
        // step 1. compute running sum, store it in the map
        int runningSumNew = nd.value + runningSum;
        List<TreeNode> nodes = map.get(runningSumNew);
        if(nodes == null) {
            nodes = new ArrayList<>();
            nodes.add(nd);
            map.put(runningSumNew, nodes);
        } else {
            nodes.add(nd);
            map.put(runningSumNew, nodes);
        }

        // step 2. determine the target previous running sum which satisfy the following: runningSum - targetRunningSum = targetSum
        int targetRunningSum = runningSumNew - targetSum;
        // look up target running sum in the hashmap
        List<TreeNode> startNodes = map.get(targetRunningSum);
        if (startNodes != null && startNodes.size() > 0) {
            for(TreeNode startNode: startNodes) {
                ans.add(new Result(startNode, nd));
            }
        }


        // Recursion:
        recf(nd.left, map, runningSumNew, targetSum, ans);
        recf(nd.right, map, runningSumNew, targetSum, ans);

        // Post Order: when finish visiting certain node (including all its children),
        // decrease the value of the <key, value> pair, (if value = 0 after decrease, remove the pair)
        if ( nodes.size() > 1) {
            nodes.remove(nd);
            map.put(runningSumNew, nodes);
        } else {
            map.remove(runningSumNew);
        }

    }

    private class Result {
        TreeNode start;
        TreeNode end;

        public Result(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);

        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(-2);

        root.left.right.right = new TreeNode(2);

        root.right.right = new TreeNode(11);
        root.right.right.right = new TreeNode(7);
        root.right.right.right.right = new TreeNode(1);

        PathSum myclass = new PathSum();


        // count number of paths
        int ans = myclass.recf(root, new HashMap<>(), 0, 8);

        System.out.println("Total number of path is " + ans);

        // print all paths
        List<Result> ansList = new ArrayList<>();
        myclass.recf(root, new HashMap<>(), 0, 8, ansList);
        for(Result result : ansList) {
            System.out.println("From " + result.start.value + " to " + result.end.value);
        }

    }
}
