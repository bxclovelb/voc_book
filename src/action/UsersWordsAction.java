package action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import service.UsersWordsService;

import com.opensymphony.xwork2.ActionSupport;

public class UsersWordsAction extends ActionSupport {
	private String userId = "";
	private int model = 0;
	private int theBand = 0;
	private int pageNo = 0;
	private String alphabet = "";
	private String word = "";
	private byte score = 0;
	
	private UsersWordsService usersWordsService;
	
	private Map<String,Object> data;
	
	

	public UsersWordsAction() {
		data = new HashMap<String,Object>();
	}

	public String getUserBand(){
		theBand = usersWordsService.getUserBand(userId);
		System.out.println("theBand:"+theBand);
		return SUCCESS;
	}
	
	public String getCountDiffDegree(){
		data.clear();
		data = usersWordsService.getCountDiffDegree(userId,model);
		return SUCCESS;
	}
	
	public String getWords(){
		data = usersWordsService.getWords(userId,theBand,alphabet,pageNo);
		return SUCCESS;
	}
	
	public String getWordsCount(){
		data = usersWordsService.getWordsCount(theBand,alphabet);
		return SUCCESS;
	}
	
	public String updateDegree(){
		data = usersWordsService.updateDegree(userId,word);
		return SUCCESS;
	}
	
	public String getFirstDetail(){
		data = usersWordsService.getFirstDetail(word);
		return SUCCESS;
	}
	
	public String getAssociation(){
		data = usersWordsService.getAssociation(word);
		return SUCCESS;
	}
	
	public String getRelatedExercise(){
		data = usersWordsService.getRelatedExercise(word);
		return SUCCESS;
	}
	
	public String updateDegreeByExer(){
		usersWordsService.updateDegreeByExer(userId,word,score);
		return SUCCESS;
	}
	
	public String getDetails(){
		data = usersWordsService.getDetails(word);
		return SUCCESS;
	}
	
	public String getNotes(){
		data = usersWordsService.getNotes(userId,word);
		return SUCCESS;
	}
	
	
	public String showInverse(){
		return SUCCESS;
	}
	
	public String getWordsCountInverse(){
		data = usersWordsService.getWordsCountInverse(alphabet,theBand);
		return SUCCESS;
	}
	
	public String getWordsInverse(){
		data = usersWordsService.getWordsInverse(userId,theBand,alphabet,pageNo);
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
	public UsersWordsService getUsersWordsService() {
		return usersWordsService;
	}
	public void setUsersWordsService(UsersWordsService usersWordsService) {
		this.usersWordsService = usersWordsService;
	}

	public int getTheBand() {
		return theBand;
	}

	public void setTheBand(int theBand) {
		this.theBand = theBand;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public byte getScore() {
		return score;
	}

	public void setScore(byte score) {
		this.score = score;
	}

	//@JSON(serialize=true)
	public Map<String,Object> getData() {
		return data;
	}
	public void setData(Map<String,Object> data) {
		this.data = data;
	}
}
