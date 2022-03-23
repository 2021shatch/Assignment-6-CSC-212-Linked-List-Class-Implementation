/**
 * Class to test doubly linked list methods
 *
 * @author Sabrina Hatch & Anh Nguyen
 * @version Spring 2022
 */
public class DLLTest {
	/** Test Phase One Implementation */
	public static void testDLLPhaseOne() {
		DLL<Character> testDLL = new DLL<Character>();

		// Test empty constructor
		TestCode.beginTest("Phase1DLL: Empty List");
		TestCode.subTest("Empty list", testDLL.isEmpty());
		TestCode.subTest("Head", testDLL.getHead() == null);
		TestCode.subTest("Tail", testDLL.getTail() == null);
		TestCode.subTest("Head tail reference", testDLL.getTail() == testDLL.getHead());
		System.out.println("DLL " + testDLL);
		TestCode.concludeTest();

		// Test DLL of one item
		testDLL.addFirst('A');
		TestCode.beginTest("Phase1DLL: One Element");
		TestCode.subTest("Empty list", !testDLL.isEmpty());
		TestCode.subTest("Head tail reference", testDLL.getTail() == testDLL.getHead());
		TestCode.subTest("Tail Next", testDLL.getTail().getNext() == null);
		TestCode.subTest("Tail Previous", testDLL.getTail().getPrevious() == null);
		TestCode.subTest("Head", testDLL.getHead().getData() == 'A');
		TestCode.subTest("Head Previous", testDLL.getHead().getPrevious() == null);
		TestCode.subTest("Head Next", testDLL.getHead().getNext() == null);
		System.out.println("DLL " + testDLL);
		TestCode.concludeTest();

		// Test DLL of two item
		testDLL.addFirst('B');
		TestCode.beginTest("Phase1DLL: Two Element");
		TestCode.subTest("Empty list", !testDLL.isEmpty());
		TestCode.subTest("Head tail reference", testDLL.getTail() == testDLL.getHead().getNext());
		TestCode.subTest("Head tail reference reverse", testDLL.getTail().getPrevious() == testDLL.getHead());
		TestCode.subTest("Head Previous", testDLL.getHead().getPrevious() == null);
		TestCode.subTest("Tail Next", testDLL.getTail().getNext() == null);
		TestCode.subTest("Head", testDLL.getHead().getData() == 'B');
		TestCode.subTest("Head Next", testDLL.getHead().getNext().getData() == 'A');
		TestCode.subTest("Tail", testDLL.getTail().getData() == 'A');
		TestCode.subTest("Tail Previous", testDLL.getTail().getPrevious().getData() == 'B');
		System.out.println("DLL " + testDLL);
		TestCode.concludeTest();

		// Test DLL of three item
		testDLL.addFirst('C');
		TestCode.beginTest("Phase1DLL: Two Element");
		TestCode.subTest("Empty list", !testDLL.isEmpty());
		TestCode.subTest("Head tail reference", testDLL.getTail() == testDLL.getHead().getNext().getNext());
		TestCode.subTest("Head tail reference reverse",
				testDLL.getTail().getPrevious().getPrevious() == testDLL.getHead());
		TestCode.subTest("Tail Next", testDLL.getTail().getNext() == null);
		TestCode.subTest("Head Previous", testDLL.getHead().getPrevious() == null);
		TestCode.subTest("Head", testDLL.getHead().getData() == 'C');
		TestCode.subTest("Head Next", testDLL.getHead().getNext().getData() == 'B');
		TestCode.subTest("Head Next", testDLL.getHead().getNext().getNext().getData() == 'A');
		TestCode.subTest("Tail", testDLL.getTail().getData() == 'A');
		TestCode.subTest("Tail Previous", testDLL.getTail().getPrevious().getData() == 'B');
		TestCode.subTest("Tail Previous", testDLL.getTail().getPrevious().getPrevious().getData() == 'C');
		System.out.println("DLL " + testDLL);
		TestCode.concludeTest();
	}

	/** Test Remove first implementation */
	public static void testDLLRemoveFirst() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		// test remove first from list of three items
		abc_list.removeFirst();
		abc_arr = new String[] { "B", "C" };
		TestUtils.verifyDLL("Remove first: List of 3", abc_list, abc_arr);

		// test remove first from list of two items
		abc_list.removeFirst();
		abc_arr = new String[] { "C" };
		TestUtils.verifyDLL("Remove first: List of 2", abc_list, abc_arr);

		// test remove first from list of one items
		abc_list.removeFirst();
		abc_arr = new String[] {};
		TestUtils.verifyDLL("Remove first: List of 1", abc_list, abc_arr);

		// test remove first from empty list (throw exception)
		abc_list.removeFirst();
	}

	/** Test add last implementation */
	public static void testDLLAddLast() {
		DLL<String> abc_list = new DLL<String>();

		// test add last to empty list
		abc_list.addLast("A");
		String[] abc_arr = { "A" };
		TestUtils.verifyDLL("Add last: List of 0", abc_list, abc_arr);

		// test add last to list of one
		abc_list.addLast("B");
		abc_arr = new String[] { "A", "B" };
		TestUtils.verifyDLL("Add last: List of 1", abc_list, abc_arr);

		// test add last to list of two
		abc_list.addLast("C");
		abc_arr = new String[] { "A", "B", "C" };
		TestUtils.verifyDLL("Add last: List of 2", abc_list, abc_arr);
	}

	/** Test remove last implementation */
	public static void testDLLRemoveLast() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		abc_list.removeLast();
		abc_arr = new String[] { "A", "B" };
		TestUtils.verifyDLL("Remove last: List of 3", abc_list, abc_arr);

		abc_list.removeLast();
		abc_arr = new String[] { "A" };
		TestUtils.verifyDLL("Remove last: List of 2", abc_list, abc_arr);

		abc_list.removeLast();
		abc_arr = new String[] {};
		TestUtils.verifyDLL("Remove last: List of 1", abc_list, abc_arr);

		abc_list.removeLast();
	}

	/** Test add after implementation */
	public static void testDLLAddAfter() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		abc_list.addAfter(null, "1");
		abc_arr = new String[] { "1", "A", "B", "C" };
		TestUtils.verifyDLL("Add after at head", abc_list, abc_arr);

		abc_list.addAfter(abc_list.getHead(), "D");
		abc_arr = new String[] { "1", "D", "A", "B", "C" };
		TestUtils.verifyDLL("Add after in between", abc_list, abc_arr);

		abc_list.addAfter(abc_list.getTail(), "2");
		abc_arr = new String[] { "1", "D", "A", "B", "C", "2" };
		TestUtils.verifyDLL("Add after at tail", abc_list, abc_arr);

		abc_list = new DLL<String>();
		abc_list.addAfter(null, "1");
		abc_arr = new String[] { "1" };
		TestUtils.verifyDLL("Add after empty list", abc_list, abc_arr);
	}

	/** Test remove implementation */
	public static void testDLLRemove() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		abc_list.remove(abc_list.getHead().getNext());
		abc_arr = new String[] { "A", "C" };
		TestUtils.verifyDLL("Remove in between", abc_list, abc_arr);

		abc_list.remove(abc_list.getTail());
		abc_arr = new String[] { "A" };
		TestUtils.verifyDLL("Remove after at tail", abc_list, abc_arr);

		abc_list.remove(abc_list.getHead());
		abc_arr = new String[] {};
		TestUtils.verifyDLL("Remove after at head", abc_list, abc_arr);
	}

	/** Test size implementation */
	public static void testDLLSize() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		TestCode.runTest("Size 3", abc_list.size() == 3);

		abc_list = new DLL<String>();
		TestCode.runTest("Size 0", abc_list.size() == 0);
	}

	/** Test add before implementation */
	public static void testAddBefore() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);

		abc_list.addBefore(null, "1");
		abc_arr = new String[] { "A", "B", "C", "1" };
		TestUtils.verifyDLL("Add after at tail", abc_list, abc_arr);

		abc_list.addBefore(abc_list.getHead(), "D");
		abc_arr = new String[] { "D", "A", "B", "C", "1" };
		TestUtils.verifyDLL("Add after at head", abc_list, abc_arr);

		abc_list.addBefore(abc_list.getTail(), "2");
		abc_arr = new String[] { "D", "A", "B", "C", "2", "1" };
		TestUtils.verifyDLL("Add after before tail", abc_list, abc_arr);

		abc_list = new DLL<String>();
		abc_list.addBefore(null, "1");
		abc_arr = new String[] { "1" };
		TestUtils.verifyDLL("Add after empty list", abc_list, abc_arr);
	}

	/** Test copy constructor implementation */
	public static void testDLLCopy() {
		DLL<String> abc_list = new DLL<String>();
		DLL<String> abc_list_copy = new DLL<String>(abc_list);
		TestUtils.verifyDLL("Copy list empty", abc_list_copy, new String[] {});

		String[] abc_arr = { "A", "B", "C" };
		abc_list = TestUtils.makeDLL(abc_arr);
		abc_list_copy = new DLL<String>(abc_list);
		TestUtils.verifyDLL("Copy list 3 elements", abc_list_copy, abc_arr);

		abc_arr = new String[] { "A", "B" };
		abc_list = TestUtils.makeDLL(abc_arr);
		abc_list_copy = new DLL<String>(abc_list);
		TestUtils.verifyDLL("Copy list 2 elements", abc_list_copy, abc_arr);

		abc_arr = new String[] { "A" };
		abc_list = TestUtils.makeDLL(abc_arr);
		abc_list_copy = new DLL<String>(abc_list);
		TestUtils.verifyDLL("Copy list 1 elements", abc_list_copy, abc_arr);
	}

	/** Test subseq by copy implementation */
	public static void testDLLSubseqByCopy() {
		DLL<String> abc_list = new DLL<String>();
		DLL<String> abc_list_subseq = abc_list.subseqByCopy(null, 0);
		TestUtils.verifyDLL("Subseq subseq empty", abc_list_subseq, new String[] {});

		String[] abc_arr = { "A", "B", "C" };
		abc_list = TestUtils.makeDLL(abc_arr);

		abc_arr = new String[] { "A" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 1);
		TestUtils.verifyDLL("Subseq list 1 elements", abc_list_subseq, abc_arr);
		TestUtils.verifyDLL("Original list", abc_list, new String[] { "A", "B", "C" });

		abc_arr = new String[] { "A", "B" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 2);
		TestUtils.verifyDLL("Subseq list 2 elements", abc_list_subseq, abc_arr);

		abc_arr = new String[] { "A", "B", "C" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 3);
		TestUtils.verifyDLL("Subseq list 3 elements", abc_list_subseq, abc_arr);

		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 4);
	}

	/** Test splice by copy implementation */
	public static void testDLLSpliceByCopy() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);
		String[] num_arr = { "1", "2", "3" };
		DLL<String> num_list = TestUtils.makeDLL(num_arr);
		String[] test_arr = { "A", "1", "2", "3", "B", "C" };
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifyDLL("Splice", abc_list, test_arr);

		abc_list = new DLL<String>();
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifyDLL("Splice into empty list", abc_list, num_arr);

		TestUtils.verifyDLL("Splice check copy", num_list, num_arr);

		abc_list = TestUtils.makeDLL(abc_arr);
		num_list = new DLL<String>();
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifyDLL("Splice from empty list", abc_list, abc_arr);
	}

	/** Test splice by transfer implementation */
	public static void testDLLSpliceByTransfer() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);
		String[] num_arr = { "1", "2", "3" };
		DLL<String> num_list = TestUtils.makeDLL(num_arr);
		String[] test_arr = { "A", "B", "C", "1", "2", "3" };
		abc_list.spliceByTransfer(num_list, abc_list.getTail());
		TestUtils.verifyDLL("Splice by Transfer 1", abc_list, test_arr);
		TestUtils.verifyDLL("Splice by Transfer check list 1", num_list, new String[] {});

		abc_list = new DLL<String>();
		num_list = TestUtils.makeDLL(num_arr);
		abc_list.spliceByTransfer(num_list, abc_list.getHead());
		TestUtils.verifyDLL("Splice by Transfer 2", abc_list, num_arr);
		TestUtils.verifyDLL("Splice by Transfer check list 2", num_list, new String[] {});

		abc_list = TestUtils.makeDLL(abc_arr);
		num_list = TestUtils.makeDLL(num_arr);
		abc_list.spliceByTransfer(num_list, null);
		test_arr = new String[] { "1", "2", "3", "A", "B", "C" };
		TestUtils.verifyDLL("Splice by Transfer 3", abc_list, test_arr);
		TestUtils.verifyDLL("Splice by Transfer check list 3", num_list, new String[] {});

		abc_list = TestUtils.makeDLL(abc_arr);
		num_list = TestUtils.makeDLL(num_arr);
		abc_list.spliceByTransfer(num_list, abc_list.getHead());
		test_arr = new String[] { "A", "1", "2", "3", "B", "C" };
		TestUtils.verifyDLL("Splice by Transfer 4", abc_list, test_arr);
		TestUtils.verifyDLL("Splice by Transfer check list 4", num_list, new String[] {});
	}

	/** Test subseq by transfer implementation */
	public static void testDLLSubseqByTransfer() {
		String[] abc_arr = { "A", "B", "C" };
		DLL<String> abc_list = TestUtils.makeDLL(abc_arr);
		DLL<String> sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getTail());
		TestUtils.verifyDLL("Subseq by Transfer 1", sub_list, abc_arr);
		TestUtils.verifyDLL("Subseq by Transfer check list 1", abc_list, new String[] {});

		abc_list = TestUtils.makeDLL(abc_arr);
		String[] test_arr_1 = { "B", "C" };
		String[] test_arr_2 = { "A" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead().getNext(), abc_list.getTail());
		TestUtils.verifyDLL("Subseq by Transfer 2", sub_list, test_arr_1);
		TestUtils.verifyDLL("Subseq by Transfer check list 2", abc_list, test_arr_2);

		abc_list = TestUtils.makeDLL(abc_arr);
		test_arr_1 = new String[] { "A", "B" };
		test_arr_2 = new String[] { "C" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getHead().getNext());
		TestUtils.verifyDLL("Subseq by Transfer 3", sub_list, test_arr_1);
		TestUtils.verifyDLL("Subseq by Transfer check list 3", abc_list, test_arr_2);

		abc_list = TestUtils.makeDLL(abc_arr);
		test_arr_1 = new String[] { "A" };
		test_arr_2 = new String[] { "B", "C" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getHead());
		TestUtils.verifyDLL("Subseq by Transfer 4", sub_list, test_arr_1);
		TestUtils.verifyDLL("Subseq by Transfer check list 4", abc_list, test_arr_2);

		abc_list = TestUtils.makeDLL(abc_arr);
		test_arr_1 = new String[] { "B" };
		test_arr_2 = new String[] { "A", "C" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead().getNext(), abc_list.getTail().getPrevious());
		TestUtils.verifyDLL("Subseq by Transfer 5", sub_list, test_arr_1);
		TestUtils.verifyDLL("Subseq by Transfer check list 5", abc_list, test_arr_2);

		abc_list = TestUtils.makeDLL(abc_arr);
		sub_list = abc_list.subseqByTransfer(null, abc_list.getHead());
	}

	public static void main(String[] args) {

	}
}