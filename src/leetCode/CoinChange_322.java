package leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange_322 {
    public int coinChange(int[] coins, int amount) {
        // step 1. sort coins
        Arrays.sort(coins); // ascending order

        // step 2.
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> curAns = new ArrayList<>();
//        return recf(coins.length - 1, coins, amount, ans, curAns);
        return 0;
    }

//    private int recf(int i, int[] coins, int curAmount, List<List<Integer>> ans, List<Integer> curAns) {
//
////        if(i < 0 ) {
////            return curAmount == 0 ? 0 : -1;
////        }
//        if(i < 0 && curAmount != 0) {
//            return -1;
//        }
//
//        if(i < 0 && curAmount == 0) {
//            ans.add(curAns);
//        }
//
//        int div = curAmount / coins[i];
////        System.out.println("i = " + i+"  curAmount = " + curAmount + "   max div = " + div);
//
//        for (int j = div; j >= 0; j--) {
////            System.out.println("Before recf: j = " +j + "   curAmount = " + curAmount);
////            int returnVal = recf(i-1, coins, curAmount % coins[i]);
//            int returnVal = recf(i-1, coins, curAmount - j * coins[i]);
////            System.out.println("After recf: j = " + j +"    returnVal = " + returnVal + "   curAmount = " + curAmount);
//
//
//            // only keep trying next j if the "returnVal" is -1
//            if (returnVal != -1) {
////                return div + returnVal;
//                System.out.println("i = " + i + "   returnVal = " + returnVal + "   j = " + j);
//                return j + returnVal;
//            }
//        }
//
//        return -1;
//    }

    public static void main(String[] args) {
        CoinChange_322 myclass = new CoinChange_322();
//        int[] coins = new int[]{5, 3};
//        int amount = 19;
        int[] coins = new int[]{186,419,83,408};
        int amount = 6249;
//        int[] coins = new int[]{1};
//        int amount = 0;
        int ans = myclass.coinChange(coins, amount);
        System.out.println(ans);
    }
}
