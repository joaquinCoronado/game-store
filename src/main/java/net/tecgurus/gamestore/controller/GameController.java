package net.tecgurus.gamestore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/game/update/{id}")
	public String updateGameView(@PathVariable("id") Long id, Model model) {
		Game game = gameService.getById(id);
		model.addAttribute("game", game);
		return "updateGame";
	}
	
	//ACTIONS
	
	@PostMapping("/game/create")
	public String createGame(@Valid Game game, BindingResult result) {
		
		if(result.hasErrors()) {
			return "createGame";
		}
		
		this.gameService.create(game);
		return "redirect:/game/list";
	}
	
	@PostMapping("/game/update")
	public String updateGame(@Valid Game game, BindingResult result) {
		
		if(result.hasErrors()) {
			return "updateGame";
		}
		
		this.gameService.update(game);
		return "redirect:/game/list";
	}
	
	@PostMapping("/game/delete/{id}")
	public String deleteGame(@PathVariable("id") Long id, Model model) {
		this.gameService.delete(id);
		return "redirect:/game/list";
	}
	
	
	
	
	
	
	
	

	
}
