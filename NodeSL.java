/**
 * Class to represent one node of a singly linked list
 *
 * @author Anonymous
 * @version Spring 2022
 */
public class NodeSL<T> {
	/** The data at the node */
	private T data;

	/** Link to the next node */
	private NodeSL<T> next;

	/**
	 * Constructor takes data and next node
	 * 
	 * @param data the value at the node
	 * @param next link to the next node
	 */
	public NodeSL(T data, NodeSL<T> next) {
		this.data = data;
		this.next = next;
	}

	/** @return the data at this node */
	public T getData() {
		return data;
	}

	/** @param d new data vaue */
	public void setData(T d) {
		this.data = d;
	}

	/** @return reference to the next node */
	public NodeSL<T> getNext() {
		return next;
	}

	/** @param d new next node */
	public void setNext(NodeSL<T> next) {
		this.next = next;
	}

}