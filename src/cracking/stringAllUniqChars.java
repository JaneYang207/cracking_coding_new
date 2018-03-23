package cracking;

/**
 * Created by jyang on 3/1/17.
 */
public class stringAllUniqChars
{
	public static void main(String[] args) {
		System.out.println("==========1.1===========");
		System.out.println(isAllUniqChars("something"));
		System.out.println(isAllUniqChars("popup"));

		System.out.println("==========1.2===========");
		System.out.println(reverseString("abcd"));
		System.out.println(reverseString("solution"));

		System.out.println("==========1.3===========");
		System.out.println(removeStringDup("abcd"));
		System.out.println(removeStringDup("popup"));
		System.out.println(removeStringDup(null));
		System.out.println(removeStringDup(""));
		System.out.println(removeStringDup("aaaaa"));
		System.out.println(removeStringDup("aaabbb"));
		System.out.println(removeStringDup("ababab"));
	}

	/**
	 * 1.1 Implement an algorithm to determine if a string has all unique characters.
	 * Time: O(n)
	 * Space: 256 bit
	 * @param str
	 * @return
	 */
	public static boolean isAllUniqChars(String str) {
		boolean[] ascCodeContained = new boolean[256]; // undefined if not contained in the string, true if only show once, false if show multiple times

		for (int i=0; i<str.length(); i++) {
			int ascCode = str.charAt(i);

			if (ascCodeContained[ascCode]) {
				return false;
			}

			ascCodeContained[ascCode] = true;
		}
		return true;
	}

	/**
	 * 1.2 reverse a c style string
	 * @param inputStr
	 * @return
	 */
	public static String reverseString(String inputStr) {
		String outputStr = " ";
		for (int i=inputStr.length()-1; i>=0; i--) {
			outputStr += inputStr.charAt(i);
		}
		return outputStr;
	}

	/**
	 * Design an algorithm to remove the duplicate characters in a string without using any
	 * additional buffer. Note: one or two additional variables are fine. An extra copy of
	 * the array is not
	 * @param str
	 * @return
	 */
	public static String removeStringDup(String str) {
		// edge cases
		if(str == null) {
			return null;
		}
		if (str.length() <= 1) { // including length = 1 and length = 0 (empty string)
			return str;
		}

		// convert String to char[]
		char[] arr = str.toCharArray();

		int tail = 1;
		// todo: when create a new boolean[], what's the default value of each element?
		boolean[] charSet = new boolean[256];
		charSet[(int)arr[0]] = true;

		for (int i=1; i<arr.length; i++) {
			// determine if it's a duplicate
			int ascCode = arr[i];

			// todo: change this to !charSet[]
			if(charSet[ascCode]){
				continue;
			}
			charSet[ascCode] = true;

			// if it's not a duplicate, add it to the tail
			arr[tail] = arr[i];
			tail++;
		}

		// convert char[] chars = ['a', 'b', 'c'] to String:
		// way 1. new String(chars)
		// way 2. String.valueOf(chars)
//		return new String(arr);
		return String.valueOf(arr, 0, tail);
		// String.valueOf(char[] chars, int offset, int count)
		// offset is the index of the first character of the subarray;
		// count is the length of the subarray
	}
}
