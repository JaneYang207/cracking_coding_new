package chap8.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * 8.9
 * Key: how do we know if "(" or ")" is valid or not
 * Answer:
 * 1. left paren: "(" is always valid
 * 2. right paren: ")" is invalid if number of ")" is larger than number of "(". eg. "())"
 */
public class Parentheses {
	/**
	 * @param index index of the character array. For example, if n = 3, then output strings should contains 6 characters,
	 *             index should be from 0 ~ 5;
	 * @param previous string composed of characters before index i
	 */
	void doGetParentheses1(int index, String previous, int leftRemaing, int rightRemaing, List<String> ans) {
		// base condition
		if (leftRemaing == 0 && rightRemaing == 0) {
			ans.add(previous);
			return;
		}

		if(leftRemaing > 0) {
			doGetParentheses1(index+1, previous + "(", leftRemaing - 1, rightRemaing, ans);
		}

		if (rightRemaing > 0 && rightRemaing > leftRemaing) {
			doGetParentheses1(index+1, previous + ")", leftRemaing, rightRemaing - 1, ans);
		}
	}

	/**
	 * Instead of a String as "Previous", use List<Character> "cur"
	 */
	void doGetParentheses2(int index, List<Character> cur, int leftRemaing, int rightRemaing, List<List<Character>> ans) {
		// base condition
		if (leftRemaing == 0 && rightRemaing == 0) {
			ans.add(deepCopy(cur));
			return;
		}

		if(leftRemaing > 0) {
			Character newLeftChar = new Character('(');
			cur.add(newLeftChar);
			doGetParentheses2(index+1, cur, leftRemaing - 1, rightRemaing, ans);
			cur.remove(newLeftChar);
		}

		if (rightRemaing > 0 && rightRemaing > leftRemaing) {
			Character newRightChar = new Character(')');
			cur.add(newRightChar);
			doGetParentheses2(index+1, cur, leftRemaing, rightRemaing - 1, ans);
			cur.remove(newRightChar);
		}
	}

	List<String> getParentheses1(int n) {
		List<String> ans = new ArrayList<>();
		doGetParentheses1(0, "", n, n, ans);
		return ans;
	}

	List<List<Character>> getParentheses2(int n) {
		List<Character> cur = new ArrayList<>();
		List<List<Character>> ans = new ArrayList<>();
		doGetParentheses2(0, cur, n, n, ans);
		return ans;
	}

	public void displayString(List<String> strs) {
		for (String str : strs) {
			System.out.println(str);
		}
	}

	public void displayList(List<Character> list) {
		for (Character ch : list) {
			System.out.print(ch);
		}
		System.out.println(" ");
	}

	public List<Character> deepCopy(List<Character> list) {
		List<Character> listCopy = new ArrayList<>();

		for(Character ch : list) {
			listCopy.add(ch);
		}

		return listCopy;
	}

	public void displayAllLists(List<List<Character>> lists) {
		for (List<Character> list : lists) {
			displayList(list);
		}
	}

	public static void main(String[] args) {
		Parentheses myclass = new Parentheses();
//		List<String> ans1 = myclass.getParentheses1(3);
//		myclass.displayString(ans1);

		List<List<Character>> ans2 = myclass.getParentheses2(3);
		myclass.displayAllLists(ans2);
	}
}
