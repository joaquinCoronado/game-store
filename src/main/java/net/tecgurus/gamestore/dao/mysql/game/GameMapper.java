package net.tecgurus.gamestore.dao.mysql.game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import net.tecgurus.gamestore.model.Game;

public class GameMapper implements RowMapper<Game>{
	
	public static LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	@Override
	public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
		Game game = new Game();
		game.setId(rs.getLong("id"));
		game.setTitle(rs.getString("title"));
		game.setType(rs.getString("type"));
		game.setImage(rs.getString("image"));
		game.setPrice(rs.getDouble("price"));
		
		if(rs.getTimestamp("updated_at") != null) {
			Date updatedAt = new Date(rs.getTimestamp("updated_at").getTime());
			game.setUpdatedAt(GameMapper.toLocalDateTime(updatedAt));
		}
		
		if(rs.getTimestamp("created_at") != null) {
			Date createdAt = new Date(rs.getTimestamp("created_at").getTime());
			game.setCreatedAt(GameMapper.toLocalDateTime(createdAt));
		}
		
		return game;
	}

}
