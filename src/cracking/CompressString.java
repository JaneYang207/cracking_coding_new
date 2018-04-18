package cracking;

/**
 * Created by jyang on 3/12/17.
 */
public class CompressString
{
	public static String compressStringByCompareWithNext(String inputStr) {
		if (inputStr == null) {
			return null;
		}

		String outputStr = "";

		int count = 1;
		for (int i = 0; i < inputStr.length(); i++ ) {
			if ( i+1 < inputStr.length() && inputStr.charAt(i) == inputStr.charAt(i+1) ) {
				count++;
			}
			else {
				outputStr = outputStr + inputStr.charAt(i) + count;
				count = 1;
			}
		}

		return (outputStr.length() < inputStr.length()) ? outputStr : inputStr;
	}

	public static String compressStringByCompareWithPrevious(String inputStr) {
		if (inputStr == null) {
			return null;
		}

		if (inputStr.length() < 2) {
			return inputStr;
		}

		String outputStr = "";

        // Note: If compare character with previous one, need to give initialize value to the previous Character before starting the loop
		char previousChar = inputStr.charAt(0);
		int count = 1;
		// Note: since loop starting from index 1, needs to consider strings whose length is only 1
		for (int i = 1; i < inputStr.length(); i++ ) {
			if (inputStr.charAt(i) == previousChar ) {
				count++;
			}
			else
			{
				outputStr = outputStr + previousChar + count;
				previousChar = inputStr.charAt(i);
				count = 1;
			}
		}

		// Note: a trick in the former RotateMatrix: the last part never gets added to the output string.
		// eg: aabccc, outputStr after the loop is "a2b1", "c3" was never added to output string.
		// So needs the following:
		outputStr = outputStr + previousChar + count;

		return (outputStr.length() < inputStr.length()) ? outputStr : inputStr;
	}

	public static String compressStringUsingStringBuilder(String inputStr) {
		if (inputStr == null) {
			return null;
		}

		StringBuilder outputStr = new StringBuilder(); // If not specify capacity, capacity will be 16

		int count = 1;
		for (int i = 0; i < inputStr.length(); i++ ) {
			if ( i+1 < inputStr.length() && inputStr.charAt(i) == inputStr.charAt(i+1) ) {
				count++;
			}
			else {
				outputStr.append(inputStr.charAt(i));
				outputStr.append(count);
				count = 1;
			}
		}

		return (outputStr.length() < inputStr.length()) ? outputStr.toString() : inputStr;
	}

	public static void main(String[] args) {
//		String outputStr = compressStringByCompareWithNext("abcccccccc");
//		String outputStr = compressStringByCompareWithPrevious("abcccccccc");
		String outputStr = compressStringUsingStringBuilder("abcccccccc");
		System.out.println(outputStr);
	}
}
