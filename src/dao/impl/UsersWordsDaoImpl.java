package dao.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import dao.UsersWordsDao;

public class UsersWordsDaoImpl extends BaseDao implements UsersWordsDao {
	private String getBandStr(int band,boolean isImportant) {
		String bandStr = "";
		switch (band){
			case 1:bandStr = "band_3";break;
			case 2:bandStr = "band_6";break;
			case 3:bandStr = "pets_1";
				if(!isImportant){bandStr = "band_"+bandStr;}break;
			case 4:bandStr = "pets_2";
				if(!isImportant){bandStr = "band_"+bandStr;}break;
			case 5:bandStr = "pets_3";
				if(!isImportant){bandStr = "band_"+bandStr;}break;
			case 6:bandStr = "pets_4";
				if(!isImportant){bandStr = "band_"+bandStr;}break;
			case 7:bandStr = "pets_5";
				if(!isImportant){bandStr = "band_"+bandStr;}break;
			case 8:bandStr = "band_graduate";break;
			default:bandStr = "band_4";
		}
		if(isImportant){
			bandStr = "important_"+bandStr;
		}else{
			bandStr = bandStr+" = 1";
		}
		
		return bandStr;
	}
	
	//获得不同程度个数
	public Map<String,Integer> getCountDiffDegree(String userId,int model){
		int band = getUserBand(userId);
		String bandStr = getBandStr(band,false);
		
		Session session = getSessionFactory().openSession();
			
		String sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w "+
				"WHERE u.user_id = '"+userId+"' AND u.degree = 100 AND u.word = w.word "+
				"AND w."+bandStr+"=1";
		SQLQuery query = session.createSQLQuery(sql);
		List list = query.list();
		
		Map<String, Integer> data = new HashMap<String, Integer>();
		data.put("high", list.size());
		
		sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w "+
				"WHERE u.user_id = '"+userId+"' AND u.degree = 50 AND u.word = w.word "+
				"AND w."+bandStr+"=1";
		query = session.createSQLQuery(sql);
		list = query.list();
		data.put("middle", list.size());
		
		sql = "SELECT DISTINCT word FROM vocbook_words WHERE "+bandStr+"=1";
		query = session.createSQLQuery(sql);
		list = query.list();
		data.put("low", list.size() - data.get("high") - data.get("middle"));
		
		session.close();
		
		return data;
	}
	
	//获得用户上一次所选级别
	public int getUserBand(String userId){
		String hql = "SELECT userband.band FROM UsersBand userband "+
			"WHERE userband.userId = '"+userId+"'";
		Session session = getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		List list = query.list();
		
		int result = 0;
		if(list.size() == 0){
			result = -1;
		}else{
			result = (Integer)list.get(0);
		}
		
		session.close();

		return result;
	}

	@Override
	public List<String> getWords(int band, String alphabet, int from, int count) {
		String bandStr = getBandStr(band,false);
		String sql = "SELECT DISTINCT word FROM vocbook_words WHERE alphabet = '"+
				alphabet+"' AND order_id = 1 AND "+bandStr+" LIMIT "+from+","+count;
		Session session = getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(sql);
		List words = query.list();
		
		session.close();
		
		return words;
	}

	@Override
	public BigInteger getWordsCount(int band, String alphabet) {
		String bandStr = getBandStr(band,false);
		
		String sql = "SELECT COUNT(DISTINCT word) count FROM vocbook_words "+
				"WHERE alphabet = '"+alphabet+"' AND order_id = 1 AND "+bandStr;
		Session session = getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(sql);
		List list = query.list();
		
		session.close();
		
		return (BigInteger)list.get(0);
	}
	
	private String getFixedWord(String word){
		if(word.contains("'")){
			word = word.replace("'", "\'");
		}
		return word;
	}
	
	@Override
	public List<Byte> getDegrees(String userId, List<String> words) {
		Session session = getSessionFactory().openSession();
		
		List<Byte> degrees = new ArrayList<Byte>();
		
		for(String word : words){
			String wd = "";
			wd = getFixedWord(word);
			
			String sql = "SELECT degree FROM vocbook_users_words WHERE user_id = '"+
					userId+"' AND word ='"+wd+"'";
			SQLQuery query = session.createSQLQuery(sql);
			
			List<Byte> degree = query.list();
			
			if(degree.size() == 0 || degree.get(0) == 0){
				degrees.add(new Byte("-1"));
			}else if(degree.get(0) == 100){
				degrees.add(new Byte("1"));
			}else{
				degrees.add(new Byte("0"));
			}
		}
		
		session.close();
		
		return degrees;
	}

	@Override
	public List<Boolean> getImportants(int band, List<String> words) {
		String bandStr = getBandStr(band,true);
		
		List<Boolean> importants = new ArrayList<Boolean>();
		Session session = getSessionFactory().openSession();
		
		for(String word : words){
			String wd = "";
			wd = getFixedWord(word);
			
			String sql = "SELECT distinct "+bandStr+" band FROM vocbook_words "+
					"WHERE word ='"+wd+"'";
			SQLQuery query = session.createSQLQuery(sql);
			List list = query.list();
			
			importants.add((Boolean)list.get(0));
		}
		
		session.close();
		
		return importants;
	}
	
	private String getNowDateTime(){
		return SimpleDateFormat.getDateTimeInstance().format(new Date());
	}
	
	@Override
	public int updateDegree(String userId, String word) {
		String sql = "SELECT degree FROM vocbook_users_words"+
			" WHERE user_id='"+userId+"' AND word='"+word+"'";
		Session session = getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<Byte> degree = query.list();
		
		int dg = 0;
		if(degree.size() != 0){
			if(degree.get(0) == 0){
				dg = 50;
			}else if((int)degree.get(0) == 100){
				dg = 0;
			}else{
				dg = 100;
			}
			
			sql = "UPDATE vocbook_users_words SET degree = "+dg+
				",create_date = '"+getNowDateTime()+"'"+
				" WHERE user_id = '"+userId+"' AND word = '"+word+"'";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
		}else{
			dg = 50;
			
			sql = "SELECT voc_id,alphabet,anti_alphabet,cat_id FROM vocbook_words "+
				"WHERE voc_id = (SELECT MIN(voc_id) FROM vocbook_words WHERE word = '"+
				word+"' AND order_id = 1)";
			query = session.createSQLQuery(sql);
			List info = query.list();
			
			sql = "INSERT INTO vocbook_users_words(user_id,voc_id,degree,notes,"+
				"is_additional,create_date,alphabet,anti_alphabet,cat_id,word,"+
				"exercise_date) VALUES('"+userId+"',"+(BigInteger)(((Object[])info.get(0))[0])+","+dg+",null,"+
				"0,'"+getNowDateTime()+"','"+(Character)(((Object[])info.get(0))[1])+
				"','"+(Character)(((Object[])info.get(0))[2])+"',"+(Byte)(((Object[])info.get(0))[3])+",'"+word+"',null)";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
		}
		
		session.close();
		
		
		if (dg == 0){
			return -1;
		}else if(dg == 100){
			return 1;
		}else{
			return 0;
		}
	}
	
	@Override
	public List getFirstDetail(String word) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT word,meaning_ch,meaning_en,example "+
			"FROM vocbook_words WHERE word = '"+word+"' AND order_id = 1";
		SQLQuery query = session.createSQLQuery(sql);
		
		List firstDetail = query.list();
		
		session.close();
		
		return firstDetail;
	}
	
	@Override
	public List getAssociation(String word) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT relativeword FROM vocbook_word_association "+
				"WHERE coreword = '"+word+"'";
		SQLQuery query = session.createSQLQuery(sql);
		
		List association = query.list();
		
		session.close();
		
		return association;
	}
	
	@Override
	public List getRelatedExercise(String word) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT word,choice,choice_a,choice_b,choice_c,choice_d,content "+
				"FROM vocbook_word_related_exercise WHERE word = '"+word+"'";
		SQLQuery query = session.createSQLQuery(sql);
		
		List relatedExercise = query.list();
		
		session.close();
		
		return relatedExercise;
	}
	
	@Override
	public void updateDegreeByExer(String userId, String word, byte score) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT degree FROM vocbook_users_words"+
				" WHERE user_id='"+userId+"' AND word='"+word+"'";
		SQLQuery query = session.createSQLQuery(sql);
		
		List degree = query.list();
		
		if(degree.size() != 0){
			sql = "UPDATE vocbook_users_words SET degree = "+score+
					",exercise_date = '"+getNowDateTime()+"'"+
					" WHERE user_id = '"+userId+"' AND word = '"+word+"'";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
		}else{
			sql = "SELECT voc_id,alphabet,anti_alphabet,cat_id FROM vocbook_words "+
					"WHERE voc_id = (SELECT voc_id FROM vocbook_words WHERE word = '"+
					word+"' AND order_id = 1)";
			query = session.createSQLQuery(sql);
			List info = query.list();
			
			sql = "INSERT INTO vocbook_users_words(user_id,voc_id,degree,notes,"+
					"is_additional,create_date,alphabet,anti_alphabet,cat_id,word,"+
					"exercise_date) VALUES('"+userId+"',"+(BigInteger)((Object[])info.get(0))[0]+
					","+score+",null,0,'"+getNowDateTime()+"','"+
					(Character)((Object[])info.get(0))[1]+"','"+(Character)((Object[])info.get(0))[2]+
					"',"+(Byte)((Object[])info.get(0))[3]+",'"+word+"','"+getNowDateTime()+"')";
			query = session.createSQLQuery(sql);
			query.executeUpdate();
		}
		
		session.close();
	}
	
	@Override
	public List getDetails(String word) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT word,meaning_ch,meaning_en,example FROM vocbook_words "+
				"WHERE word = '"+word+"' ORDER BY order_id";
		SQLQuery query = session.createSQLQuery(sql);
		List details = query.list();
		
		session.close();
		
		return details;
	}
	
	@Override
	public List getNotes(String userId,String word) {
		Session session = getSessionFactory().openSession();
		String sql = "SELECT notes FROM vocbook_users_words WHERE word = '"
				+word+"' AND user_id='"+userId+"'";
		SQLQuery query = session.createSQLQuery(sql);
		List notes = query.list();
		
		session.close();
		
		return notes;
	}
}
