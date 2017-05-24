package logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;

@Service
public class UserCatalogImpl implements UserCatalog {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void entryUser(User user) {
		// TODO Auto-generated method stub
		this.userDao.create(user);
	}

	@Override
	public User getUserByUserIdAndPassword(String userId, String password) {
		// TODO Auto-generated method stub
		return this.userDao.findByUserIdAndPassword(userId, password);
	}

}
