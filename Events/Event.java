
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * An object for a general event.
 * 
 * @author Chad Hogg, Sam Noggle
 */
public abstract class Event {

	// Class invariants:
	// 1: startTime is before endTime

	/** The name of the event. */
	private String eventName;
	/** The time when the event starts. */
	private LocalTime startTime;
	/** The time when the event ends. */
	private LocalTime endTime;

	/**
	 * Constructs a new event.
	 * 
	 * @param eName The name of the new event.
	 * @param sTime The time when the new event starts.
	 * @param eTime The time when the new event ends. This must be after startTime.
	 */
	public Event(String eName, LocalTime sTime, LocalTime eTime) {
		if (eTime.isBefore(sTime)) {
			throw new IllegalArgumentException("The start and end times are not valid.");
		} else {
			eventName = eName;
			startTime = sTime;
			endTime = eTime;
		}
	}

	/**
	 * Gets the name of the event.
	 * 
	 * @return The name of the event.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * Gets the time when the event starts.
	 * 
	 * @return The time when the event starts.
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * Gets the time when the event ends.
	 * 
	 * @return The time when the event ends.
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * Determines whether or not the event occurs on a given day.
	 * 
	 * @param when The day it might occur on.
	 * @return True if the event occurs on that day, or false otherwise.
	 */
	public abstract boolean isOnDay(LocalDate when);

	/**
	 * Creates a string with all the information of an event.
	 * 
	 * @return a string containing the events name, start time and end time.
	 */
	@Override
	public String toString() {
		return (eventName + " (" + startTime + "-" + endTime + ")");
	}

	/**
	 * Generates a hashcode for each event.
	 * 
	 * @return a unique hashcode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

}
