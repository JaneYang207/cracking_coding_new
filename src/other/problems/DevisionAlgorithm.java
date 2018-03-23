package other.problems;

/**
 * From FB:
 * Design an algorithm to compute a/b, return the quotient and remainder.
 * You are not allowed to use "/" or "%", but it's ok to use ">>" and "<<"
 * eg. 11/3, return 4 and 1
 */
public class DevisionAlgorithm {

	/**
	 * Way 1: Keep substract b from a, until the remainder < b
	 * eg. 13 - 4 = 9;
	 *     9 - 4 = 5;
	 *     5 - 4 = 1; (1 < 4 end)
	 * Time Complexity: O(quotient)
	 * @return
	 */
	Result devide1(int a, int b) {
		if (b == 0) {
			System.out.println("Illegal input. The number be devided cannot be zero");
			return null;
		}

		if (a == 0) {
			return new Result(0, 0);
		}

		int remainder = a; int quotient = 0;
		while (remainder > b) {
			quotient++;
			remainder -= b;
		}

		return new Result(quotient, remainder);
	}

	/**
	 * Way 2. Binary Search:
	 * scope of possible quotient should be between [1, a]
	 * @return
	 */
	Result devide2(int a, int b) {
		int left = 1;
		int right = a;

		int mid = 0;
		int remainder = 0;

		while (left <= right) {
			mid = (left + right) >> 1; // (1+a)/2

			remainder = a - (mid * b);

			// if "mid" is already the one
			if (remainder < b && remainder >= 0) {
				// break;
				return new Result(mid, remainder);
			}

			// go left
			if (remainder < 0) {
				right = mid - 1;
			}
			// go right
			else if (remainder > 0) {
				left = mid + 1;
			}
		}

		return new Result(mid, remainder);
	}

	private class Result {
		private int quotient;
		private int remainder;

		public Result(int quotient, int remainder) {
			this.quotient = quotient;
			this.remainder = remainder;
		}

		public void display() {
			System.out.println("quotient = " + quotient + "    remainder = " + remainder);
		}
	}

	public static void main(String[] args) {
		DevisionAlgorithm myclass = new DevisionAlgorithm();
		int a = 13;
		int b = 4;
//		Result ans = myclass.devide1(a, b);
		Result ans = myclass.devide2(a, b);
		if (ans != null) {
			ans.display();
		}
	}
}
