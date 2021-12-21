import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RepeatingEvent extends Event {

	/** The day the event occurs for the first time. */
	private LocalDate firstOccurrence;
	/** The number of times the event repeats. */
	private int repetitions;

	/**
	 * Constructs a new repeating event.
	 * 
	 * @param eName  The name of the new event.
	 * @param fOccur The first occurrence of the event.
	 * @param rep    The number of times the event repeats, cannot be negative.
	 * @param sTime  The time when the new event starts.
	 * @param eTime  The time when the new event ends. This must be after startTime.
	 */
	public RepeatingEvent(String eName, LocalDate fOccur, int rep, LocalTime sTime, LocalTime eTime) {
		super(eName, sTime, eTime);
		firstOccurrence = fOccur;
		if (rep >= 0) {
			repetitions = rep;
		} else {
			throw new IllegalArgumentException("Repetitions cannot be negative.");
		}
	}

	/**
	 * Gets the date of the event's first occurrence.
	 * 
	 * @return The day the event first occurs.
	 */
	public LocalDate getFirstOccurrence() {
		return firstOccurrence;
	}

	/**
	 * Gets the amount of times the event repeats.
	 * 
	 * @return The amount of times the event repeats.
	 */
	public int getRepetitions() {
		return repetitions;
	}

	/**
	 * Creates a string with all the information of an event.
	 * 
	 * @return a string containing the events name, start time, end time and how
	 *         many times the event repeats.
	 */
	@Override
	public String toString() {
		if (repetitions == 0) {
			return (super.toString() + " repeating for all");
		} else {
			return (super.toString() + " repeating for " + repetitions);
		}
	}

	/**
	 * Generates a hashcode for each event.
	 * 
	 * @return a unique hashcode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((firstOccurrence == null) ? 0 : firstOccurrence.hashCode());
		result = prime * result + repetitions;
		return result;
	}

	/**
	 * Tests if two events are equal.
	 * 
	 * @return true if the objects are equal and false if they are not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof RepeatingEvent))
			return false;
		RepeatingEvent other = (RepeatingEvent) obj;
		if (firstOccurrence == null) {
			if (other.firstOccurrence != null)
				return false;
		} else if (!firstOccurrence.equals(other.firstOccurrence))
			return false;
		if (repetitions != other.repetitions)
			return false;
		return true;
	}

}
