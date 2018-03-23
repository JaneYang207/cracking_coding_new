package cracking;

public class PermutationStrings
{
//	public boolean determinePermutaionForStrings(String str1, String str2) {
//		// todo: empty strings?
//
//		if( str1.length() == 0 && str2.length() == 0) {
//			return true;
//		}
//
//		// 1. decide if size are equal
//		if (str1.length() != str2.length()) {
//			return false;
//		}
//
//		// 2. loop 1st string to get Map<Integer, Integer>
//		Map<Integer, Integer> charMap = new HashMap<>();
//
//		for (int i=0; i<str1.length(); i++) {
////			Integer char1 = (Integer)str1.charAt(i)
//			int char1 = str1.charAt(i);
//
//			if ( charMap.containsKey(char1) ) {
//				charMap.put(char1, 1);
//			}
//			else {
//				charMap.get(char1)++;
//			}
//		}
//
//		// 3. check 2nd str
//		for(int j=0; j<str2.length(); j++) {
////			Integer char2 = (Integer) str2.charAt(j);
//			int char2 = str2.charAt(j);
//
//			if ( !charMap.containsKey(char2) ) {
//				return false;
//			}
//
//			if ( (int)charMap.get(char2) == 0 ) {
//				return false;
//			}
//
//			charMap.get(char2)--;
//
//		}
//
//		return true;
//	}

	public static boolean determinePermutateStringBySort(String str1, String str2) {
		if(str1.length() == 0 && str2.length() == 0){
			return true;
		}
		if(str1.length() != str2.length()){
			return false;
		}
		char[] arr1 = str1.toCharArray();
		char[] arr2 = str2.toCharArray();

		java.util.Arrays.sort(arr1);
		java.util.Arrays.sort(arr2);

		return String.valueOf(arr1).equals(String.valueOf(arr2));
	}

	public static boolean determinePermutaionForStrings(String str1, String str2) {

		if( str1.length() == 0 && str2.length() == 0) {
			return true;
		}

		// 1. decide if size are equal
		if (str1.length() != str2.length()) {
			return false;
		}

		// 2. loop 1st string to get Map<Integer, Integer>
		int[] flag = new int[128];

		for (int i=0; i<str1.length(); i++) {
			flag[str1.charAt(i)]++;
		}

		// 3. check 2nd str
		for(int j=0; j<str2.length(); j++) {
			flag[str2.charAt(j)]--;
		}

		for(int k=0; k<flag.length; k++) {
			if(flag[k] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String str1 = "egghead";
		String str2 = "gfheadg";
		String str3 = "eheadgg";
//		boolean result = determinePermutaionForStrings(str1, str2);
		boolean result = determinePermutateStringBySort(str1, str2);
		System.out.println("============the result is============" + result);
	}
}
