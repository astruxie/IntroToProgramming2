
public class GroupAccessRule implements AccessRule {

	/** the name of the group allowed. */
	private String groupName;

	/**
	 * creates a GroupAccessRule.
	 * 
	 * @param gName the game of the group allowed
	 * 
	 */
	public GroupAccessRule(String gName) {
		groupName = gName;
	}

	@Override
	public boolean canRead(User u) {
		if (u.isInGroup(groupName)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "members of group " + groupName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GroupAccessRule)) {
			return false;
		}
		GroupAccessRule other = (GroupAccessRule) obj;
		if (groupName == null) {
			if (other.groupName != null) {
				return false;
			}
		} else if (!groupName.equals(other.groupName)) {
			return false;
		}
		return true;
	}

}
