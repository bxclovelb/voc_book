package service;

import dao.UsersWordsDao;

public class UsersWordsService {
	private UsersWordsDao usersWordsDao;
	
	public int getUserBand(String userId){
		return usersWordsDao.getUserBand(userId);
	}
	

	public UsersWordsDao getUsersWordsDao() {
		return usersWordsDao;
	}
	public void setUsersWordsDao(UsersWordsDao usersWordsDao) {
		this.usersWordsDao = usersWordsDao;
	}
}
