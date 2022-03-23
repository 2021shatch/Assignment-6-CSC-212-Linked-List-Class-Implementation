/**
 * Class to test singly linked list methods
 *
 * @author Sabrina Hatch & Anh Nguyen
 * @version Spring 2022
 */
public class SLLTest {
	/** test SLL phase one */
	public static void testSLLPhaseOne() {
		SLL<Character> testSLL = new SLL<Character>();

		// tests the SLL constructor
		TestCode.beginTest("Phase1SLL: Empty List");
		TestCode.subTest("Empty list", testSLL.isEmpty());
		TestCode.subTest("Head", testSLL.getHead() == null);
		TestCode.subTest("Tail", testSLL.getTail() == null);
		TestCode.subTest("Head tail reference", testSLL.getTail() == testSLL.getHead());
		System.out.println("SLL " + testSLL);
		TestCode.concludeTest();

		// tests that addFirst works
		testSLL.addFirst('A');
		TestCode.beginTest("Phase1SLL: One Element");
		TestCode.subTest("Empty list", !testSLL.isEmpty());
		TestCode.subTest("Head tail reference", testSLL.getTail() == testSLL.getHead());
		TestCode.subTest("Tail Next", testSLL.getTail().getNext() == null);
		TestCode.subTest("Head", testSLL.getHead().getData() == 'A');
		TestCode.subTest("Head Next", testSLL.getHead().getNext() == null);
		System.out.println("SLL " + testSLL);
		TestCode.concludeTest();

		testSLL.addFirst('B');
		TestCode.beginTest("Phase1SLL: Two Element");
		TestCode.subTest("Empty list", !testSLL.isEmpty());
		TestCode.subTest("Head tail reference", testSLL.getTail() == testSLL.getHead().getNext());
		TestCode.subTest("Tail Next", testSLL.getTail().getNext() == null);
		TestCode.subTest("Head", testSLL.getHead().getData() == 'B');
		TestCode.subTest("Head Next", testSLL.getHead().getNext().getData() == 'A');
		TestCode.subTest("Tail", testSLL.getTail().getData() == 'A');
		System.out.println("SLL " + testSLL);
		TestCode.concludeTest();

		testSLL.addFirst('C');
		TestCode.beginTest("Phase1SLL: Two Element");
		TestCode.subTest("Empty list", !testSLL.isEmpty());
		TestCode.subTest("Head tail reference", testSLL.getTail() == testSLL.getHead().getNext().getNext());
		TestCode.subTest("Tail Next", testSLL.getTail().getNext() == null);
		TestCode.subTest("Head", testSLL.getHead().getData() == 'C');
		TestCode.subTest("Head Next", testSLL.getHead().getNext().getData() == 'B');
		TestCode.subTest("Head Next", testSLL.getHead().getNext().getNext().getData() == 'A');
		TestCode.subTest("Tail", testSLL.getTail().getData() == 'A');
		System.out.println("SLL " + testSLL);
		TestCode.concludeTest();
	}

	/** test remove first for sll */
	public static void testSLLRemoveFirst() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		abc_list.removeFirst();
		abc_arr = new String[] { "B", "C" };
		TestUtils.verifySLL("Remove first: List of 3", abc_list, abc_arr);

		abc_list.removeFirst();
		abc_arr = new String[] { "C" };
		TestUtils.verifySLL("Remove first: List of 2", abc_list, abc_arr);

		abc_list.removeFirst();
		abc_arr = new String[] {};
		TestUtils.verifySLL("Remove first: List of 1", abc_list, abc_arr);

		abc_list.removeFirst();
	}

	/** test add last for sll */
	public static void testSLLAddLast() {
		SLL<String> abc_list = new SLL<String>();
		// edgecase where the list starts empty
		abc_list.addLast("A");
		String[] abc_arr = { "A" };
		TestUtils.verifySLL("Add last: List of 0", abc_list, abc_arr);

		abc_list.addLast("B");
		abc_arr = new String[] { "A", "B" };
		TestUtils.verifySLL("Add last: List of 1", abc_list, abc_arr);

		abc_list.addLast("C");
		abc_arr = new String[] { "A", "B", "C" };
		TestUtils.verifySLL("Add last: List of 2", abc_list, abc_arr);
	}

	/** test remove last for sll */
	public static void testSLLRemoveLast() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		abc_list.removeLast();
		abc_arr = new String[] { "A", "B" };
		TestUtils.verifySLL("Remove last: List of 3", abc_list, abc_arr);

		// tests the edgecase where the tail and head are the same
		abc_list.removeLast();
		abc_arr = new String[] { "A" };
		TestUtils.verifySLL("Remove last: List of 2", abc_list, abc_arr);

		abc_list.removeLast();
		abc_arr = new String[] {};
		TestUtils.verifySLL("Remove last: List of 1", abc_list, abc_arr);
		// edgecase where the list is null and you try to remove from it
		abc_list.removeLast();
	}

	/** test add after for all */
	public static void testSLLAddAfter() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		abc_list.addAfter(null, "1");
		abc_arr = new String[] { "1", "A", "B", "C" };
		TestUtils.verifySLL("Add after at head", abc_list, abc_arr);

		abc_list.addAfter(abc_list.getHead(), "D");
		abc_arr = new String[] { "1", "D", "A", "B", "C" };
		TestUtils.verifySLL("Add after in between", abc_list, abc_arr);

		abc_list.addAfter(abc_list.getTail(), "2");
		abc_arr = new String[] { "1", "D", "A", "B", "C", "2" };
		TestUtils.verifySLL("Add after at tail", abc_list, abc_arr);

		abc_list = new SLL<String>();
		abc_list.addAfter(null, "1");
		abc_arr = new String[] { "1" };
		TestUtils.verifySLL("Add after empty list", abc_list, abc_arr);
	}

	/** test remove last for sll */
	public static void testSLLremoveAfter() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		abc_list.removeAfter(abc_list.getHead());
		abc_arr = new String[] { "A", "C" };
		TestUtils.verifySLL("removeAfter in between", abc_list, abc_arr);

		abc_list.removeAfter(abc_list.getHead().getNext());
		abc_arr = new String[] { "A" };
		TestUtils.verifySLL("removeAfter after at tail", abc_list, abc_arr);

		abc_list.removeAfter(null);
		abc_arr = new String[] {};
		TestUtils.verifySLL("removeAfter after at head", abc_list, abc_arr);
	}

	public static void testSLLSize() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		TestCode.runTest("Size 3", abc_list.size() == 3);

		abc_list = new SLL<String>();
		TestCode.runTest("Size 0", abc_list.size() == 0);
	}

	public static void testSLLRemoveAfter() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);

		abc_list.removeAfter(abc_list.getHead());
		abc_arr = new String[] { "A", "C" };
		TestUtils.verifySLL("Remove in between", abc_list, abc_arr);

		abc_list.removeAfter(abc_list.getHead());
		abc_arr = new String[] { "A" };
		TestUtils.verifySLL("Remove after at tail", abc_list, abc_arr);

		abc_list.removeAfter(null);
		abc_arr = new String[] {};
		TestUtils.verifySLL("Remove after at head and at one element", abc_list, abc_arr);
	}

	/** Test copy constructor implementation */
	public static void testSLLCopy() {
		SLL<String> abc_list = new SLL<String>();
		SLL<String> abc_list_copy = new SLL<String>(abc_list);
		TestUtils.verifySLL("Copy list empty", abc_list_copy, new String[] {});

		String[] abc_arr = { "A", "B", "C" };
		abc_list = TestUtils.makeSLL(abc_arr);
		abc_list_copy = new SLL<String>(abc_list);
		TestUtils.verifySLL("Copy list 3 elements", abc_list_copy, abc_arr);

		abc_arr = new String[] { "A", "B" };
		abc_list = TestUtils.makeSLL(abc_arr);
		abc_list_copy = new SLL<String>(abc_list);
		TestUtils.verifySLL("Copy list 2 elements", abc_list_copy, abc_arr);

		abc_arr = new String[] { "A" };
		abc_list = TestUtils.makeSLL(abc_arr);
		abc_list_copy = new SLL<String>(abc_list);
		TestUtils.verifySLL("Copy list 1 elements", abc_list_copy, abc_arr);
	}

	/** Test subseq by copy implementation */
	public static void testSLLSubseqByCopy() {
		SLL<String> abc_list = new SLL<String>();
		SLL<String> abc_list_subseq = abc_list.subseqByCopy(null, 0);
		TestUtils.verifySLL("Subseq subseq empty", abc_list_subseq, new String[] {});

		String[] abc_arr = { "A", "B", "C" };
		abc_list = TestUtils.makeSLL(abc_arr);

		abc_arr = new String[] { "A" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 1);
		TestUtils.verifySLL("Subseq list 1 elements", abc_list_subseq, abc_arr);
		TestUtils.verifySLL("Original list", abc_list, new String[] { "A", "B", "C" });

		abc_arr = new String[] { "A", "B" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 2);
		TestUtils.verifySLL("Subseq list 2 elements", abc_list_subseq, abc_arr);

		abc_arr = new String[] { "A", "B", "C" };
		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 3);
		TestUtils.verifySLL("Subseq list 3 elements", abc_list_subseq, abc_arr);

		abc_list_subseq = abc_list.subseqByCopy(abc_list.getHead(), 4);
	}

	/** Test splice by copy implementation */
	public static void testSLLSpliceByCopy() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);
		String[] num_arr = { "1", "2", "3" };
		SLL<String> num_list = TestUtils.makeSLL(num_arr);
		String[] test_arr = { "A", "1", "2", "3", "B", "C" };
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifySLL("Splice", abc_list, test_arr);

		abc_list = new SLL<String>();
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifySLL("Splice into empty list", abc_list, num_arr);

		TestUtils.verifySLL("Splice check copy", num_list, num_arr);

		abc_list = TestUtils.makeSLL(abc_arr);
		num_list = new SLL<String>();
		abc_list.spliceByCopy(num_list, abc_list.getHead());
		TestUtils.verifySLL("Splice from empty list", abc_list, abc_arr);
	}

	/** Test splice by transfer implementation */
	public static void testSLLSpliceByTransfer() {
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);
		String[] num_arr = { "1", "2", "3" };
		SLL<String> num_list = TestUtils.makeSLL(num_arr);
		String[] test_arr = { "A", "B", "C", "1", "2", "3" };
		abc_list.spliceByTransfer(num_list, abc_list.getTail());
		TestUtils.verifySLL("Splice by Transfer 1", abc_list, test_arr);
		TestUtils.verifySLL("Splice by Transfer check list 1", num_list, new String[] {});

		abc_list = new SLL<String>();
		num_list = TestUtils.makeSLL(num_arr);
		abc_list.spliceByTransfer(num_list, abc_list.getHead());
		TestUtils.verifySLL("Splice by Transfer 2", abc_list, num_arr);
		TestUtils.verifySLL("Splice by Transfer check list 2", num_list, new String[] {});

		abc_list = TestUtils.makeSLL(abc_arr);
		num_list = TestUtils.makeSLL(num_arr);
		abc_list.spliceByTransfer(num_list, null);
		test_arr = new String[] { "1", "2", "3", "A", "B", "C" };
		TestUtils.verifySLL("Splice by Transfer 3", abc_list, test_arr);
		TestUtils.verifySLL("Splice by Transfer check list 3", num_list, new String[] {});

		abc_list = TestUtils.makeSLL(abc_arr);
		num_list = TestUtils.makeSLL(num_arr);
		abc_list.spliceByTransfer(num_list, abc_list.getHead());
		test_arr = new String[] { "A", "1", "2", "3", "B", "C" };
		TestUtils.verifySLL("Splice by Transfer 4", abc_list, test_arr);
		TestUtils.verifySLL("Splice by Transfer check list 4", num_list, new String[] {});
	}

	/** Test subseq by transfer implementation */
	public static void testSLLSubseqByTransfer() {
		// tests if you are taking out from head to tail
		String[] abc_arr = { "A", "B", "C" };
		SLL<String> abc_list = TestUtils.makeSLL(abc_arr);
		SLL<String> sub_list = abc_list.subseqByTransfer(null, abc_list.getTail());
		TestUtils.verifySLL("Subseq by Transfer 1", sub_list, abc_arr);
		TestUtils.verifySLL("Subseq by Transfer check list 1", abc_list, new String[] {});

		// test to see if you are taking out from somewhere in seq to tail
		abc_list = TestUtils.makeSLL(abc_arr);
		String[] test_arr_1 = { "B", "C" };
		String[] test_arr_2 = { "A" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getTail());
		TestUtils.verifySLL("Subseq by Transfer 2", sub_list, test_arr_1);
		TestUtils.verifySLL("Subseq by Transfer check list 2", abc_list, test_arr_2);

		// tests to see if you are taking out from head to somewhere in the seq
		abc_list = TestUtils.makeSLL(abc_arr);
		test_arr_1 = new String[] { "A", "B" };
		test_arr_2 = new String[] { "C" };
		sub_list = abc_list.subseqByTransfer(null, abc_list.getHead().getNext());
		TestUtils.verifySLL("Subseq by Transfer 3", sub_list, test_arr_1);
		TestUtils.verifySLL("Subseq by Transfer check list 3", abc_list, test_arr_2);

		// tests to see what happens when you only take out one element
		abc_list = TestUtils.makeSLL(abc_arr);
		test_arr_1 = new String[] { "A" };
		test_arr_2 = new String[] { "B", "C" };
		sub_list = abc_list.subseqByTransfer(null, abc_list.getHead());
		TestUtils.verifySLL("Subseq by Transfer 4", sub_list, test_arr_1);
		TestUtils.verifySLL("Subseq by Transfer check list 4", abc_list, test_arr_2);

		// test to see what happens from somewhere in the seq to somewhere in the seq
		abc_list = TestUtils.makeSLL(abc_arr);
		test_arr_1 = new String[] { "B" };
		test_arr_2 = new String[] { "A", "C" };
		sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getHead().getNext());
		TestUtils.verifySLL("Subseq by Transfer 5", sub_list, test_arr_1);
		TestUtils.verifySLL("Subseq by Transfer check list 5", abc_list, test_arr_2);

		// tests for throwing an error in all cases
		abc_list = TestUtils.makeSLL(abc_arr);
		sub_list = abc_list.subseqByTransfer(abc_list.getHead(), null);
		// the first of the 2 tests for the edgecase where the user gives an OOB index
		// the second of the 2 tests for the edgecase where the user makes the afterHere
		// and toHere the same
		/*
		 * sub_list = abc_list.subseqByTransfer(abc_list.getHead(),
		 * abc_list.getTail().getNext());
		 * 
		 * sub_list = abc_list.subseqByTransfer(abc_list.getHead(), abc_list.getHead());
		 */

	}

	public static void main(String[] args) {

	}
}