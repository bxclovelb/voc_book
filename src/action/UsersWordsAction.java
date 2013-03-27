package action;

import org.apache.struts2.json.annotations.JSON;

import service.UsersWordsService;

import com.opensymphony.xwork2.ActionSupport;

public class UsersWordsAction extends ActionSupport {
	private String userId;
	private int model;
	private int band;
	private UsersWordsService usersWordsService;

	public String getUserBand(){
		band = usersWordsService.getUserBand(userId);
		System.out.println(band);
		return SUCCESS;
	}
	
	
	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JSON(serialize=false)
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	@JSON(serialize=false)
	public UsersWordsService getUsersWordsService() {
		return usersWordsService;
	}
	public void setUsersWordsService(UsersWordsService usersWordsService) {
		this.usersWordsService = usersWordsService;
	}
	@JSON(serialize=true)
	public int getBand() {
		return band;
	}
	public void setBand(int band) {
		this.band = band;
	}
}
