package leetCode;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber_202 {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int cur = n;
        int squareSum;
        while(true) { // loop condition?
            squareSum = computeSquareSum(cur);

            if(squareSum == 1) {
                return true;
            } else if (set.contains(squareSum)) {
                return false;
            } else {
                set.add(squareSum);
                cur = squareSum;
            }
        }
    }

    private int computeSquareSum(int n) {
        int ans = 0;
        int cur = n;
        int digit;
        while (cur > 0) {
            digit = cur % 10;
            ans += digit * digit;
            cur = cur / 10;
        }

        return ans;
    }
}
