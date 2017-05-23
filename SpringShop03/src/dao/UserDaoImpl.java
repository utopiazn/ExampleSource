package dao;

import javax.sql.DataSource;

import logic.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class UserDaoImpl implements UserDao{

	
	private static final String SELECT_BY_USERID_PASSWORD = "SELECT user_id, password, user_name, postcode,"
			+ " address, email, job, birthday FROM user_account WHERE user_id = ? AND password = ?";

	private SimpleJdbcTemplate template;
	
	
	public void setDataSource(DataSource dataSource){
		
		this.template = new SimpleJdbcTemplate(dataSource);
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
