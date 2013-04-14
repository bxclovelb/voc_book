package dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface UsersWordsDao {
	public Map<String,Integer> getCountDiffDegree(String userId, int model);
	public int getUserBand(String userId);
	public List<String> getWords(int band, String alphabet, int from, int count);
	public BigInteger getWordsCount(int band, String alphabet);
	public List<Byte> getDegrees(String userId, List<String> words);
	public List<Boolean> getImportants(int band, List<String> words);
	public int updateDegree(String userId, String word);
	public List getFirstDetail(String word);
	public List getAssociation(String word);
	public List getRelatedExercise(String word);
	public void updateDegreeByExer(String userId, String word, byte score);
	public List getDetails(String word);
	public List getNotes(String userId, String word);
	public BigInteger getWordsCountInverse(int theBand, String alphabet);
	public List<String> getWordsInverse(int theBand, String alphabet, int from,
			int count);
	public BigInteger getWordsCountCategory(int theBand, int catId);
	public List<String> getWordsCategory(int theBand, int catId, int i, int j);
}
