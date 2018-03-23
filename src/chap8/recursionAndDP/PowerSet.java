package chap8.recursionAndDP;

import java.util.HashSet;
import java.util.Set;

/**
 * 8.4 Power set: Write a method to return all subsets of a set
 */
public class PowerSet {

	public Set<HashSet<Integer>> getSubSets(Set<Integer> set) {
		Set<HashSet<Integer>> ans = new HashSet<>();
		HashSet<Integer> newSubSet;
		Set<HashSet<Integer>> ansCopy;
		Set<HashSet<Integer>> moreAns;

		for(int item : set) {
			// error: ConcurrentModificationException
//			for(Set subset : ans) {
//				newSubSet = new HashSet<>(subset);
//				newSubSet.add(item);
//
//				ans.add(newSubSet);
//			}

			// Solve concurrent modification: solution 1
//		  ansCopy = new HashSet<>(ans);
//			for(Set subset : ansCopy) {
//				newSubSet = new HashSet<>(subset);
//				newSubSet.add(item);
//
//				ans.add(newSubSet);
//			}

			// Solve concurrent modification: solution 2
			// This solution takes up the same memory as solution 1.
			// Although it doesn't have to copy over all subsets in "ans",
			// it copies all subsets in "moreAns" to "Ans".
			// And the number of elements to be copied over are the same for both solutions.
			moreAns = new HashSet<>();
			for(Set subset : ans) {
				newSubSet = new HashSet<>(subset);
				newSubSet.add(item);

				moreAns.add(newSubSet);
			}
			ans.addAll(moreAns);



			newSubSet = new HashSet<>();
			newSubSet.add(item);
			ans.add(newSubSet);
		}

		ans.add(new HashSet<>());

		return ans;
	}

	public void display(Set<HashSet<Integer>> ans) {
		for(HashSet<Integer> subset : ans) {
			for (int number : subset) {
				System.out.print(number + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		PowerSet myclass = new PowerSet();

		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);

		Set<HashSet<Integer>> ans = myclass.getSubSets(set);
		myclass.display(ans);
	}

}
