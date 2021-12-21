
public class PublicAccessRule implements AccessRule {
	/**
	 * constructs a new PublicAccessRule.
	 * 
	 */
	public PublicAccessRule() {
	}

	/**
	 * Allows everyone to be able to read the resource.
	 * 
	 * @param u the user trying to access the resource
	 */
	@Override
	public boolean canRead(User u) {
		return true;
	}

	@Override
	public String toString() {
		return "anyone";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PublicAccessRule)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int hashCode() {
		return 4352;
	}
}
