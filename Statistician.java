/**
 * A class that keeps track of statistics about a sequence of real numbers. It
 * has the following class invariants: - sumOfValues contains the sum of all
 * values entered. If the sum ever becomes too high or low to represent, it will
 * be Double.POSITIVE_INFINITY or Double.NEGATIVE_INFINITY. (You do not need to
 * worry about this.) - count contains the number of values. If the number of
 * values entered is too high to count, it will be Integer.MAX_VALUE. (You do
 * not need to worry about this.) - smallestValue contains smallest value
 * entered, or 0.0 if no values were entered. - largestValue contains largest
 * value entered, or 0.0 if no values were entered.
 * 
 * @author Michael Main, Beth Katz, Chad Hogg, Samantha Noggle
 */
public class Statistician implements Cloneable {

	/** The sum of all the numbers inserted since the last reset. */
	private double sumOfValues;
	/** The amount of numbers inserted since the last reset. */
	private int count;
	/** The smallest value entered, or 0.0 if there were no numbers yet. */
	private double smallestValue;
	/** The largest value entered, or 0.0 if there were no numbers yet. */
	private double largestValue;

	/**
	 * Initializes a new Statistician.
	 * 
	 * @postcondition The new Statistician contains no numbers.
	 **/
	public Statistician() {
		count = 0;
		sumOfValues = 0;
		smallestValue = 0.0;
		largestValue = 0.0;
	}

	/**
	 * Gets the amount of numbers that have been given to this Statistician.
	 * 
	 * @return The count of how many numbers have been given to this Statistician
	 *         since it was initialized or reinitialized.
	 **/
	public int getLength() {
		return count;
	}

	/**
	 * Gets the sum of all the numbers that have been given to this Statistician.
	 * 
	 * @return The sum of all the numbers that have been given to this Statistician
	 *         since it was created or reset.
	 **/
	public double getSum() {
		return sumOfValues;
	}

	/**
	 * Gets the arithmetic average of all the numbers that have been given to this
	 * Statistician. Note: If getLength() is zero, then the answer will be
	 * Double.NaN.
	 * 
	 * @return The arithmetic mean of all the numbers that have been given to this
	 *         Statistician since it was created or reset.
	 **/
	public double getMean() {
		double num = sumOfValues / count;
		return num;
	}

	/**
	 * Gets the smallest number that has been given to this Statistician.
	 * 
	 * @return The smallest number that has been given to this Statistician since it
	 *         was created or reset, or Double.NAN if there have been no numbers
	 *         yet.
	 **/
	public double getSmallest() {
		if (count == 0) {
			return Double.NaN;
		} else {
			return smallestValue;
		}
	}

	/**
	 * Gets the largest number that has been given to this Statistician.
	 * 
	 * @return The largest number that has been given to this Statistician since it
	 *         was created or reset, or Double.NAN if there have been no numbers
	 *         yet.
	 **/
	public double getLargest() {
		if (count == 0) {
			return Double.NaN;
		} else {
			return largestValue;
		}
	}

	/**
	 * Gives a new number to this Statistician.
	 * 
	 * @param number The new number that is being given.
	 * @postcondition This Statistician has updated its statistics to include the
	 *                new number.
	 **/
	public void insert(double number) {
		sumOfValues += number;
		count++;
		if (count != 1) {
			if (number < smallestValue) {
				smallestValue = number;
			}

			if (number > largestValue) {
				largestValue = number;
			}
		} else {
			largestValue = number;
			smallestValue = number;
		}
	}

	/**
	 * Resets this Statistician.
	 * 
	 * @postcondition This Statistician is reinitialized as if it has never been
	 *                given any numbers.
	 **/
	public void reset() {
		smallestValue = 0;
		largestValue = 0;
		sumOfValues = 0;
		count = 0;
	}

	/**
	 * Add all the numbers of another Statistician to this Statistician.
	 * 
	 * @param addend A Statistician whose numbers will be added to this
	 *               Statistician, which must not be null.
	 * @postcondition The numbers from addend have been added to this Statistician.
	 *                After the operation, this Statistician acts as if it were
	 *                given all of its original numbers and also given all of the
	 *                numbers from the addend.
	 * @throws NullPointerException If addend is null.
	 **/
	public void add(Statistician addend) {
		sumOfValues += addend.getSum();
		count += addend.getLength();

		if (smallestValue > addend.getSmallest()) {
			smallestValue = addend.getSmallest();
		}
		if (largestValue < addend.getLargest()) {
			largestValue = addend.getLargest();
		}
	}

	/**
	 * Create a new Statistician that behaves as if it was given all the numbers
	 * from this and another Statistician.
	 * 
	 * @param other An existing Statistician, which may not be null.
	 * @return A new Statistician that acts as if it was given all the numbers from
	 *         this Statistician and the other Statistician.
	 * @postcondition Nothing about this Statistician has changed.
	 * @throws NullPointerException If other is null.
	 **/
	public Statistician union(Statistician other) {
		Statistician newstat = new Statistician();
		sumOfValues += other.getSum();
		count += other.getLength();

		if (smallestValue > other.getSmallest()) {
			smallestValue = other.getSmallest();
		}
		if (largestValue < other.getLargest()) {
			largestValue = other.getLargest();
		}

		if (other.getLength() == 0) {
			return null;
		} else {
			return newstat;
		}
	}

}
