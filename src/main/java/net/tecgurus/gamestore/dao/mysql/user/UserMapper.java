package net.tecgurus.gamestore.dao.mysql.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import net.tecgurus.gamestore.dao.mysql.game.GameMapper;
import net.tecgurus.gamestore.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setEnabled(rs.getBoolean("enabled"));
		
		Timestamp tsUpdatedAt = rs.getTimestamp("updated_at"); 
		if(tsUpdatedAt != null) {
			Date updatedAt = new Date(tsUpdatedAt.getTime());
			user.setUpdatedAt(GameMapper.toLocalDateTime(updatedAt));
		}
		
		Timestamp tsCreateAt = rs.getTimestamp("created_at"); 
		if(tsCreateAt != null) {
			Date createAt = new Date(tsCreateAt.getTime());
			user.setCreatedAt(GameMapper.toLocalDateTime(createAt));
		}
		
		return user;
	}

}
