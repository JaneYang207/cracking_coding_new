package chap4.treesAndGraphs;

/**
 * Created by jyang on 8/29/17.
 */
public class recursionPlayGround
{

	public static void main(String[] args) {
		int result =  doRecursion(0, 8);
	}

	private static int doRecursion(int lower, int higher){
		if(lower == higher) {
			return lower;
		} else {
			int mid = (lower + higher)/2;
			int some = doRecursion(lower, mid-1);
			int some2 = doRecursion(mid+1, higher);
			return 0;
		}
	}
}
