package action;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private String userId = "";
	private int model = 0;
	private int theband = 0;

	public String showNormal(){
		return SUCCESS;
	}
	
	public String checkUserId(){
		
		return SUCCESS;
	}
	

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}
	
	public int getTheband() {
		return theband;
	}
	
	public void setTheband(int band) {
		this.theband = band;
	}
	
}
