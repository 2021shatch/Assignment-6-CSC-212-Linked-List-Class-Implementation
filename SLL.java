/**
 * Class to implement a singly linked list
 *
 * @author Sabrina Hatch & Anh Nguyen
 * @version Spring 2022
 */
public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase3SLL<T> {
	/** The head node of the list */
	private NodeSL<T> head;

	/** The tail node of the list */
	private NodeSL<T> tail;

	/** Constructor for an empty list */
	public SLL() {
		head = tail = null;
	}

	/** Method to check if a list is empty */
	public boolean isEmpty() {
		return (head == null);
	}

	/** Accessors to get head */
	public NodeSL<T> getHead() {
		return head;
	}

	/** Accessors to get tail */
	public NodeSL<T> getTail() {
		return tail;
	}

	/**
	 * Inserts the given item at the head of the list
	 * 
	 * @param v item to insert
	 */
	public void addFirst(T v) {
		NodeSL<T> newNode = new NodeSL<>(v, head);
		head = newNode;
		if (tail == null) {
			tail = head;
		}
	}

	/**
	 * @return returns a string representation of the list
	 */
	public String toString() {
		String listString = "[";
		for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
			listString = listString + item.getData() + ", ";
		}
		if (tail != null) {
			listString = listString + tail.getData();
		}
		listString = listString + "]";
		return listString;
	}

	/**
	 * Removes the given item from the head of the list
	 * 
	 * @return item that was removed
	 */
	public T removeFirst() {
		if (head == null) {
			throw new MissingElementException();
		}
		NodeSL<T> remove = head;
		// get next element and set it as the new head
		head = head.getNext();
		// remove old head link
		remove.setNext(null);
		// if list is empty then set tail to null
		if (head == null) {
			tail = null;
		}
		return remove.getData();
	}

	/**
	 * Inserts the given item at the tail of the list
	 * 
	 * @param v item to insert
	 */
	public void addLast(T v) {
		NodeSL<T> newNode = new NodeSL<T>(v, null);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	/** Remove the item at the tail of the list */
	public T removeLast() {
		// if list is empty
		if (head == null) {
			throw new MissingElementException();
		}
		// if list only has one element
		NodeSL<T> remove = tail;
		if (head == tail) {
			head = tail = null;
		}
		// traverses over each element in the list until they find the second to last
		// one, then exits the loop and sets the second to last's ref to null
		else {
			NodeSL<T> almostLast = head;
			while (almostLast.getNext().getNext() != null) {
				almostLast = almostLast.getNext();
			}
			almostLast.setNext(null);
			tail = almostLast;
		}
		return remove.getData();
	}

	/**
	 * Inserts the given item at after the given position
	 * 
	 * @param v item to insert
	 */
	public void addAfter(NodeSL<T> here, T v) {
		// if here is the tail
		if (here == tail) {
			addLast(v);
		}
		// if here = null, aka you want to make the new node the new head
		else if (here == null) {
			addFirst(v);
		}
		// if here is anywhere else in the sequence
		else {
			NodeSL<T> newNode = new NodeSL<>(v, here.getNext());
			here.setNext(newNode);
		}
	}

	/**
	 * Removes the node after the given position
	 * 
	 * @param here marks position to remove after
	 * @return item removed
	 */
	public T removeAfter(NodeSL<T> here) {
		// if here is the tail
		if (here == tail) {
			return removeLast();
		}
		// if here = null, aka you want to make the new node the new head
		else if (here == null) {
			return removeFirst();
		}
		// if here is anywhere else in the sequence
		else {

			NodeSL<T> temp = here.getNext();
			here.setNext(here.getNext().getNext());
			temp.setNext(null);
			return temp.getData();
		}

	}

	/** @return size of list */
	public int size() {
		NodeSL<T> temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.getNext();
		}
		return count;
	}

	/** Copy constructor makes a deep copy of the list */
	public SLL(SLL<T> orig) {
		// if the list is empty
		if (orig.getHead() == null) {
			head = tail = null;
		} else {
			NodeSL<T> pos = orig.getHead();
			head = new NodeSL<T>(pos.getData(), null);
			tail = head;
			for (pos = orig.getHead().getNext(); pos != null; pos = pos.getNext()) {
				tail.setNext(new NodeSL<T>(pos.getData(), null));
				tail = tail.getNext();
			}
		}
	}

	/**
	 * Makes a copy of elements from the original list
	 * 
	 * @param here starting point of copy
	 * @param n    number of items to copy
	 * @return the copied list
	 */
	public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
		SLL<T> copiedList = new SLL<T>();
		if (head == null) {
			return copiedList;
		} else {
			try {
				NodeSL<T> pos = here;
				for (int i = 0; i < n; i++) {
					copiedList.addLast(pos.getData());
					pos = pos.getNext();
				}
			} catch (NullPointerException e) {
				System.err.println("Not enough node to copy");
				System.exit(-1);
			}
		}
		return copiedList;

	}

	/**
	 * Places copy of the provided list into this after the specified node.
	 * 
	 * @param list      the list to splice in a copy of
	 * @param afterHere marks the position in this where the new list should go
	 */
	public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
		if (list.getHead() == this.head) {
			throw new SelfInsertException();
		}
		for (NodeSL<T> pos = list.getHead(); pos != null; pos = pos.getNext()) {
			addAfter(afterHere, pos.getData());
			if (afterHere == null) {
				afterHere = head;
			} else {
				afterHere = afterHere.getNext();
			}
		}

	}

	/**
	 * Extracts a subsequence of nodes and returns them as a new list
	 * 
	 * @param afterHere marks the node just before the extraction
	 * @param toHere    marks the node where the extraction ends
	 * @return the new list
	 */
	public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
		// if the index is OOB or if the list is empty
		if (head == null || toHere == null || toHere == afterHere) {
			throw new MissingElementException();
		} else {
			SLL<T> subseq = new SLL<T>();
			// if the subseq is the entire of the original list
			if (afterHere == null && toHere == tail) {
				subseq.head = head;
				subseq.tail = tail;
				head = tail = null;
			}
			// if the subseq starts at the head and ends somewhere in the seq
			else if (afterHere == null && toHere != tail) {
				subseq.head = head;
				subseq.tail = toHere;
				head = toHere.getNext();
				toHere.setNext(null);
			}
			// if the subseq starts somewhere in the seq and ends at the tail
			else if (afterHere != null && toHere == tail) {
				subseq.head = afterHere.getNext();
				subseq.tail = toHere;
				tail = afterHere;
				afterHere.setNext(null);

			}
			// if the list starts somewhere in the seq and ends somewhere in the seq
			else {
				subseq.head = afterHere.getNext();
				subseq.tail = toHere;
				afterHere.setNext(toHere.getNext());
				toHere.setNext(null);
			}
			return subseq;
		}
	}

	/**
	 * Takes the provided list and inserts its elements into this after the
	 * specified node. The inserted list ends up empty.
	 * 
	 * @param list      the list to splice in. Becomes empty after the call
	 * @param afterHere Marks the place where the new elements are inserted
	 */
	public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
		if (list.getHead() == this.head) {
			throw new SelfInsertException();
		}
		// when would the tail be null?
		if (tail != null) {
			// if you are inserting right before the head of the original list
			if (afterHere == null) {
				list.tail.setNext(head);
				head = list.head;
				// if you are inserting right after the tail
			} else if (afterHere == tail) {
				tail.setNext(list.head);
				tail = list.tail;
				// if you are inserting the list into this.list anywhere in the seq of this.list
			} else {
				list.tail.setNext(afterHere.getNext());
				afterHere.setNext(list.head);
			}
		}
		// if this is empty
		else {
			this.head = list.getHead();
			this.tail = list.getTail();
		}
		list.head = list.tail = null;
	}
}
