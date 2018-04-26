package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange_PrintAll {
    public List<List<Integer>> coinChange(int[] coins, int amount) {
        // step 1. sort coins
        Arrays.sort(coins); // ascending order

        // step 2.
        List<List<Integer>> allAns = new ArrayList<>();
        List<Integer> curAns = new ArrayList<>();
        recf(coins.length - 1, coins, amount, allAns, curAns);
        return allAns;
    }

    private void recf(int i, int[] coins, int curAmount, List<List<Integer>> allAns, List<Integer> curAns) {
        if (i < 0) {
            if (curAmount == 0) {
                // add curAns to allAns
                allAns.add(deepCopy(curAns));
                displayList(curAns);
            }

            // back tracking
            System.out.println("remove last: " + curAns.get(curAns.size()-1));
//            curAns.remove(curAns.get(curAns.size()-1)); // remove first occurence of the specified object if present
            curAns.remove(curAns.size()-1); // remove object at the specified index

            System.out.println("======= list after remove: ");
            displayList(curAns);

            return;
        }

        int div = curAmount / coins[i];

        for (int j = div; j >= 0; j--) {
            // step 1. add j to curAns
            Integer toAdd = new Integer(j);
            curAns.add(toAdd);
//            curAns.add(j);
            System.out.println("add int: " + j);

            // step 2. recursion
            recf(i-1, coins, curAmount - j * coins[i], allAns, curAns);

            // step 3. always back tacking
            curAns.remove(toAdd);
//            curAns.remove(curAns.get(curAns.size()-1));
            System.out.println("remove int: " + j);
            System.out.println("======= list after remove: ");
            displayList(curAns);
        }
    }

    private List<Integer> deepCopy(List<Integer> input) {
        List<Integer> output = new ArrayList<>();
        for(int num : input) {
            output.add(num);
        }
        return output;
    }

    public static void displayList(List<Integer> list) {
        for (int num : list) {
            System.out.print(num + " --> ");
        }
        System.out.println();
    }

    public static void displayAllList(List<List<Integer>> lists) {
        for(List<Integer> list : lists) {
            displayList(list);
        }
    }

    public static void main(String[] args) {
        CoinChange_PrintAll myclass = new CoinChange_PrintAll();
//        int[] coins = new int[]{186,419,83,408};
//        int amount = 6249;
//        int[] coins = new int[]{1};
//        int amount = 0;

        int[] coins = new int[]{10, 5, 3};
        int amount = 36;
        List<List<Integer>> ans = myclass.coinChange(coins, amount);
        displayAllList(ans);

    }
}
