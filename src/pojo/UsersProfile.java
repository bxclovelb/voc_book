package pojo;

import java.util.Date;

public class UsersProfile implements java.io.Serializable {

	private String userId;
	private byte band;
	private Date dateTime;

	public UsersProfile() {
	}

	public UsersProfile(String userId, byte band) {
		this.userId = userId;
		this.band = band;
	}

	public UsersProfile(String userId, byte band, Date dateTime) {
		this.userId = userId;
		this.band = band;
		this.dateTime = dateTime;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte getBand() {
		return this.band;
	}

	public void setBand(byte band) {
		this.band = band;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "VocbookUsersProfile [userId=" + userId + ", band=" + band
				+ ", dateTime=" + dateTime + "]";
	}

}
