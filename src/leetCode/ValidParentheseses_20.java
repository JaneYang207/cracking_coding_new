package leetCode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ValidParentheseses_20 {
    List<Character> lefts = Arrays.asList('(', '[', '{');
    List<Character> rights = Arrays.asList(')', ']', '}');

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);

            if(lefts.contains(ch)) {
                stack.push(ch);
            } else if (rights.contains(ch)) {

                if (stack.isEmpty() || !checkIfMatch(ch, stack.peek())) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                System.out.println("Input contains non-parenthesis character.");
                return false;
            }
        }

        return stack.isEmpty();
    }

    private boolean checkIfMatch(char right, char top) {
        switch(right) {
            case ')': return top == '(';
            case ']': return top == '[';
            case '}': return top == '{';
            default: return false;
        }
    }

    public static void main(String[] args) {
        ValidParentheseses_20 myclass = new ValidParentheseses_20();
        boolean ans = myclass.isValid("()");
        System.out.println("ans = " + ans);
    }
}
