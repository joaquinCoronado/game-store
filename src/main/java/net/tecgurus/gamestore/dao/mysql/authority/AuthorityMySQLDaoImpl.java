package net.tecgurus.gamestore.dao.mysql.authority;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.dao.IAuthorityDao;
import net.tecgurus.gamestore.model.Authority;

@Repository
public class AuthorityMySQLDaoImpl implements IAuthorityDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void create(Authority authority) {
		String query = "INSERT INTO authorities(user_id, authority, created_at) VALUE(?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
			connection -> {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, authority.getUserId());
				ps.setString(2, authority.getAuthority().toString());
				ps.setTimestamp(3, Timestamp.valueOf(authority.getCreatedAt()));
				return ps;
			},
			keyHolder
		);
		
		Long id = keyHolder.getKey().longValue();
		authority.setId(id);
	}

	@Override
	public List<Authority> listByUserId(Long userId) {
		String query = "SELECT * FROM authorities WHERE user_id = ?";
		return jdbcTemplate.query(query, new AuthorityMapper(), userId);
	}
}
