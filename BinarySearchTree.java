/**
 * A collection of elements, stored in a tree in order.
 * 
 * @author Chad Hogg
 * @param <E> The type of element stored in the tree.
 */
public class BinarySearchTree<E extends Comparable<E>> {

	/**
	 * A node in a binary tree.
	 * 
	 * @author Chad Hogg
	 * @param <E> The type of data stored in the node.
	 */
	public static class BTNode<E> {

		/** The piece of data stored in this node. */
		public E data;
		/** The left subtree of this node. */
		public BTNode<E> left;
		/** The right subtree of this node. */
		public BTNode<E> right;

		/**
		 * Creates a new node.
		 * 
		 * @param data  The data to store.
		 * @param left  The left subtree.
		 * @param right The right subtree.
		 */
		public BTNode(E data, BTNode<E> left, BTNode<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	// Class invariants:
	// For every node X, the value stored in all of X's left descendants is <= the
	// value stored in X.
	// For every node X, the value stored in all of X's right descendants is > the
	// value stored in X.

	/** The root of the entire tree (or null if empty). */
	private BTNode<E> overallRoot;

	/**
	 * Initializes a new, empty tree.
	 */
	public BinarySearchTree() {
		overallRoot = null;
	}

	/**
	 * Adds a new element to the tree.
	 * 
	 * @param value The element to add.
	 * @postcondition The tree contains one more copy of the element than it used
	 *                to.
	 */
	public void add(E value) {
		add(value, overallRoot);
	}

	/**
	 * Adds a new element to a subtree, then returns the (possibly new) root of that
	 * subtree.
	 * 
	 * @param value       The element to add.
	 * @param subtreeRoot The root of a subtree within this tree (could be null).
	 * @return The new root of that subtree (a new node if subtreeRoot was null,
	 *         otherwise unchanged).
	 * @postcondition The subtree contains one more copy of the element than it used
	 *                to.
	 */
	private BTNode<E> add(E value, BTNode<E> subtreeRoot) {
		// base case
		if (size() == 0) {
			BTNode<E> newNode = new BTNode<E>(value, null, null);
			overallRoot = newNode;
			return overallRoot;

		} else if (value.compareTo(subtreeRoot.data) <= 0) {
			if (subtreeRoot.left == null) {
				BTNode<E> newNode = new BTNode<E>(value, null, null);
				subtreeRoot.left = newNode;
				return subtreeRoot.left;
			} else {
				return add(value, subtreeRoot.left);
			}
		} else if (subtreeRoot.right == null) {
			BTNode<E> newNode = new BTNode<E>(value, null, null);
			subtreeRoot.right = newNode;
			return subtreeRoot.right;
		} else {
			return add(value, subtreeRoot.right);
		}
	}

	/**
	 * Checks whether or not this tree contains an element.
	 * 
	 * @param value The element to look for.
	 * @return True if the tree contains at least one copy of the element, or false.
	 */
	public boolean contains(E value) {
		return contains(value, overallRoot);
	}

	/**
	 * Checks whether or not a subtree contains an element.
	 * 
	 * @param value       The element to look for.
	 * @param subtreeRoot The root of a subtree within this tree (could be null).
	 * @return True if that subtree contains at least one copy of the element, or
	 *         false.
	 */
	private boolean contains(E value, BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return false;
		}
		if (subtreeRoot.data.compareTo(value) == 0) {
			return true;
		} else if (subtreeRoot.data.compareTo(value) < 0) {
			return contains(value, subtreeRoot.right);
		} else if (subtreeRoot.data.compareTo(value) > 0) {
			return contains(value, subtreeRoot.left);
		} else {
			return false;
		}
	}

	/**
	 * Removes one copy of an element from the tree, if at least one exists.
	 * 
	 * @param value The element to remove.
	 * @postcondition If the tree contained at least one copy of the element, it has
	 *                one fewer.
	 */
	public void remove(E value) {
		overallRoot = remove(value, overallRoot);
	}

	/**
	 * Removes one copy of an element from a subtree, if at least one exists.
	 * 
	 * @param value       The element to remove.
	 * @param subtreeRoot The root of a subtree in which the element should be
	 *                    removed.
	 * @return The new root of that subtree (unchanged, unless node was the one
	 *         removed).
	 * @postcondition If the subtree contained at least one copy of the element, it
	 *                has one fewer.
	 */
	private BTNode<E> remove(E value, BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return null;
		}
		if (value.equals(subtreeRoot.data)) {
			// if the node has no children...
			if (subtreeRoot.left == null && subtreeRoot.right == null) {
				return null;
			}
			// only has one child...
			if (subtreeRoot.right == null) {
				return subtreeRoot.left;
			}

			if (subtreeRoot.left == null) {
				return subtreeRoot.right;
			}
			// if it has two children
			// find value to replace
			E largest = findLargest(subtreeRoot.right);
			// replace
			subtreeRoot.data = largest;
			// delete from it's original spot
			subtreeRoot.left = remove(largest, subtreeRoot.left);
			return subtreeRoot;

		}

		// traverse tree
		if (value.compareTo(subtreeRoot.data) <= 0) {
			subtreeRoot.left = remove(value, subtreeRoot.left);
			return subtreeRoot;
		} else {
			subtreeRoot.right = remove(value, subtreeRoot.right);
			return subtreeRoot;
		}
	}

	/**
	 * Finds the largest value in a subtree.
	 * 
	 * @param subtreeRoot The root of the subtree whose largest value is desired.
	 * @return The largest element in that subtree.
	 */
	private E findLargest(BTNode<E> subtreeRoot) {
		if (subtreeRoot.right == null) {
			if (subtreeRoot.left == null) {
				return subtreeRoot.data;
			} else {
				return findLargest(subtreeRoot.left);
			}
		}
		return null;
	}

	/**
	 * Gets the number of nodes in the tree.
	 * 
	 * @return The number of nodes in the tree.
	 */
	public int size() {
		return size(overallRoot);
	}

	/**
	 * Gets the number of nodes in a subtree.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return The number of nodes in that subtree.
	 */
	private int size(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return 0;
		} else {
			return 1 + size(subtreeRoot.left) + size(subtreeRoot.right);
		}
	}

	/**
	 * Gets the depth of the tree.
	 * 
	 * @return The length of the longest path from overall root to a leaf.
	 */
	public int depth() {
		return depth(overallRoot);
	}

	/**
	 * Gets the depth of a subtree.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return The length of the longest path from subtree's root to a leaf.
	 */
	private int depth(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return -1;
		} else {
			return 1 + Math.max(depth(subtreeRoot.left), depth(subtreeRoot.right));
		}
	}

	/**
	 * Gets a String containing all of the elements in pre-order.
	 * 
	 * @return A String containing all of the elements in pre-order.
	 */
	public String getElementsPre() {
		return getElementsPre(overallRoot);
	}

	/**
	 * Gets a String containing all of the elements of a subtree in pre-order.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return A String containing all of the elements of the subtree in pre-order.
	 */
	private String getElementsPre(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return "";
		} else {
			return subtreeRoot.data + " " + getElementsPre(subtreeRoot.left) + getElementsPre(subtreeRoot.right);
		}
	}

	/**
	 * Gets a String containing all of the elements in post-order.
	 * 
	 * @return A String containing all of the elements in post-order.
	 */
	public String getElementsPost() {
		return getElementsPost(overallRoot);
	}

	/**
	 * Gets a String containing all of the elements of a subtree in post-order.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return A String containing all of the elements of the subtree in post-order.
	 */
	private String getElementsPost(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return "";
		} else {
			return getElementsPost(subtreeRoot.left) + getElementsPost(subtreeRoot.right) + subtreeRoot.data + " ";
		}
	}

	/**
	 * Gets a String containing all of the elements in in-order.
	 * 
	 * @return A String containing all of the elements in in-order.
	 */
	public String getElementsIn() {
		return getElementsIn(overallRoot);
	}

	/**
	 * Gets a String containing all of the elements of a subtree in in-order.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return A String containing all of the elements of the subtree in in-order.
	 */
	private String getElementsIn(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return "";
		} else {
			return getElementsIn(subtreeRoot.left) + subtreeRoot.data + " " + getElementsIn(subtreeRoot.right);
		}
	}

	//// Methods for converting to string ////

	/**
	 * Finds the number of characters in the string representation of the largest
	 * element in a subtree.
	 * 
	 * @param subtreeRoot The root of the subtree.
	 * @return The width of the widest element, or 0 if there are no elements.
	 */
	private int widthOfWidestElement(BTNode<E> subtreeRoot) {
		if (subtreeRoot == null) {
			return 0;
		} else {
			int width = subtreeRoot.data.toString().length();
			width = Math.max(width, widthOfWidestElement(subtreeRoot.left));
			width = Math.max(width, widthOfWidestElement(subtreeRoot.right));
			return width;
		}
	}

	/**
	 * Appends some number of copies of a String to a StringBuilder.
	 * 
	 * @param builder The StringBuilder.
	 * @param toAdd   The String to append.
	 * @param copies  The number of copies to append, which may not be negative.
	 * @postcondition The toAdd String has been appended to builder copies number of
	 *                times.
	 */
	private static void appendCopies(StringBuilder builder, String toAdd, int copies) {
		if (copies < 0) {
			throw new IllegalArgumentException("Cannot make negative number of copies.");
		}
		for (int counter = 0; counter < copies; counter++) {
			builder.append(toAdd);
		}
	}

	/**
	 * Appends a String to a StringBuilder, center-justified in a certain field
	 * width.
	 * 
	 * @param builder    The StringBuilder to which things will be appended.
	 * @param toAdd      The String to append.
	 * @param fieldWidth The desired minimum width of the field.
	 * @postcondition toAdd has been appended to builder, as well as enough spaces
	 *                on either side to take up at least fieldWidth characters.
	 */
	private static void appendCenter(StringBuilder builder, String toAdd, int fieldWidth) {
		int missingSpaces = fieldWidth - toAdd.length();
		appendCopies(builder, " ", Math.max(0, missingSpaces / 2));
		builder.append(toAdd);
		appendCopies(builder, " ", Math.max(0, (missingSpaces + 1) / 2));
	}

	/**
	 * Appends information about all nodes on a certain level of the tree to a
	 * StringBuilder.
	 * 
	 * @param builder     The StringBuilder to which the information will be
	 *                    appended.
	 * @param subtreeRoot The root of the subtree whose nodes should be appended.
	 * @param rootLevel   The level within the tree of the subtree root.
	 * @param targetLevel The level within the tree that is supposed to be appended.
	 * @param spacing     The width of elements.
	 * @param totalHeight The height of the tree.
	 * @postcondition Information about the requested part of the tree has been
	 *                appended to the StringBuilder.
	 */
	private void appendLevelString(StringBuilder builder, BTNode<E> subtreeRoot, int rootLevel, int targetLevel,
			int spacing, int totalHeight) {
		int spacesAfter = spacing * (int) (Math.pow(2, totalHeight - targetLevel) - 1) * 2;
		if (rootLevel == targetLevel) {
			appendCenter(builder, subtreeRoot.data.toString(), spacing * 2);
			appendCopies(builder, " ", spacesAfter);
		} else {
			if (subtreeRoot.left == null) {
				int spaces = (spacing * 2 + spacesAfter) * (int) Math.pow(2, targetLevel - rootLevel - 1);
				appendCopies(builder, " ", spaces);
			} else {
				appendLevelString(builder, subtreeRoot.left, rootLevel + 1, targetLevel, spacing, totalHeight);
			}
			if (subtreeRoot.right == null) {
				int spaces = (spacing * 2 + spacesAfter) * (int) Math.pow(2, targetLevel - rootLevel - 1);
				appendCopies(builder, " ", spaces);
			} else {
				appendLevelString(builder, subtreeRoot.right, rootLevel + 1, targetLevel, spacing, totalHeight);
			}
		}
	}

	@Override
	public String toString() {
		if (size() > 0) {
			StringBuilder builder = new StringBuilder();
			int height = depth();
			int spacing = widthOfWidestElement(overallRoot) + 1;
			appendCopies(builder, " ", spacing * (int) (Math.pow(2, height) - 1));
			appendCenter(builder, "root", spacing * 2);
			builder.append("\n");
			for (int level = 0; level <= height; level++) {
				int spacesBefore = spacing * (int) (Math.pow(2, height - level) - 1);
				appendCopies(builder, " ", spacesBefore);
				appendLevelString(builder, overallRoot, 0, level, spacing, height);
				builder.append("\n");
			}
			return builder.toString();
		} else {
			return "empty tree";
		}
	}

}
