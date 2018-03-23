package chap10.sortingAndSearching;

/**
 * 8.5 Recursive Multiply
 */
public class RecursiveMultiply {

	int fun(int m, int n) {
		int larger, smaller;
		if (m>n) {
			larger = m;
			smaller = n;
		} else {
			larger = n;
			smaller = m;
		}
		return funHelpler(larger, smaller);
	}

	int funHelpler(int m, int n) {
		if (n == 0) return 0;
		if (n == 1) return m;

		int left = fun(m, n >> 1);
		if (n % 2 == 0) {
			return left + left;
		} else {
			return left + left + m;
		}
	}


	public static void main(String[] args) {
		RecursiveMultiply myclass = new RecursiveMultiply();
		int ans = myclass.fun(11, 10);
		System.out.println("ans = " + ans);
	}
}
