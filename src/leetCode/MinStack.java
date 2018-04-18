package leetCode;

import java.util.EmptyStackException;
import java.util.Stack;

public class MinStack {

    Stack<Integer> stack;
    // use another stack "mins" to keep track of min values. Top of "mins" is the current min value
    Stack<Integer> mins;

    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.mins = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);

        // only push min values into "mins"

        // values gets pused in are not unique, they might duplicate
        // if (mins.isEmpty() || x < getMin())
        if (mins.isEmpty() || x <= getMin()) {
            mins.push(x);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }

        // java stack pop() will also throw "java.util.EmptyStackException" when stack is empty
        Integer out = stack.pop();
        System.out.print("POP out " + out + " from stack.");

        // only pop out from "mins" when "out == mins.peek()"
//        if (!mins.isEmpty() && out == mins.peek()) {
        if (!mins.isEmpty() && out.equals(mins.peek())) {
            System.out.print("=============POP out " + mins.peek() + "from mins.");
            mins.pop();
        }
        System.out.println("");
    }

    public int top() {
        return stack.isEmpty() ? 0: stack.peek();
    }

    public int getMin() {
        // java stack peek() will throw "java.util.EmptyStackException" when stack is empty
        // return mins.peek();

        int ans = mins.isEmpty() ? 0 : mins.peek();
        return ans;
    }

    public static void main(String[] args) {
        MinStack myclass = new MinStack();
        myclass.push(512);
        myclass.push(-1024);
        myclass.push(-1024);
        myclass.push(512);

        myclass.pop(); // 512
        myclass.getMin();

        myclass.pop(); // -1024
        myclass.getMin();

        myclass.pop(); // -1024
        int ans = myclass.getMin();
        System.out.println("min is " + ans);

    }
}
