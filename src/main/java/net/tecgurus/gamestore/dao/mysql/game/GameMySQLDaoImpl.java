package net.tecgurus.gamestore.dao.mysql.game;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.dao.IGameDao;
import net.tecgurus.gamestore.model.Game;

@Repository("gameMySQLDao")
public class GameMySQLDaoImpl implements IGameDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Game> listAll() {
		String query = "SELECT * FROM games";
		return this.jdbcTemplate.query(query, new GameMapper() );
	}

	@Override
	public Game getById(Long id) throws EmptyResultDataAccessException{
		String query = "SELECT * FROM games WHERE id = ?";
		return jdbcTemplate.queryForObject(query, new GameMapper(), id);
	}

	@Override
	public void create(Game game) {
		String query = "INSERT INTO games(title, type, price, image, created_at) VALUES(?,?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update( 
			connection ->{
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, game.getTitle());
				ps.setString(2, game.getType());
				ps.setDouble(3, game.getPrice() != null ? game.getPrice() : 0L);
				ps.setString(4, game.getImage());
				ps.setTimestamp(5, Timestamp.valueOf(game.getCreatedAt()));
				return ps;
			}, 
			keyHolder
		);
		
		Long id = keyHolder.getKey().longValue();
		game.setId(id);
	}

	@Override
	public void update(Game game) {
		String query = "UPDATE games SET title = ?, type = ?, price = ?, image = ?, updated_at = ? WHERE id = ?";
		jdbcTemplate.update(
			query, 
			game.getTitle(),
			game.getType(),
			game.getPrice(),
			game.getImage(),
			game.getUpdatedAt(),
			game.getId()
		);
	}

	@Override
	public void delete(Long id) {
		String query = "DELETE FROM games WHERE id = ?";
		jdbcTemplate.update(query, id);
	}

}
