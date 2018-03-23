package cracking;

/**
 * Created by jyang on 3/12/17.
 */
public class OneWayAwayStrings
{
	public static boolean oneWayAwayStrings(String str1, String str2) {
		if(str1 == null || str2 == null) {
			return false;
		}
		if(Math.abs(str1.length() - str2.length()) > 1) {
			return false;
		}
//		else if (str1.length() == str2.length()) {
//			return oneReplaceAwayStrings(str1, str2);
//		}
//		else if(str1.length() - str2.length() == 1) {
//			return oneInsertAwayStrings(str2, str1);
//		}
//		else {
//			return oneInsertAwayStrings(str1, str2);
//		}

		if(str1.length() - str2.length() == 1) {
			return oneCombinedWayAwayStrings(str2, str1);
		} else {
			return oneCombinedWayAwayStrings(str1, str2);
		}
	}

	private static boolean oneInsertAwayStrings(String shorterString, String longerString) {
		boolean flagFoundDiff = false;

		int index1 = 0, index2 = 0;
		while ( index1 < shorterString.length() && index2 < longerString.length() )
		{
			if(shorterString.charAt(index1) != longerString.charAt(index2)) {
				if(flagFoundDiff) {
					return false;
				}
				else{
					flagFoundDiff = true;
				}

				index2++;
			}

			else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	private static boolean oneReplaceAwayStrings(String str1, String str2) {
		boolean flagFoundDiff = false;

		int index1 = 0, index2 = 0;
		while (index1 < str1.length() && index2 < str2.length()) {
			if(str1.charAt(index1) != str2.charAt(index2)) {
				if(flagFoundDiff) {
					return false;
				} else{
					flagFoundDiff = true;
				}
			}

			index1++;
			index2++;
		}
		return true;
	}


	private static boolean oneCombinedWayAwayStrings(String str1, String str2) {
		boolean flagFoundDiff = false;

		int index1 = 0, index2 = 0;
		while (index1 < str1.length() && index2 < str2.length()) {
			if(str1.charAt(index1) != str2.charAt(index2)) {
				if(flagFoundDiff) {
					return false;
				} else{
					flagFoundDiff = true;
				}

				// if the two string are the same length,  move the shorter index
				if(str1.length() == str2.length()) {
					index1++;
				}
				// always move the longer index (no matter the two strings are the same length or not)
				index2++;
			}

			// always move both indexes if characters are the same
			else {
				index1++;
				index2++;
			}

		}
		return true;
	}

	public static void main(String[] args) {
		String str1 = "aple";
		String str2 = "ple";
		boolean result = oneWayAwayStrings(str1, str2);
		System.out.println(result);
	}
}
