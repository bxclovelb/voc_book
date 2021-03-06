package service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UsersWordsDao;

public class UsersWordsService {
	private UsersWordsDao usersWordsDao;
	
	public int getUserBand(String userId){
		return usersWordsDao.getUserBand(userId);
	}
	
	public Map getCountDiffDegree(String userId, int model){
		Map data = usersWordsDao.getCountDiffDegree(userId,model);
		return data;
	}

	public Map getWords(String userId,int band, String alphabet, int pageNo) {
		Map data = new HashMap();
		
		List<String> words = usersWordsDao.getWords(band,alphabet,(pageNo-1)*30,30);
		data.put("words",words);
		
		List<Byte> degrees = usersWordsDao.getDegrees(userId,words);
		data.put("degrees",degrees);
		
		List<Boolean> importants = usersWordsDao.getImportants(band,words);
		data.put("importants",importants);
		
		return data;
	}

	public Map getWordsCount(int band, String alphabet) {
		Map data = new HashMap();
		
		BigInteger count = usersWordsDao.getWordsCount(band,alphabet);
		data.put("count", count);
		
		return data;
	}
	
	public Map<String, Object> updateDegree(String userId, String word) {
		Map data = new HashMap();
		
		int degree = usersWordsDao.updateDegree(userId, word);
		data.put("degree", degree);
		
		return data;
	}
	
	public Map<String, Object> getFirstDetail(String word) {
		Map data = new HashMap();
		
		List firstDetail = usersWordsDao.getFirstDetail(word);
		data.put("firstDetail", firstDetail);
		
		return data;
	}

	public Map<String, Object> getAssociation(String word) {
		Map data = new HashMap();
		
		List association = usersWordsDao.getAssociation(word);
		data.put("association", association);
		
		return data;
	}
	

	public Map<String, Object> getRelatedExercise(String word) {
		Map data = new HashMap();
		
		List relatedExercise = usersWordsDao.getRelatedExercise(word);
		data.put("relatedExercise", relatedExercise);
		
		return data;
	}
	
	public void updateDegreeByExer(String userId, String word,
			byte score) {
		usersWordsDao.updateDegreeByExer(userId,word,score);
	}

	public Map<String, Object> getDetails(String word) {
		Map data = new HashMap();
		
		List details = usersWordsDao.getDetails(word);
		data.put("details", details);
		
		return data;
	}

	public Map<String, Object> getNotes(String userId, String word) {
		Map data = new HashMap();
		
		List notes = usersWordsDao.getNotes(userId,word);
		data.put("notes", notes);
		
		return data;
	}

	public Map getWordsCountInverse(String alphabet, int theBand) {
		Map data = new HashMap();
		
		BigInteger count = usersWordsDao.getWordsCountInverse(theBand,alphabet);
		data.put("count", count);
		
		return data;
	}
	
	public Map<String, Object> getWordsInverse(String userId, int theBand,
			String alphabet, int pageNo) {
		Map data = new HashMap();
		
		List<String> words = usersWordsDao.getWordsInverse(theBand,alphabet,(pageNo-1)*30,30);
		data.put("words",words);
		
		if(words.size() != 0){
			List<Byte> degrees = usersWordsDao.getDegrees(userId,words);
			data.put("degrees",degrees);
			
			List<Boolean> importants = usersWordsDao.getImportants(theBand,words);
			data.put("importants",importants);
		}
		
		
		return data;
	}
	
	public Map<String, Object> getWordsCountCategory(int catId, int theBand) {
		Map data = new HashMap();
		
		BigInteger count = usersWordsDao.getWordsCountCategory(theBand,catId);
		data.put("count", count);
		
		return data;
	}

	public Map<String, Object> getWordsCategory(int catId, int pageNo,
			String userId, int theBand) {
		Map data = new HashMap();
		
		List<String> words = usersWordsDao.getWordsCategory(theBand,catId,(pageNo-1)*30,30);
		data.put("words",words);
		
		if(words.size() != 0){
			List<Byte> degrees = usersWordsDao.getDegrees(userId,words);
			data.put("degrees",degrees);
			
			List<Boolean> importants = usersWordsDao.getImportants(theBand,words);
			data.put("importants",importants);
		}
		
		return data;
	}
	public Map<String, Object> saveNotes(String userId, String word,
			String notes) {
		Map data = new HashMap();
		
		boolean success = usersWordsDao.saveNotes(userId, word, notes);
		data.put("success", success);
		
		return data;
	}
	public Map<String, Object> getUserNotesCount(String userId) {
		Map data = new HashMap();
		
		int count = usersWordsDao.getUserNotesCount(userId);
		data.put("count", count);
		
		return data;
	}
	public Map<String, Object> getUserNotes(String userId, int pageNo) {
		Map data = new HashMap();
		
		List<Object[]> notes = usersWordsDao.getUserNotes(userId,(pageNo-1)*10,10);
		data.put("result", notes);
		List<String> words = new ArrayList<String>();
		for(Object[] objs : notes){
			String word = (String)objs[0];
			words.add(word);
		}
		List<Byte> degrees = usersWordsDao.getDegrees(userId,words);
		data.put("degree",degrees);
		
		return data;
	}
	
	

	public UsersWordsDao getUsersWordsDao() {
		return usersWordsDao;
	}
	public void setUsersWordsDao(UsersWordsDao usersWordsDao) {
		this.usersWordsDao = usersWordsDao;
	}
}
