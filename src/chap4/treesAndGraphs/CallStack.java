package chap4.treesAndGraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 636. Exclusive Time of Functions
 */
public class CallStack {
	public int[] exclusiveTime(int n, List<String> logs) {
		int[] ans = new int[n];

		Stack<StackObject> s = new Stack();

		// read log
		StackObject cur;

		for(String str : logs) {
			cur = new StackObject(str);

			System.out.println(str);

			// if is "start", push into log
			if (cur.operator == 0) {
				s.push(cur);
			}

			// if is "end", pop, then compute time span
			else {

				// check if the one at the stack top is the same function, if not, log is wrong
				if (s.peek().id != cur.id) {
					System.out.println("Log is wrong at Function with id = " + cur.id);
					return null;
				}

				// pop top
				StackObject top = s.pop();

				// compute time span of this function; add it to the ans array
				long timeSpan = cur.time - top.time;
				ans[cur.id] += timeSpan; // before this operation, value of ans[cur.id] might be 0,
				// or some negative values (if we deducted time span of the function it called from it)
				// or some postivie values (if we called the same function multiple times)

				if(!s.isEmpty()) {
					// need to decrease the time span of the inner function from it's outer function
					StackObject newTop = s.peek(); // the new top now should be the outer function
					ans[newTop.id] -= timeSpan;
				}
			}
		}

		return ans;
	}

	private class StackObject {
		 int id;
		 long time;
		 int operator; // 0: start; 1: end

		public StackObject(int id, long time, int operator) {
			this.id = id;
			this.time = time;
			this.operator = operator;
		}

		public StackObject(String str) {
			String[] splits = str.split(":");
			if (splits.length != 3) {
				System.out.println("Log format incorrect");
				this.id = -1;
				this.time = -1;
				this.operator = -1;
			}
			else {
				this.id = Integer.parseInt(splits[0]);
				this.operator = (splits[1].equals("start")) ? 0 : 1;
				this.time = Long.parseLong(splits[2]);
			}

		}
	}

	public void display(int[] ans) {
		for(int i: ans) {
			System.out.print(i+" ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		CallStack myclass = new CallStack();
		List<String> logs = new ArrayList<>();
		logs.add("0:start:1");
		logs.add("1:start:4");
		logs.add("1:end:7");
		logs.add("2:start:8");
		logs.add("2:end:10");
		logs.add("0:end:14");
		logs.add("3:start:15");
		logs.add("3:end:19");

		int[] ans = myclass.exclusiveTime(4, logs);
		myclass.display(ans);

	}
}
