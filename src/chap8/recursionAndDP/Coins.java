package chap8.recursionAndDP;

import java.util.HashMap;
import java.util.Map;

/**
 * 8.11 Coins:
 */
public class Coins {

	// wrong solution
//	int f(int n, int[] memo) {
//		if (n < 0) return 0;
//
//		if (memo[n] == 0) {
//			memo[n] = f(n-1, memo) + f(n-5, memo) + f(n-10, memo) + f(n-25, memo);
//		}
//		return memo[n];
//	}
//
//	int coins (int n) {
//		int[] memo = new int[n+1];
//		memo[0] = 1;
//		return f(n, memo);
//	}


	int makeChange(int[] coins, int n) {
		if (n < 0) return -1; // invalid input

		// initialize memo map
		Map<MyKey, Integer> memo = new HashMap<>();
		return doMakeChange(coins, n, 0, memo);
	}

	/**
	 *
	 * @param coins [25, 10, 5, 1]
	 * @param n total amount of money
	 * @param index
	 * @param memo
	 * @return
	 */
	int doMakeChange(int[] coins, int n, int index, Map<MyKey, Integer> memo) {
		// if coins is penny, return 1
		if (index == coins.length - 1)
			return 1;

		// check memo
		MyKey key = new MyKey(n, index);

		if ( !memo.containsKey(key) ){
			int m = n / coins[index];
			int ans = 0;

			for (int i = m; i >= 0; i--) {
				ans += doMakeChange(coins, n - i * coins[index], index+1, memo);
			}

			memo.put(key, ans);
		}

		return memo.get(key);
	}

	private class MyKey {
		int n;
		int index;

		public MyKey (int n, int index) {
			this.n = n;
			this.index = index;
		}
	}


	public static void main(String[] args) {
		Coins myclass = new Coins();
//		int ans = myclass.coins(6);
//		System.out.println("ans = " + ans);

		int ans = myclass.makeChange(new int[]{25, 10, 5, 1}, 16);
		System.out.println("ans = " + ans);
	}
}
