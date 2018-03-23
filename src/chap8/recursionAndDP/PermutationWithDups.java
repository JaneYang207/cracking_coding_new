package chap8.recursionAndDP;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermutationWithDups {

	/**
	 * Solution 1. use HashSet instead of Arraylist to rule out duplicates in ans
	 * Time: O(n * n * n!)
	 */
	Set<String> getPermutations1(String input) {
		Set<String> ans = new HashSet<>();
		getPermsRec1("", input, ans);

		return ans;
	}

	void getPermsRec1(String prefix, String remaining, Set<String> ans) {
		if(remaining.length() == 0) {
			ans.add(prefix);
		}

		for(int i = 0; i<remaining.length(); i++) {
			String new_prefix = prefix + remaining.charAt(i);
			String new_remaining = remaining.substring(0, i) + remaining.substring(i+1);

			getPermsRec1(new_prefix, new_remaining, ans);
		}
	}


	/**
	 * Solution 2: memorize
	 * More time efficient than solution 1, but cost more space
	 */
	List<String> getPermutations2(String input) {
		List<String> ans = new LinkedList<>();
		Map<String, String> map = new HashMap<>();
		getPermsRec2("", input, ans, map);

		return ans;
	}

	void getPermsRec2(String prefix, String remaining, List<String> ans, Map<String, String> memo) {
		if(remaining.length() == 0) {
			ans.add(prefix);
		}

		for(int i = 0; i<remaining.length(); i++) {
			String new_prefix = prefix + remaining.charAt(i);
			String new_remaining = remaining.substring(0, i) + remaining.substring(i+1);

			// check if we already called the recursion function "getPermsRec2" with the same "prefix", and "remaining"
			// if yes, do not call the recursion function again.
		  if (!memo.containsKey(new_prefix) || !memo.get(new_prefix).equals(new_remaining)) {
				getPermsRec2(new_prefix, new_remaining, ans, memo);
				memo.put(new_prefix, new_remaining);
			}
		}
	}


	List<String> getPermutations3(String input) {
		List<String> ans = new ArrayList<>();

		// iterate through the input string, count characters
		Map<Character, Integer> map = countChars(input);

		getPermsRec3("", map, ans);

		return ans;
	}



	void getPermsRec3(String prefix, Map<Character, Integer> remainingMap, List<String> ans) {

		// take each key in "remainingMap", attch it to prefix; update "remainingMap" accordingly
//		Set<Character> keys = remainingMap.keySet();
		Set<Character> keys = new HashSet<>(remainingMap.keySet());

		// Base case: only one key left
		if (keys.size() == 1) {

			// build the string to add
			// error: cannot cast Object[] to Characer[]
//			Character[] key_arr = (Character[])keys.toArray();
			Character key = (Character) (keys.toArray())[0];
			int value = remainingMap.get(key);

			StringBuilder builder = new StringBuilder();
			builder.append(prefix);
			for (int count = 0; count < value; count++) {
				builder.append(key);
			}
			ans.add(builder.toString());
			return;
		}

		for (char key : keys) {
			// update prefix
			String new_prefix = prefix + key;
//			Map<Character, Integer> new_remainingMap = new HashMap<>(remainingMap);

			// update remainingMap: either update value, or remove the key
			int value = remainingMap.get(key);
			value--;
			if (value == 0) {
				remainingMap.remove(key);
//				new_remainingMap.remove(key);
			} else {
				remainingMap.put(key, value);
//				new_remainingMap.put(key, value);
			}

			getPermsRec3(new_prefix, remainingMap, ans);
//			getPermsRec3(new_prefix, new_remainingMap, ans);
		}
	}

	void getPermsRec4(String prefix, Map<Character, Integer> remainingMap, List<String> ans) {

		// take each key in "remainingMap", attch it to prefix; update "remainingMap" accordingly
		Set<Character> keys = remainingMap.keySet();

		// Base case: only one key left
		if (keys.size() == 1) {

			Character key = (Character) (keys.toArray())[0];
			int value = remainingMap.get(key);

			StringBuilder builder = new StringBuilder();
			builder.append(prefix);
			for (int count = 0; count < value; count++) {
				builder.append(key);
			}
			ans.add(builder.toString());
			return;
		}

		for (char key : keys) {
			// update prefix
			String new_prefix = prefix + key;

			// update remainingMap: either update value, or remove the key
			int value = remainingMap.get(key);
			value--;
			if (value == 0) {
				remainingMap.remove(key);
			} else {
				remainingMap.put(key, value);
			}

			getPermsRec4(new_prefix, remainingMap, ans);
		}
	}

	void getPermsRec5(String prefix, Map<Character, Integer> remainingMap, List<String> ans) {

		// take each key in "remainingMap", attch it to prefix; update "remainingMap" accordingly
		Set<Character> keys = new HashSet<>(remainingMap.keySet());

		// Base case: only one key left
		if (keys.size() == 1) {

			// build the string to add
			Character key = (Character) (keys.toArray())[0];
			int value = remainingMap.get(key);

			StringBuilder builder = new StringBuilder();
			builder.append(prefix);
			for (int count = 0; count < value; count++) {
				builder.append(key);
			}
			ans.add(builder.toString());
			return;
		}

		for (char key : keys) {
			// update prefix
			String new_prefix = prefix + key;
			Map<Character, Integer> new_remainingMap = new HashMap<>(remainingMap);

			// update remainingMap: either update value, or remove the key
			int value = remainingMap.get(key);
			value--;
			if (value == 0) {
				new_remainingMap.remove(key);
			} else {
				new_remainingMap.put(key, value);
			}

			getPermsRec5(new_prefix, new_remainingMap, ans);
		}
	}

	Map<Character, Integer> countChars(String input) {
		Map<Character, Integer> map = new HashMap<>();
		char[] arr = input.toCharArray();
		for (char ch:arr) {
			int value = map.containsKey(ch) ? map.get(ch) + 1 : 1;
			map.put(ch, value);
		}
		return map;
	}

	void displayStrings(Set<String> strs) {
		for (String str: strs) {
			System.out.print(str + " ");
		}
		System.out.println("");
	}

	void displayStrings(List<String> strs) {
		for (String str: strs) {
			System.out.print(str + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		PermutationWithDups myclass = new PermutationWithDups();
		Set<String> ans1 = myclass.getPermutations1("abbd");
		List<String> ans2 = myclass.getPermutations2("abbd");
		List<String> ans3 = myclass.getPermutations3("abbd");

		myclass.displayStrings(ans1);
		myclass.displayStrings(ans2);
		myclass.displayStrings(ans3);
	}
}
