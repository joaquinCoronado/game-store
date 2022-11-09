package net.tecgurus.gamestore.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.model.Game;

@Repository
public interface IGameDao {
	List<Game> listAll();
	Game getById(Long id) throws EmptyResultDataAccessException;
	void create(Game game);
	void update(Game game);
	void delete(Long id);
}
