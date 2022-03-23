/**
 * A class conatining some methods that might be useful for testing. Uncomment
 * these methods when you have implemented the corresponding classes (SLL,DLL)
 *
 * @author CSC 212
 * @version March 2022
 */
public class TestUtils {
	/** Makes an SLL that holds the contents of the provided array */
	public static <T> SLL<T> makeSLL(T[] arr) {
		SLL<T> list = new SLL<T>();
		int i = arr.length;
		while (i > 0) {
			i--;
			list.addFirst(arr[i]);
		}
		return list;
	}

	/** checks the form and contents of a SLL for errors */
	public static <T> void verifySLL(String testName, SLL<T> list, T[] values) {
		TestCode.beginTest(testName);
		NodeSL<T> here = list.getHead();
		for (int i = 0; i < values.length; i++) {
			TestCode.subTest("element " + i, values[i] == here.getData());
			here = here.getNext();
		}
		TestCode.subTest("tail terminal", here == null);
		TestCode.concludeTest();
	}

	/** Makes an DLL with the contents of the array */
	public static <T> DLL<T> makeDLL(T[] arr) {
		DLL<T> list = new DLL<T>();
		int i = arr.length;
		while (i > 0) {
			i--;
			list.addFirst(arr[i]);
		}
		return list;
	}

	/** checks the form and contents of a DLL for errors */
	public static <T> void verifyDLL(String testName, DLL<T> list, T[] values) {
		TestCode.beginTest(testName);
		NodeDL<T> here = list.getHead();
		for (int i = 0; i < values.length; i++) {
			TestCode.subTest("forward element " + i, values[i] == here.getData());
			here = here.getNext();
		}
		TestCode.subTest("tail terminal", here == null);
		here = list.getTail();
		for (int i = values.length-1; i > -1; i--) {
			TestCode.subTest("reverse element " + i, values[i] == here.getData());
			here = here.getPrevious();
		}
		TestCode.subTest("head terminal", here == null);
		TestCode.concludeTest();
	}

	/** Demonstrates use of these methods */
	public static void demoTest() {
		// create an array
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = makeSLL(abc_arr);
		verifySLL("SLL creation", abc_list, abc_arr);
	}
}