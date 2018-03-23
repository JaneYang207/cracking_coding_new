package cracking;

/**
 * Created by jyang on 3/12/17.
 */
public class ReplaceStringSpaces
{
	public static String replaceStringSpaces(String inputStr, int actualLength){
		String outputStr = "";

		char ch;
		for (int i=0; i < Math.min(actualLength, inputStr.length()); i++) {
			ch = inputStr.charAt(i);

			if(inputStr.charAt(i) != ' ') {
				outputStr+=(String.valueOf(ch));
			}
			else {
				outputStr+="%20";
			}
		}

		return outputStr;
	}

	public static char[] replaceStringSpacesTwoScanApproach(char[] inputStr, int actualLength) {
		// Scan to get number of spaces and extra spaces
		int spaceCount = 0;
		for (int i=0; i<actualLength; i++) {
			if(inputStr[i] == ' ') {
				spaceCount++;
			}
		}
		int indexDiff = 2 * spaceCount;

		// Scan from end to beginning to modify the char[]
		for (int j = actualLength - 1; j >= 0; j--) {

			int index = j + indexDiff;

			if(inputStr[j] == ' ') {
				inputStr[index] = '0';
				inputStr[index-1] = '2';
				inputStr[index-2] = '%';

				indexDiff = indexDiff -2;
			}
			else {
				inputStr[index] = inputStr[j];
			}
		}
		return inputStr;
	}

	public static void main(String[] args) {
//		String str1 =" ";
//		String str2 ="%20";
//		System.out.println(str1.length());
//		System.out.println(str2.length());
		String inputStr1 = "Mr John Smith    ";
		int actualLength1 = 13;

		String inputStr2 = "Mr  John    Smith      ";
		int actualLength2 = 20;

		String inputStr3= "  Mr John Smith  ";
		int actualLength3 = 20;

		String inputStr4="";
		int actualLength4=5;

//		String outputStr = replaceStringSpaces(inputStr4, actualLength4);
		char[] inputStr = inputStr1.toCharArray();
		char[] outputChar = replaceStringSpacesTwoScanApproach(inputStr, actualLength1);
		String outputStr = String.valueOf(outputChar);

		System.out.println(outputStr);

	}
}
