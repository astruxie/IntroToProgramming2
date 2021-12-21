import java.security.*;

public class Resource {
	/** the name of the resource. */
	private String resourceName;
	/** the contents of the resource. */
	private String contents;
	/** the rule about who is authorized to read the resource. */
	private AccessRule accessRule;

	/**
	 * Creates a resource.
	 * 
	 * @param rName the name of the resource
	 * @param c     the contents of the resource
	 * @param aRule the rule about who is authorized to use the resource
	 */
	public Resource(String rName, String c, AccessRule aRule) {
		resourceName = rName;
		contents = c;
		accessRule = aRule;
	}

	/**
	 * Gets the name of the resource.
	 * 
	 * @return the name of the resource
	 */

	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Gets the contents for the user if they are allowed to read it.
	 * 
	 * @param u the user who wants to access the contents.
	 * @return true if they are allowed, false if they are not allowed.
	 */
	public String getContents(User u) {
		if (accessRule.canRead(u)) {
			return contents;
		} else {
			throw new AccessControlException("User cannot access this resource ");
		}
	}

	@Override
	public String toString() {
		return (resourceName + " with hidden contents, accessible by " + accessRule.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessRule == null) ? 0 : accessRule.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((resourceName == null) ? 0 : resourceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Resource)) {
			return false;
		}
		Resource other = (Resource) obj;
		if (accessRule == null) {
			if (other.accessRule != null) {
				return false;
			}
		} else if (!accessRule.equals(other.accessRule)) {
			return false;
		}
		if (contents == null) {
			if (other.contents != null) {
				return false;
			}
		} else if (!contents.equals(other.contents)) {
			return false;
		}
		if (resourceName == null) {
			if (other.resourceName != null) {
				return false;
			}
		} else if (!resourceName.equals(other.resourceName)) {
			return false;
		}
		return true;
	}

}
