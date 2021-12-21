import java.time.LocalDate;
import java.time.LocalTime;

public class OneTimeEvent extends Event {

	/** The day the event occurs. */
	private LocalDate date;

	/**
	 * Constructs a new one-time event.
	 * 
	 * @param eName The name of the new event.
	 * @param sTime The time when the new event starts.
	 * @param eTime The time when the new event ends. This must be after startTime.
	 * @param d     The day when the new event occurs.
	 */
	public OneTimeEvent(String eName, LocalDate d, LocalTime sTime, LocalTime eTime) {
		super(eName, sTime, eTime);
		date = d;

	}

	/**
	 * Determines whether or not the event occurs on a given day.
	 * 
	 * @param when The day it might occur on.
	 * @return True if the event occurs on that day, or false otherwise.
	 */
	@Override
	public boolean isOnDay(LocalDate when) {
		if (when.equals(date)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a string with all the information of an event.
	 * 
	 * @return a string containing the events name, start time, end time and the
	 *         date it occurs on.
	 */
	@Override
	public String toString() {
		return (super.toString() + " on " + date);
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (!(obj instanceof OneTimeEvent))
			return false;
		OneTimeEvent other = (OneTimeEvent) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

}
