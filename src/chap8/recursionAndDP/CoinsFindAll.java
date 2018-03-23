package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * From FaceBook:
 * Find all combinations of coins which added up to a certain value
 */
public class CoinsFindAll {
	/**
	 *
	 * @param previous:
	 * @param value
	 * @param coins
	 * @param index
	 * @param ans
	 */
	void recf(int[] previous, int value, int[] coins, int index, List<int[]> ans)
	{
		// base condition
		if(index == coins.length-1) {
			previous[index] = value;

			// note: "previous" added to "ans" list here will be modified later. So should make a deep-copy of "previous" first.
			// ans.add(previous);

			ans.add(deepCopy(previous));
			return;
		}

		// this base condition is not correct
//		if(index > coins.length-1) {
//			ans.add(deepCopy(previous));
//			return;
//		}

		int max = value/coins[index];
		for (int i = max; i >= 0; i--) {
			previous[index] = i;
			recf(previous, value - coins[index]*i, coins, index+1, ans);
		}
	}

	void displayArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "   ");
		}
		System.out.println("");
	}

	void displayAll(List<int[]> ans) {
		for(int[] arr : ans) {
			displayArr(arr);
		}
	}

	int[] deepCopy(int[] arr) {
		int[] copy = new int[arr.length];
		for (int i=0; i<arr.length; i++) {
			copy[i] = arr[i];
		}
		return copy;
	}

	public static void main(String[] args) {
		CoinsFindAll myclass = new CoinsFindAll();

		int value = 131;
		int[] coins = new int[]{25, 20, 5, 1};
		int[] previous = new int[coins.length];
		List<int[]> ans = new ArrayList<>();

		myclass.recf(previous, value, coins, 0, ans);

		myclass.displayAll(ans);

	}
}
