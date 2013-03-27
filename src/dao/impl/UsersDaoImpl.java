package dao.impl;

import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import pojo.UsersProfile;
import dao.UsersDao;

public class UsersDaoImpl extends BaseDao implements UsersDao {

	@Override
	public void checkUserId(String userId , byte band) {
//		UsersProfile profile = this.getHibernateTemplate().get(UsersProfile.class , userId);
//		if(profile == null){
//			this.getHibernateTemplate().save(new UsersProfile(userId,band,new Date()));
//		}
	}

}
