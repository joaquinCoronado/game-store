package net.tecgurus.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.tecgurus.gamestore.model.Game;
import net.tecgurus.gamestore.service.GameService;

@Controller
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping({"/", "/game/list"})
	public String list(Model model) {
		
		List<Game> gameList = gameService.listAll();
		model.addAttribute("gameList",gameList);
		
		return "gameList";
	}
	
	@GetMapping("/game/create")
	public String createGameView(Model model){
		model.addAttribute("game", new Game());
		return "createGame";
	}
	
	@GetMapping("/game/update")
	public String updateGameView(Model model) {
		return "updateGame";
	}
	
	//ACTIONS
	
	@PostMapping("/game/create")
	public String createGame(Game game) {
		this.gameService.create(game);
		return "redirect:/game/list";
	}
	
	
	
	
	
	
	

	
}
