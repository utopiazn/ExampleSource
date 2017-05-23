package logic;

import dao.UserDao;

public class UserCatalogImpl implements UserCatalog {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	@Override
	public void entryUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.create(user);
	}

}
