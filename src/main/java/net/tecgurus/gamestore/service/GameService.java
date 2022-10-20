package net.tecgurus.gamestore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.dao.IGameDao;
import net.tecgurus.gamestore.model.Game;

@Service
public class GameService {
	
	@Autowired
	@Qualifier("gameMySQLDao")
	private IGameDao gameDao;
	
	public List<Game> listAll() {
		return gameDao.listAll();
	}
	
	public Game getById(Long id) {
		return gameDao.getById(id);
	}
	
	public void create(Game game) {
		game.setCreatedAt(LocalDateTime.now());
		gameDao.create(game);
	}
	
	public void update(Game game) {
		game.setUpdatedAt(LocalDateTime.now());
		gameDao.update(game);
	}
	
	public void delete(Long id) {
		gameDao.delete(id);
	}
}
