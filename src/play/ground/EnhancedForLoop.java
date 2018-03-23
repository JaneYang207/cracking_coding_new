package play.ground;

/**
 * Created by jyang on 3/17/18.
 */
public class EnhancedForLoop {

	public static void main(String[] args) {
		String[] arr;

		// if arr is null, the for loop will throw NullPointerException
		// arr = null;

		// if arr size is 0, the it will not enter the for loop
		arr = new String[0];

		for(String str : arr) {
			System.out.println(str);
		}

	}
}
