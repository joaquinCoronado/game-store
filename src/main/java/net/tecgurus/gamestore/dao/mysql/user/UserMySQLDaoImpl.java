package net.tecgurus.gamestore.dao.mysql.user;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		String query = "INSERT INTO users(name, password, enabled, created_at, email) VALUES(?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
			connection -> {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getPassword());
				ps.setBoolean(3, user.getEnabled());
				ps.setTimestamp(4, Timestamp.valueOf(user.getCreatedAt()));
				ps.setString(5, user.getEmail());
				return ps;
			},
			keyHolder
		);
		
		Long id = keyHolder.getKey().longValue();
		user.setId(id);
	}
	
	
	
	
	
	
	
	
	

}
