package pojo;

public class UsersWordsId implements java.io.Serializable {

	private String userId;
	private String word;

	public UsersWordsId() {
	}

	public UsersWordsId(String userId, String word) {
		this.userId = userId;
		this.word = word;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsersWordsId))
			return false;
		UsersWordsId castOther = (UsersWordsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getWord() == castOther.getWord()) || (this.getWord() != null
						&& castOther.getWord() != null && this.getWord()
						.equals(castOther.getWord())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getWord() == null ? 0 : this.getWord().hashCode());
		return result;
	}

}
