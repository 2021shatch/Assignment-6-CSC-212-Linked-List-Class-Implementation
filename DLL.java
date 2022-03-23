/**
 * Class to implement a doubly linked list and its functions
 *
 * @author Sabrina Hatch & Anh Nguyen
 * @version Spring 2022
 */
public class DLL<T> {
	/** The head node of the list */
	private NodeDL<T> head;

	/** The tail node of the list */
	private NodeDL<T> tail;

	/** Constructor for an empty list */
	public DLL() {
		head = tail = null;
	}

	/** Method to check if a list is empty */
	public boolean isEmpty() {
		return (head == null);
	}

	/** Accessors to get head */
	public NodeDL<T> getHead() {
		return head;
	}

	/** Accessors to get tail */
	public NodeDL<T> getTail() {
		return tail;
	}

	/**
	 * Inserts the given item at the head of the list
	 * 
	 * @param v item to insert
   * @return void N/A
	 */
	public void addFirst(T v) {
		NodeDL<T> newNode = new NodeDL<>(v, null, head);
		NodeDL<T> temp = head;
		head = newNode;
		if (tail == null) {
			tail = head;
		} else {
			temp.setPrevious(newNode);
		}
	}

	/**
   * @param no parameter N/A
	 * @return returns a string representation of the list
	 */
	public String toString() {
		String listString = "[";
		for (NodeDL<T> item = this.head; item != this.tail; item = item.getNext()) {
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
	 * @param no parameteres N/A
	 * @return v item removed
	 */
	public T removeFirst() throws MissingElementException {
		// if empty list then throw exception
		if (head == null) {
			throw new MissingElementException();
		}
		NodeDL<T> remove = head;
		// set new head
		head = head.getNext();
		// remove the old head link to the next element
		remove.setNext(null);
		// if list is empty then set tail to null
		if (head == null) {
			tail = null;
		}
		// update head previous link
		else {
			head.setPrevious(null);
		}
		return remove.getData();
	}

	/**
	 * Removes the given item from the tail of the list
	 * @param no parameters N/A
	 * @return item removed
	 */
	public T removeLast() throws MissingElementException {
		// if list is empty
		if (head == null) {
			throw new MissingElementException();
		}
		// if list is not empty
		NodeDL<T> remove = tail;
		tail = tail.getPrevious();
		remove.setPrevious(null);
		// if only one element in list
		if (tail == null) {
			head = null;
		}
		// if mult element in list
		else {
			tail.setNext(null);
		}
		return remove.getData();
	}

	/**
	 * Inserts the given item at the tail of the list
	 * 
	 * @param item to insert
   * @return void N/A
	 */
	public void addLast(T v) {
		NodeDL<T> newNode = new NodeDL<T>(v, tail, null);
		NodeDL<T> temp = tail;
		tail = newNode;
		// if list is empty
		if (head == null) {
			head = tail;
		}
		// if list is not empty
		else {
			temp.setNext(newNode);
		}
	}

	/**
	 * Inserts the given item after the specified node
	 * 
	 * @param here node to insert after
	 * @param v item to insert
	 */
	public void addAfter(NodeDL<T> here, T v) {
		// adding at the head
		if (here == null) {
			addFirst(v);
		}
		// adding at the tail
		else if (here == tail) {
			addLast(v);
		}
		// adding in between
		else {
			NodeDL<T> newNode = new NodeDL<T>(v, here, here.getNext());
			here.getNext().setPrevious(newNode);
			here.setNext(newNode);
		}
	}

	/**
	 * Removes the node
	 * 
	 * @param here marks position to remove
	 * @return item removed
	 */
	public T remove(NodeDL<T> here) throws MissingElementException {
		// remove at head
		if (here == head) {
			return removeFirst();
		}
		// remove at tail
		else if (here == tail) {
			return removeLast();
		}
		// remove at null
		else if (here == null) {
			throw new MissingElementException();
		}
		// remove within the list
		else {
			NodeDL<T> remove = here;
			here.getPrevious().setNext(here.getNext());
			here.getNext().setPrevious(here.getPrevious());
			here.setNext(null);
			here.setPrevious(null);
			return remove.getData();
		}
	}

	/**
	 * Returns a count of the number of elements in the list
	 * @param no parameters N/A
	 * @return current number of nodes
	 */
	public int size() {
		int counter = 0;
		// if list is empty
		if (head == null) {
			return counter;
		}
		// loop through items in list if non-empty
		for (NodeDL<T> item = head; item != tail; item = item.getNext()) {
			counter = counter + 1;
		}
		counter = counter + 1;
		return counter;
	}

	/**
	 * Inserts the given item before the specified node
	 * 
	 * @param here node to insert before
	 * @param v item to insert
	 */
	public void addBefore(NodeDL<T> here, T v) {
		// adding at the head
		if (here == head) {
			addFirst(v);
		}
		// adding at the tail
		else if (here == null) {
			addLast(v);
		}
		// adding in between
		else {
			NodeDL<T> newNode = new NodeDL<T>(v, here.getPrevious(), here);
			here.getPrevious().setNext(newNode);
			here.setPrevious(newNode);
		}
	}

	/** Copy constructor makes a deep copy of the list 
  *
  *@param the original doubly linked list
  */
	public DLL(DLL<T> orig) {
		// if empty
		if (orig.getHead() == null) {
			head = tail = null;
		}
		// if non-empty
		else {
			NodeDL<T> pos = orig.getHead();
			head = new NodeDL<T>(pos.getData(), null, null);
			tail = head;
			for (pos = orig.getHead().getNext(); pos != null; pos = pos.getNext()) {
				tail.setNext(new NodeDL<T>(pos.getData(), tail, null));
				tail = tail.getNext();
			}
		}
	}

	/**
	 * Makes a copy of elements from the original list
	 * 
	 * @param here starting point of copy
	 * @param n number of items to copy
	 * @return the copied list
	 */
	public DLL<T> subseqByCopy(NodeDL<T> here, int n) {
		DLL<T> copiedList = new DLL<T>();
		// if empty
		if (head == null) {
			return copiedList;
		}
		// if non-empty
		else {
			try {
				NodeDL<T> pos = here;
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
	 * @param list - the list to splice in a copy of
	 * @param afterHere marks the position in this where the new list should go
	 * @return void N/A
  */
	public void spliceByCopy(DLL<T> list, NodeDL<T> afterHere) throws SelfInsertException {
		if (list.getHead() == this.head) {
			throw new SelfInsertException();
		}
		for (NodeDL<T> pos = list.getHead(); pos != null; pos = pos.getNext()) {
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
	 * @param fromHere marks the node where the extraction begins
	 * @param toHere   marks the node where the extraction ends
	 * @return the new list
	 */
	public DLL<T> subseqByTransfer(NodeDL<T> fromHere, NodeDL<T> toHere) throws MissingElementException {
		if (head == null) {
			throw new MissingElementException();
		} else {
			DLL<T> subseq = new DLL<T>();
			subseq.head = fromHere;
			subseq.tail = toHere;
			if (fromHere == null || toHere == null) {
				throw new MissingElementException();
			}
			if (fromHere == head && toHere == tail) {
				head = tail = null;
			} else if (fromHere == head && toHere != tail) {
				head = toHere.getNext();
				head.setPrevious(null);
				subseq.tail.setNext(null);
			} else if (fromHere != head && toHere == tail) {
				tail = fromHere.getPrevious();
				tail.setNext(null);
				subseq.head.setPrevious(null);
			} else {
				fromHere.getPrevious().setNext(toHere.getNext());
				toHere.getNext().setPrevious(fromHere.getPrevious());
				fromHere.setPrevious(null);
				toHere.setNext(null);
			}
			return subseq;
		}
	}

	/**
	 * Takes the provided list and inserts its elements into this after the
	 * specified node. The inserted list ends up empty.
	 * 
	 * @param list the list to splice in. Becomes empty after the call
	 * @param afterHere Marks the place where the new elements are inserted
   * @return void N/A
	 */
	public void spliceByTransfer(DLL<T> list, NodeDL<T> afterHere) throws SelfInsertException {
		if (list.getHead() == this.head) {
			throw new SelfInsertException();
		}
		if (tail != null) {
			if (afterHere == null) {
				head.setPrevious(list.getTail());
				list.getTail().setNext(head);
				head = list.head;
			} else if (afterHere == tail) {
				tail.setNext(list.getHead());
				list.getHead().setPrevious(tail);
				tail = list.tail;
			} else {
				list.tail.setNext(afterHere.getNext());
				list.head.setPrevious(afterHere);
				afterHere.getNext().setPrevious(list.getTail());
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