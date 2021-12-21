
/**
 * A class that stores a list of doubles, keeping them in numerical order at all
 * times.
 * 
 * @author Beth Katz, Chad Hogg, Sam Noggle
 *
 */
public class SortedList {

	/**
	 * A node in a sorted list.
	 * 
	 * @author Chad Hogg
	 */
	private static class Node {
		/** The number stored in this node. */
		public double value;
		/** A reference to the node that comes after this one (null if none). */
		public Node next;

		/**
		 * Constructs a new Node.
		 * 
		 * @param value The number to store in the new node.
		 * @param next  The node that should come after the new one.
		 */
		public Node(double value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	// Class Invariant:
	// 1. listLength cannot be negative

	/** The first node of this list, or null if there is none. */
	private Node head;
	/** The number of nodes in this list. */
	private int listLength;

	/**
	 * Constructs a new, empty list.
	 * 
	 * @postcondition This list has no nodes.
	 */
	public SortedList() {
		listLength = 0;
		head = null;
	}

	/**
	 * Inserts a new value into this list, maintaining the sorted order.
	 * 
	 * @param value The value to add.
	 * @postcondition The new value has been added to the list, after all elements
	 *                that are smaller than it and before all elements that are
	 *                greater than it.
	 */
	public void insert(double value) {

		Node prevNode = getPrecedingNode(value);
		if (prevNode == null) {
			Node newNode = new Node(value, head);
			head = newNode;
		} else {
			Node newNode = new Node(value, prevNode.next);
			prevNode.next = newNode;
		}
		listLength++;

	}

	/**
	 * Gets the node that would come before the new one created to hold a value
	 * (also known as the last node that contains a number less than the value).
	 * 
	 * @param value A value that is going to be inserted.
	 * @return The last node that contains a number less than the value, or null if
	 *         no nodes contain numbers less than the value.
	 */
	private Node getPrecedingNode(double value) {
		if (head == null) {
			return head;
		}

		// head cannot be null now...
		Node prev = null;
		Node curr = head;

		// move forward to find the space
		for (int i = 1; i <= listLength; i++) {
			if (curr.value < value) {
				prev = curr;
				curr = curr.next;
			} else {
				continue;
			}
		}
		return prev;

	}

	/**
	 * Searches this list for a value.
	 * 
	 * @param value A number to search for.
	 * @return The first position at which that value appears (0 = first node, 1 =
	 *         second node, ...) or -1 if it not found anywhere.
	 */
	public int find(double value) {
		// variables
		int index = 0;
		Node curr = head;

		// check for null
		if (listLength == 0) {
			return -1;
		} else {
			// find the value (or not find it)
			while (index < listLength) {
				if (curr.value == value) {
					return index;
				} else {
					curr = curr.next;
				}
				index++;
			}
			return -1;
		}
	}

	/**
	 * Removes a node from this list.
	 * 
	 * @param index The position of the node to remove (0 = first node, 1 = second
	 *              node, ...).
	 * @return Whether or not such a node exists. (True if 0 >= index < size().)
	 * @postcondition If there was a node at that position, it has been removed.
	 */
	public boolean removeAt(int index) {
		Node prev = null;
		Node curr = head;

		// if only one in list
		if (listLength == 1 && index == 0) {
			listLength = 0;
			head = null;
			return true;

		}
		// if removing first
		if (index == 0 && listLength != 0) {
			Node removed = head.next;
			head.value = removed.value;
			head.next = removed.next;
			listLength--;
			return true;
		}

		if (index >= 0 && index < listLength && listLength != 0) {
			// get to the node @ index i guess
			for (int i = 0; i < index; i++) {
				prev = curr;
				curr = curr.next;
			}
			// removing it??????? i hope
			prev.next = curr.next;
			listLength--;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the number of elements in this list.
	 * 
	 * @return The number of elements in this list.
	 */
	public int size() {
		// this should work right?
		return listLength;
	}

	/**
	 * Gets a String representing this list.
	 * 
	 * @return A String like "( 2.0 4.0 5.0 )".
	 */
	@Override
	public String toString() {
		String answer = "( ";

		for (Node current = head; current != null; current = current.next) {
			answer += current.value + " ";
		}
		answer += ")";
		return answer;
	}
}
