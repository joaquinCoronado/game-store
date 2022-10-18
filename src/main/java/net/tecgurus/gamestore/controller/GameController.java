package net.tecgurus.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
	
	@GetMapping({"/", "/game/list"})
	public String list(Model model) {
		return "gameList";
	}
	
	@GetMapping("/game/create")
	public String createGameView(Model model){
		return "createGame";
	}
	
	@GetMapping("game/update")
	public String updateGameView(Model model) {
		return "updateGame";
	}
	
	

	
}
