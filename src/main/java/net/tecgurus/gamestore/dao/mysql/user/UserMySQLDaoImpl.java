package net.tecgurus.gamestore.dao.mysql.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.dao.IUserDao;
import net.tecgurus.gamestore.model.User;

@Repository
public class UserMySQLDaoImpl implements IUserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User getByEmail(String email) {
		User user = null;
		
		
		String query = "SELECT * FROM users WHERE email = ?";
		
		try {
			user = jdbcTemplate.queryForObject(query, new UserMapper(), email); 
		} catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		
	}

}
