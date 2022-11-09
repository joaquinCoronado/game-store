package net.tecgurus.gamestore.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.tecgurus.gamestore.config.SpringDataJDBCConfig;
import net.tecgurus.gamestore.config.WebAppConfig;
import net.tecgurus.gamestore.model.Game;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {WebAppConfig.class, SpringDataJDBCConfig.class})
@Transactional
@Rollback
public class GameDaoTest {
	
	@Autowired
	@Qualifier("gameMySQLDao")
	private IGameDao gameDao;
	
	@Test
	public void listAll() {
		List<Game> games = gameDao.listAll();
		Assertions.assertTrue(games.size() > 0);
	}
	
	@Test
	public void getByIdWithNullGame() {
		Game game = gameDao.getById(1L);
		System.out.println(game);
	}
	
	@Test
	public void getById() {
		Game game = gameDao.getById(1L);
		Assertions.assertNotNull(game);
	}
	
	@Test
	public void createGame() {
		Game game = new Game();
		game.setTitle("title test");
		game.setType("type test");
		game.setPrice(1500.00);
		game.setImage("image test");
		game.setUpdatedAt(LocalDateTime.now());
		game.setCreatedAt(LocalDateTime.now());
		
		gameDao.create(game);
		
		Assertions.assertNotNull(game.getId());
	}
	
	
	@Test
	public void updateGame() {
		Game game = new Game();
		game.setTitle("game_1");
		game.setCreatedAt(LocalDateTime.now());
		gameDao.create(game);
		
		game.setTitle("game_1 - update");
		game.setUpdatedAt(LocalDateTime.now());
		gameDao.update(game);
		
		Game updatedGame = gameDao.getById(game.getId());
		
		Assertions.assertEquals(game.getId(), updatedGame.getId());
		Assertions.assertEquals(game.getTitle(), updatedGame.getTitle());
		//Assertions.assertTrue(game.getCreatedAt().toEpochSecond(null) .equals(updatedGame.getCreatedAt()));
		//Assertions.assertEquals(game.getUpdatedAt(), updatedGame.getUpdatedAt());
	}
	
	
	@Test
	public void delete() {
		Game game = new Game();
		game.setTitle("game delete");
		game.setCreatedAt(LocalDateTime.now());
		gameDao.create(game);
		
		Assertions.assertNotNull(game.getId());
		
		gameDao.delete(game.getId());
		
		Game deletedGame = gameDao.getById(game.getId());
		Assertions.assertNull(deletedGame);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
