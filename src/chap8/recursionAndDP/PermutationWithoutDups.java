package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * 8.7 Permutations without duplicates
 */
public class PermutationWithoutDups {

	List<String> getPermutations1(String input)
	{
		List<String> permutations = new ArrayList<>();

		// base condition
		if (input.length() == 0) {
			return null;
		}

		if (input.length() == 1) {
			permutations.add(input);
			return permutations;
		}

		// recursion

		String subString = input.substring(1);
		char ch = input.charAt(0);

		List<String> last_permutations = getPermutations1(subString);

		for (String str : last_permutations) {
			for (int i = 0; i <= str.length(); i++) {
				permutations.add(insertCharAt(str, ch, i));
			}
		}

		return permutations;
	}


	String insertCharAt(String str, char ch, int i) {
		return str.substring(0, i) + ch + str.substring(i);
	}

	void displayStrings(List<String> strs) {
		for (String str : strs) {
			System.out.print(str + " ");
		}
	}


	static void testSubstringMethod(String str) {
		String sub1, sub2;
//		for (int i = 0; i <= str.length(); i++) {
//			System.out.print("when i = " + i);
//			sub1 = str.substring(0, i);
			sub2 = str.substring(4); // string index out of range: -1
//			System.out.print("       sub1 = " + sub1);
			System.out.println("       sub2 = " + sub2);
//		}
	}



	List<String> getPermutations2(String input) {
		List<String> ans = new ArrayList<>();
		getPermsRec("", input, ans);

		return ans;
	}

	void getPermsRec(String prefix, String remaining, List<String> ans) {
//		String newPrefix;
//		String newRemaining;

		if(remaining.length() == 0) {
			ans.add(prefix);
			return;
		}

		for(int i = 0; i<remaining.length(); i++) {
			// will modify the prefix for next iteration
//			prefix = prefix + remaining.charAt(i);

			// concurrency modification
//			remaining = remaining.substring(0, i) + remaining.substring(i+1);

			getPermsRec(prefix, remaining, ans);

			remaining = remaining.substring(0, i) + prefix.charAt(prefix.length()-1) + remaining.substring(i);
			prefix = prefix.substring(0, prefix.length()-1);

//			newPrefix = prefix + remaining.charAt(i);
//			newRemaining = remaining.substring(0, i) + remaining.substring(i+1);
//
//			getPermsRec(newPrefix, newRemaining, ans);
		}

	}

	public static void main(String[] args) {
//		PermutationWithoutDups.testSubstringMethod("abc");

		PermutationWithoutDups myclass = new PermutationWithoutDups();
//		List<String> ans = myclass.getPermutations1("abc");
		List<String> ans = myclass.getPermutations2("abc");
		myclass.displayStrings(ans);
	}
}
