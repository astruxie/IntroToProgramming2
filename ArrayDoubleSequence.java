import java.util.Arrays;

/**
 * A sequence of doubles stored in a partially-filled array.
 * 
 * @author Michael Main, Chad Hogg, Sam Noggle
 */
public class ArrayDoubleSequence implements DoubleSequence {
	// Invariant of the ArrayDoubleSequence class:
	// 1. The number of elements in the sequence is in the instance variable
	// itemCount.
	// 2. For an empty sequence (with no elements), we do not care what is
	// stored in any of data; for a non-empty sequence, the elements of the
	// sequence are stored in data[0] through data[itemCount-1], and we
	// don't care what's in the rest of data.
	// 3. If there is a current element, then it lies in data[currentIndex];
	// if there is no current element, then currentIndex equals itemCount.

	/** A partially-filled array containing the elements of this sequence. */
	private double[] data;
	/** The number of elements in this sequence. */
	private int itemCount;
	/**
	 * The index of the current element, or a copy of itemCount if there is none.
	 */
	private int currentIndex;

	/**
	 * Initializes an empty sequence with an initial capacity of 10.
	 * 
	 * @postcondition This sequence is empty and has a capacity of 10.
	 */
	public ArrayDoubleSequence() {
		data = new double[10];
		itemCount = 0;
		currentIndex = 0;
	}

	/**
	 * Initializes an empty sequence with a specified initial capacity.
	 * 
	 * @param initialCapacity The initial capacity of the new sequence, which must
	 *                        be non-negative.
	 * @postcondition The new sequence is empty and has the given initial capacity.
	 * @throws IllegalArgumentException If initialCapacity is negative.
	 */
	public ArrayDoubleSequence(int initialCapacity) {
		if (initialCapacity >= 0) {
			data = new double[initialCapacity];
			itemCount = 0;
			currentIndex = 0;
		} else {
			throw new IllegalArgumentException("The initial capacity cannot be negative.");
		}
	}

	/**
	 * Increases the capacity of this sequence, if necessary.
	 * 
	 * @param minimumCapacity The minimum capacity for this sequence.
	 * @postcondition This sequence's capacity has been changed to at least
	 *                minimumCapacity. If the capacity was already at or greater
	 *                than minimumCapacity, then the capacity is left unchanged.
	 */
	public void ensureCapacity(int minimumCapacity) {
		if (data.length <= minimumCapacity) {
			data = Arrays.copyOf(data, minimumCapacity * 2);
		}
	}

	/**
	 * Gets the current capacity of this sequence.
	 * 
	 * @return The current capacity of this sequence.
	 */
	public int getCapacity() {
		return data.length;
	}

	/**
	 * Reduces the current capacity of this sequence to its actual size.
	 * 
	 * @postcondition This sequence's capacity has been changed to its current size.
	 *                Nothing else about the sequence changes.
	 */
	public void trimToSize() {
		data = Arrays.copyOf(data, itemCount);
	}

	/**
	 * Sets the current element to be the beginning of this sequence.
	 * 
	 * @postcondition The first element of this sequence is now the current element
	 *                (but if this sequence has no elements at all, then there is no
	 *                current element).
	 */
	@Override
	public void start() {
		if (itemCount > 0) {
			currentIndex = 0;
		}
	}

	/**
	 * Gets the current element, if one exists.
	 * 
	 * @precondition This has a current element.
	 * @return The current element.
	 * @throws IllegalStateException If there is no current element.
	 */
	@Override
	public double getCurrent() {
		if (this.isCurrent()) {
			return data[currentIndex];
		} else {
			throw new IllegalStateException("There is no current element.");
		}
	}

	/**
	 * Changes the current element to the next one in the sequence.
	 * 
	 * @precondition This has a current element.
	 * @postcondition If the current element was already the end element of this
	 *                sequence (with nothing after it), then there is no longer any
	 *                current element. Otherwise, the new current element is the
	 *                element immediately after the original current element.
	 * @throws IllegalStateException If there is no current element.
	 */
	@Override
	public void advance() {
		if (this.isCurrent()) {
			if (currentIndex == itemCount - 1) {
				currentIndex = itemCount;
			} else {
				currentIndex++;
			}

		} else {
			throw new IllegalStateException("There is no current element.");
		}
	}

	/**
	 * Checks whether or not this has a current element.
	 * 
	 * @return True if this has a current element, or false otherwise.
	 */
	@Override
	public boolean isCurrent() {
		// if it is in range
		if (currentIndex != itemCount) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the number of elements in this sequence.
	 * 
	 * @return The number of elements in this sequence.
	 */
	@Override
	public int size() {
		return itemCount;
	}

	/**
	 * Adds a new element to this sequence, before the current element.
	 * 
	 * @param element The new element that should be added.
	 * @postcondition A new copy of the element has been added to this sequence. If
	 *                there was a current element, then the new element is placed
	 *                before the current element. If there was no current element,
	 *                then the new element is placed at the start of the sequence.
	 *                In all cases, the new element becomes the new current element
	 *                of this sequence.
	 */
	@Override
	public void addBefore(double element) {
		// ensure capacity
		if (itemCount == data.length) {
			this.ensureCapacity(itemCount + 1);
		}

		itemCount++;
		if (this.isCurrent()) {
			// add before current
			// move over elements
			for (int i = itemCount - 2; i >= currentIndex; i--) {
				data[i + 1] = data[i];
			}
			// add it in
			data[currentIndex] = element;
		} else {
			// move over elements
			for (int i = itemCount - 2; i > 0; i--) {
				data[i + 1] = data[i];
			}
			// add it in
			data[0] = element;
			currentIndex = 0;
		}

	}

	/**
	 * Adds a new element to this sequence, after the current element.
	 * 
	 * @param element The new element that should be added.
	 * @postcondition A new copy of the element has been added to this sequence. If
	 *                there was a current element, then the new element is placed
	 *                after the current element. If there was no current element,
	 *                then the new element is placed at the end of the sequence. In
	 *                all cases, the new element becomes the new current element of
	 *                this sequence.
	 */
	@Override
	public void addAfter(double element) {
		// ensure capacity
		if (itemCount == data.length) {
			this.ensureCapacity(itemCount);
		}
		// add
		if (this.isCurrent()) {
			data[currentIndex + 1] = element;
			currentIndex++;
		} else {
			data[itemCount] = element;
			currentIndex = itemCount;
		}
		itemCount++;

	}

	/**
	 * Removes the current element from this sequence.
	 * 
	 * @precondition This has a current element.
	 * @postcondition The current element has been removed from this sequence, and
	 *                the following element (if there is one) is now the new current
	 *                element. If there was no following element, then there is now
	 *                no current element.
	 * @throws IllegalStateException If there was no current element.
	 */
	@Override
	public void removeCurrent() {
		if (this.isCurrent()) {
			for (int i = currentIndex; i <= itemCount; i++) {
				data[i] = data[i + 1];
			}
			itemCount--;
			if (currentIndex == itemCount) {
				currentIndex = itemCount;
			}
		} else {
			throw new IllegalStateException("There is no current element");
		}
	}

	/**
	 * Gets a string representation of the sequence with current item in
	 * parentheses.
	 * 
	 * @return A String displaying this sequence.
	 */
	public String toString() {
		String answer = "";

		for (int index = 0; index < itemCount; index++) {
			if (index == currentIndex) {
				answer += "(" + data[index] + ") ";
			} else {
				answer += data[index] + " ";
			}
		}
		if (answer.length() == 0) {
			answer = "empty sequence";
		}
		return answer;
	}

}
