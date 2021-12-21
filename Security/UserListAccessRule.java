import java.util.ArrayList;

public class UserListAccessRule implements AccessRule {

	/** stores a collection of users that are granted access. */
	private ArrayList<String> userNames;

	/**
	 * Constructs a new default USerListAccessRule.
	 * 
	 */
	public UserListAccessRule() {
		userNames = new ArrayList<String>();
	}

	/**
	 * Adds a username to the list of allowed users if they are not on the list.
	 * 
	 * @param uName the user to be allowed.
	 */
	public void addUser(String uName) {
		if (!(userNames.contains(uName))) {
			userNames.add(uName);
		}
	}

	/**
	 * Removes a username fromt he list of allowed users if they are on the list.
	 * 
	 * @param uName the user to be removed.
	 */
	public void removeUser(String uName) {
		if (userNames.contains(uName)) {
			userNames.remove(uName);
		}
	}

	/**
	 * Creates a String of the users allowed or if no one is allowed.
	 * 
	 * @return a String of a list of users who are allowed.
	 */
	@Override
	public String toString() {
		String s = "users named [";

		if (userNames.size() == 0) {
			return "no users";
		} else {
			for (int i = 0; i < userNames.size(); i++) {
				s = (s + (userNames.get(i)));
				if (!(i + 1 >= userNames.size())) {
					s = s + (", ");
				}
			}
			s = s + ("]");
			return s;
		}
	}

	/**
	 * Checks if a user is allowed.
	 * 
	 * @param u the user in question.
	 * @return true if the user is allowed and false if he is not.
	 */
	@Override
	public boolean canRead(User u) {
		if (!(userNames.contains(u.getUserName()))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Generates a hashcode for each UserListAccessRule.
	 * 
	 * @return a unique hashcode.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userNames == null) ? 0 : userNames.hashCode());
		return result;
	}

	/**
	 * Tests if two USerListAccessRules are equal.
	 * 
	 * @return true if the objects are equal and false if they are not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserListAccessRule)) {
			return false;
		}
		UserListAccessRule other = (UserListAccessRule) obj;
		if (userNames == null) {
			if (other.userNames != null) {
				return false;
			}
		} else if (!userNames.equals(other.userNames)) {
			return false;
		}
		return true;
	}

}
