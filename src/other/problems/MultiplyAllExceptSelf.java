package other.problems;

/**
 * From FB:
 * Given an array { a0, a1, a2, ..., a[n-1] }
 * Compute the following array, in which arr[i] = a0.a1.a2...a[i-1].a[i+1]. ... a[n-1] (multiply all except a[i])
 * You are not allowed to use division
 *
 * Analysis:
 * C[i] = (a0.a1.a2...a[i-1]) * (a[i+1]. ... a[n-1])
 * compute two part separately
 *
 * A[i] = a0.a1.a2...a[i-1]
 * B[i] = a[i+1].a[i+2]. ... a[n-1]
 * C[i] = A[i] * B[i]
 *
 *  eg. A[0] = 1;
 *      A[1] = a0;
 *      A[2] = a0a1
 *      ...
 *      A[n-1] = a0a1a2...a[n-2]
 *
 *      Recursion Formula: A[i] = A[i-1] * a[i-1]
 *
 *
 * eg. B[n-1] = 1
 *     B[n-2] = a[n-1]
 *     B[n-3] = a[n-2]a[n-1]
 *     ...
 *     B[0] = a1a2...a[n-1]
 *
 *     Recursion Formula: B[i] = B[i+1] * a[i+1];
 *
 * eg. C[0] = A[0] * B[0] = 1 * (a1a2...a[n-1])
 *     C[1] = A[1] * B[1] = ( a0 ) * ( a2a3...a[n-1] )
 *     C[2] = A[2] * B[2] = ( a0a1 ) * ( a3a4...a[n-1] )
 */
public class MultiplyAllExceptSelf {
	public int[] multiplyExceptSelf(int[] input) {
		int cap = input.length;
		int[] C = new int[cap];

		int[] A = new int[cap]; A[0] = 1;
		int[] B = new int[cap]; B[cap-1] = 1;

		// compute A[i] in which i: 0 ~ n-1
		for (int i = 1; i < cap; i++) {
			A[i] = input[i-1] * A[i-1];
		}

		// compute B[j] in which j: n-1 ~ 0
		for (int j = cap-2; j >= 0; j--) {
			B[j] = input[j+1] * B[j+1];
		}

		// compute C
		for(int k = 0; k < cap; k++) {
			C[k] = A[k] * B[k];
		}

		return C;
 	}

	public void display(int[] A) {
		for(int num: A) {
			System.out.print(num + " ");
		}
	}

	public static void main(String[] args) {
		MultiplyAllExceptSelf myclass = new MultiplyAllExceptSelf();
		int[] input = new int[]{2, 3, 8, 4, 7};
		int[] ans = myclass.multiplyExceptSelf(input);
		myclass.display(ans);
	}
}
