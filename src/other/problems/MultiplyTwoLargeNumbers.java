package other.problems;

/**
 * From Facebook:
 * Multiply two large numbers. Each one has around 1 million digits. (10 ^ 6). Value range from 0 ~ 10^(10^6)
 *
 * Use int[] to represent large numbers. In int[] A, A[0] stands for the 0 digit, A[1] stands for the 1th digit;
 *
 * Could also use String to represent large numbers
 *
 * Analysis:
 * java long integer: maximum 64 bits. Integer value can be represented should be from -2^63 ~ 2^63-1.
 * We cannot represent the large numbers using java integer;
 * Also, We cannot store the large numbers easily.
 *
 * Two Sub-problems we need to solve here:
 * 1. how to represent and store those large numbers, and their multiply results
 * 2. how to do multipy
 *
 * sub problem 1: use array to store, each element represent one digit. eg. number "123456" is represented by [1, 2, 3, 4, 5, 6]
 * sub problem 2: do multiply digit by digit
 *
 *
 *                                                                                                           a[n-1]      .............    ............    .......... a5     a4    a3    a2     a1    a0
 *                                           *                                                                            b[m-1]  ..........  ............  .......  b5     b4    b3    b2     b1    b0
 * ______________________________________________________________________________________________________________________________________________________________________________________________________
 *                                                                                 a[n-1]b0            ......              a[m-1]b0   a[m-2]b0         ......       a5b0   a4b0  a3b0  a2b0   a1b0  a0b0
 *                                                               a[n-1]b1          a[n-2]b1            ......              a[m-2]b1   a[m-3]b1         ......       a4b1   a3b1  a2b1  a1b1   a0b1
 *                                              a[n-1]b2         a[n-2]b2          a[n-3]b2            ......              a[m-3]b3   a[m-4]b2         ......       a3b2   a2b2  a1b2  a0b2
 *                                                 .                 .                .                                       .           .                         a2b3   a1b3  a0b3
 *                                                 .                 .                .                                       .           .                         a1b4   a0b4
 *                                                 .                 .                .                                       .           .                         a0b5
 *                                                 .                 .                .                                       .           .
 *                                                 .                 .                .                                    a2b[m-3]   a1b[m-3]
 *                                                 .                 .                .                                    a1b[m-2]   a0b[m-2]
 *    a[n-1]b[m-1]   a[n-2]b[m-1]   ....... a[n-m+2]b[m-1]     a[n-m+1]b[m-1]    a[n-m]b[m-1]  ......  a2b[m-1]  a1b[m-1]  a0b[m-1]
 * ______________________________________________________________________________________________________________________________________________________________________________________________________
 *
 * Key issue:
 * Q. how many digits does "result" have? if A has "n" digits, B has "m" digits, C = A*B (count carry on)
 * A: m + (n - 1) + 1 = m + n, ( C[0], C[1], ..., C[m+n-1], in which C[m+n-1] is the carry on )
 *
 * Q. how to compute each digit?
 * A: Method 1:
 *    C[0] = a0b0
 *    C[1] = a1b0 + a0b1
 *    C[2] = a2b0 + a1b1 + a0b2
 *
 *    C[k] = add all a[i]b[j]  i+j = k
 *                             i: Math.min(k, n-1)  ( if (k <= n-1), i range from: k ~ 0; if (k > n-1), i range from n-1 ~ 0 )
 *                             j: Math.min(k, m-1)
 *
 *
 *    Method 2:
 *    Multiply all a[i]b[j], in which i: 0 ~ n-1; j: 0 ~ m-1, put the multiply result at C[i+j] ( C[i+j] = C[i+j] + a[i]b[j] ), then compute carry on
 */
public class MultiplyTwoLargeNumbers {

	public int[] multiply1 (int[] A, int[] B) {
		int sizeA = A.length;
		int sizeB = B.length;
		int[] C = new int[sizeA + sizeB];
		int carry_on = 0;

		// compute C[0] to C[n-m-2], C[n-m] is the carry on, it will be either 1 or 0
		for (int k = 0; k < C.length - 1; k++) {
			C[k] = carry_on;

//			for (int i = Math.min(k, sizeA-1); i >= 0; i--) {
			for (int i = Math.min(k, sizeA-1), j = k-i; j <= Math.min(k, sizeB-1); i--, j++) {
				C[k] += A[i] * B[k-i];
			}

			carry_on = C[k]/10;

			if(carry_on > 0) {
				C[k] = C[k] % 10;
			}

			System.out.println("C[" + k + "] = " + C[k]);
		}

		// Compute C[n+m-1]
		C[sizeA+sizeB-1] = carry_on;

		return C;
	}

	/**
	 * Multiply all a[i]b[j], in which i: 0 ~ n-1; j: 0 ~ m-1, put the multiply result at C[i+j] ( C[i+j] = C[i+j] + a[i]b[j] ), then compute carry on
	 * ( C[i+j] = C[i+j] + a[i]b[j] )
	 *
	 * @param A
	 * @param B
	 * @return
	 */
	public int[] multiply2 (int[] A, int[] B) {
		int sizeA = A.length;
		int sizeB = B.length;
		int[] C = new int[sizeA+sizeB];

		// Step 1. compute all combinations of A[i]B[j]
		for (int i = 0; i < sizeA; i++) {
			for (int j = 0; j < sizeB; j++) {
				C[i+j] += A[i]*B[j];
			}
		}

		// Step 2. compute carry-ons
		int carry_on = 0;
		for (int k = 0; k < C.length; k++) {
			// add carry_on to each digit
			C[k] += carry_on;

			// decide carry_on and remaining
			carry_on = C[k] / 10;
			C[k] = C[k] % 10;
		}

		return C;
	}

	public void display(int[] A) {
		for(int i = A.length - 1; i >= 0; i--) {
			System.out.print(A[i] + " ");
		}
	}

	public void display(int[] A, int[] B, int[] C) {
    display(A);
		System.out.print(" * ");
		display(B);
		System.out.print(" = ");
		display(C);
		System.out.println(" ");
	}


	public static void main(String[] args) {
		MultiplyTwoLargeNumbers myclass = new MultiplyTwoLargeNumbers();

		// 523 * 43
		int[] A = new int[]{3, 2, 5};
		int[] B = new int[]{3, 4};

		int[] C = myclass.multiply2(A, B);
		myclass.display(A, B, C);
	}

}
