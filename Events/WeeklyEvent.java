import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyEvent extends RepeatingEvent {

	/**
	 * Constructs a new repeating weekly event.
	 * 
	 * @param eName  The name of the new event.
	 * @param fOccur The first occurrence of the event.
	 * @param rep    The number of times the event repeats.
	 * @param sTime  The time when the new event starts.
	 * @param eTime  The time when the new event ends. This must be after startTime.
	 */
	public WeeklyEvent(String eName, LocalDate fOccur, int rep, LocalTime sTime, LocalTime eTime) {
		super(eName, fOccur, rep, sTime, eTime);
	}

	/**
	 * Determines whether or not the event occurs on a given day.
	 * 
	 * @param when The day it might occur on.
	 * @return True if the event occurs on that day, or false otherwise.
	 */
	@Override
	public boolean isOnDay(LocalDate when) {
		// variables
		LocalDate firstOccurrence = this.getFirstOccurrence();
		LocalDate lastOccurrence = firstOccurrence.plusWeeks(getRepetitions());

		// checks
		if (when.equals(firstOccurrence)) {
			return true;
		}
		if (when.getDayOfWeek() != firstOccurrence.getDayOfWeek()) {
			return false;
		} else if (when.isBefore(firstOccurrence)) {
			return false;
		} else if (this.getRepetitions() == 0) {
			return true;
		} else if (!(lastOccurrence.isBefore(when))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a string with all the information of an event.
	 * 
	 * @return a string containing the events name, start time, end time, how many
	 *         times the event repeats and it's first occurrence, weekly.
	 */
	@Override
	public String toString() {
		return (super.toString() + " weeks starting on " + super.getFirstOccurrence());

	}

}
