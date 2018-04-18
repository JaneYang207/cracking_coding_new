package leetCode;

import java.util.*;

public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> allAns = new ArrayList<>();
        int N = nums.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list;
        for(int i = 0; i < N; i++) {
            if(map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }

        int tofind;
        for(int row = 0; row < N; row++) {
            for(int col = row + 1; col < N; col++) {

                tofind = 0 - nums[row] - nums[col];
                System.out.println("row = " + row + "   col = " + col + "    tofind = " + tofind);

                // search the map
                if (map.containsKey(tofind)) {

                    List<Integer> possibles = map.get(tofind);

                    // exclude already added indexes "col", and "row"
                    for (int j = 0; j < possibles.size(); j++) {
                        int index = possibles.get(j);
                        System.out.println("index = " + index);
                        if (index != row && index != col) {
                            List<Integer> ans = new ArrayList<>();
                            ans.add(nums[row]); ans.add(nums[col]); ans.add(nums[index]);
                            System.out.println("added " + nums[row] + ",   " + nums[col] + ",   " + nums[index]);
                            allAns.add(ans);
                        }
                    }
                }
            }
        }

        return allAns;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> allAns = new ArrayList<>();

        int N = nums.length;

        // step 1. sort nums in order to de-duplicate
        Arrays.sort(nums);

        // step 2. traverse
        for(int row = 0; row < N; row++) {
            // skip duplicates
            if(row >= 1 && nums[row] == nums[row-1]) {
                System.out.println("skip row " + row);
                continue;
            }

            for(int col = row + 1; col < N; col++) {

                // skip duplictes
                if (col - 1 > row && nums[col] == nums[col-1]) {
                    System.out.println("skip col " + col);
                    continue;
                }

                for (int k = col + 1; k < N; k++) {
                    if (k-1 > col && nums[k] == nums[k-1]) {
                        System.out.println("skip k " + k);
                        continue;
                    }
                    System.out.println("row = " + row + "    col = " + col + "    k =" + k);
                    if (nums[row] + nums[col] + nums[k] == 0) {
                        List<Integer> ans = new ArrayList<>();
                        ans.add(nums[row]);
                        ans.add(nums[col]);
                        ans.add(nums[k]);
                        allAns.add(ans);
                        System.out.println("add " + nums[row] + "   " + nums[col] + "    " + nums[k]);
                    }
                }
            }
        }

        return allAns;
    }

    public List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null) {
            return null;
        }

        List<List<Integer>> allAns = new ArrayList<>();

        int N = nums.length;

        // step 1. sort
        Arrays.sort(nums);

        int i = 0;
        int j, k, targetSum, curSum;
        while (i < N - 3) {
            // de-duplicate
            if (i-1 >= 0 && nums[i] == nums[i-1]) {
                i++;
                System.out.println("skip index i = " + i + " the num skipped is " + nums[i]);
                continue;
            }

            targetSum = 0 - nums[i];

            j = i + 1;
            k = N - 1;
            while (j < k) {
                System.out.println("i = " + i + "    j = "+ j + "     k = " + k);
                // deduplicate
                if(j-1 >= i+1 && nums[j] == nums[j-1]) { // j - 1 might be out of scope (scope is [i+1, N-1])
                    System.out.println("skip index j = " + j + " the num skipped is " + nums[j]);
                    j++;
                }
                else if (k+1 <= N-1 && nums[k] == nums[k+1]) { // k+1 might be out of scope (scope is [i+1, N-1])
                    System.out.println("skip index k = " + k + " the num skipped is " + nums[k]);
                    k--;
                } else {
                    curSum = nums[j] + nums[k];

                    if (curSum > targetSum) {
                        k--;
                    } else if (curSum < targetSum) {
                        j++;
                    } else {
                        List<Integer> ans = new ArrayList<>();
                        ans.add(nums[i]); ans.add(nums[j]); ans.add(nums[k]);
                        System.out.println("add " + nums[i] + "   " + nums[j] + "   " + nums[k]);
                        allAns.add(ans);

                        j++;
                        k--;
                    }
                }

            }


            i++;
        }

        return allAns;

    }

    public static void main(String[] args) {
        ThreeSum_15 myclass = new ThreeSum_15();
        int[] nums = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        List<List<Integer>> ans = myclass.threeSum3(nums);
        System.out.println("");
    }
}
