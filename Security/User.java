import java.util.ArrayList;

public class User {

	private String userName;

	/** A collection of the names of the groups the user belongs to. */
	private ArrayList<String> groups = new ArrayList<String>();

	/**
	 * Constructs a new user.
	 * 
	 * @param uName The username of the user.
	 */
	public User(String uName) {
		userName = uName;
	}

	/**
	 * Accessor for the username.
	 * 
	 * @return the username of the user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Checks if the user is in a group.
	 * 
	 * @param groupName the group to be checked.
	 * @return true if the user is part of that group.
	 */
	public boolean isInGroup(String groupName) {
		if (groups.contains(groupName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds the user to a new group as long as they are not already part of this
	 * group.
	 * 
	 * @param newGroup the new group to be added.
	 */
	public void addToGroup(String newGroup) {
		if (!(isInGroup(newGroup))) {
			groups.add(newGroup);
		}
	}

	/**
	 * removes the user from a group as long as they are in it.
	 * 
	 * @param groupName the group to be removed.
	 */
	public void removeFromGroup(String groupName) {
		if (isInGroup(groupName)) {
			int i = groups.indexOf(groupName);
			groups.remove(i);
		}
	}

	/**
	 * Creates a String of the information of a user.
	 * 
	 * @return a string of the user's username and list of groups he is in.
	 */
	@Override
	public String toString() {
		String s = (userName + " is a member of ");

		if (groups.size() == 0) {
			s = s + ("no groups.");
		} else {
			s += "[";
			for (int i = 0; i < groups.size(); i++) {
				s = (s + (groups.get(i)));
				if (!(i + 1 >= groups.size())) {
					s = s + (", ");
				}

			}
			s = s + ("].");
		}
		return s;
	}

	/**
	 * Generates a hashcode for each user.
	 * 
	 * @return a unique hashcode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/**
	 * Tests if two users are equal.
	 * 
	 * @return true if the objects are equal and false if they are not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (groups == null) {
			if (other.groups != null) {
				return false;
			}
		} else if (!groups.equals(other.groups)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

}
