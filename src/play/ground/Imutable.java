package play.ground;

public class Imutable {

	/**
	 * Test function calls with primitive types as function parameter
	 */
	public void testPrimitiveType(int n, int m) {
		if (n == 0) return;

		m++;

		System.out.println("========== when n = " + n + "================");
		System.out.println("Before call recursion: m = " + m);

		testPrimitiveType(n-1, m);
		System.out.println("========== n = " + n + "================");
		System.out.println("After recursion call returns: m = " + m);
	}

	/**
	 * Test function calls with immutable reference types (such as String, or Integer) as function parameter
	 */
	public void testImmutable(int n, String str) {
		if (n == 0) return;

		str = str + "a";
		System.out.println("========== n = " + n + "================");
		System.out.println("Before call recursion: str = " + str);

		testImmutable(n-1, str);
		System.out.println("========== n = " + n + "================");
		System.out.println("After recursion call returns: : str = " + str);

	}

	/**
	 * Test function calls with mutable reference types as function parameter
	 */
	public void testReferenceType(int n, Person p) {
		if (n == 0) return;

		p.setName(p.getName() + "George");
		System.out.println("========== n = " + n + "================");
		System.out.println("Before call recursion: person is " + p.getName());

		testReferenceType(n-1, p);
		System.out.println("========== n = " + n + "================");
		System.out.println("After recursion call returns: : person is " + p.getName());
	}

	public static void main(String[] args) {
		Imutable myclass = new Imutable();

		myclass.testPrimitiveType(2, 10);

		myclass.testImmutable(2, "");

		myclass.testReferenceType(2, new Person(""));
	}

	private static class Person {
		String name;

		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
