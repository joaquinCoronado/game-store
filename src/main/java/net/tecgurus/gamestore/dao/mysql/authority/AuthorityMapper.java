package net.tecgurus.gamestore.dao.mysql.authority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import net.tecgurus.gamestore.dao.mysql.game.GameMapper;
import net.tecgurus.gamestore.model.Authority;
import net.tecgurus.gamestore.model.Authority.Role;

public class AuthorityMapper implements RowMapper<Authority>{

	@Override
	public Authority mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Authority authority = new Authority();
		
		authority.setId(rs.getLong("id"));
		authority.setUserId(rs.getLong("user_id"));
		
		String authorityRs = rs.getString("authority");
		if(authorityRs != null) {
			Role role = Authority.Role.valueOf(authorityRs);
			authority.setAuthority(role);
		}
		
		Timestamp createdAtTs = rs.getTimestamp("created_at");
		if(createdAtTs != null) {
			Date createdAt = new Date(createdAtTs.getTime());
			authority.setCreatedAt(GameMapper.toLocalDateTime(createdAt));
		}
		
		return authority;
	}

}
