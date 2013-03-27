package dao.impl;

import java.util.List;

import org.hibernate.Query;
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
	function getCountDiffDegree(String userId,int model){
		int band = getUserBand(userId);
		String bandStr = $this->get_band_str(band);
			
		$sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w ".
				"WHERE u.user_id = '".$user_id."' AND u.degree = 100 AND u.word = w.word ".
				"AND w.".bandStr."=1";
		$query = $this->db->query($sql);
		$data["high"] = $query->num_rows();

		$sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w ".
				"WHERE u.user_id = '".$user_id."' AND u.degree = 50 AND u.word = w.word ".
				"AND w.".bandStr."=1";
		$query = $this->db->query($sql);
		$data["middle"] = $query->num_rows();
		
		$sql = "SELECT DISTINCT word FROM vocbook_words WHERE ".bandStr."=1";
		$query = $this->db->query($sql);
		$result = $query->num_rows();
		$data["low"] = $result - $data["high"] - $data["middle"];
		
		
		return $data;
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

		return result;
	}
	
}
