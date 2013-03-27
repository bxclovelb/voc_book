package pojo;

public class UsersBand implements java.io.Serializable {

	private Long id;
	private String userId;
	private int band;

	public UsersBand() {
	}

	public UsersBand(String userId, int band) {
		this.userId = userId;
		this.band = band;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBand() {
		return this.band;
	}

	public void setBand(int band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "UsersBand [id=" + id + ", userId=" + userId + ", band=" + band
				+ "]";
	}
	
}
