package net.tecgurus.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DummyController {
	
	@GetMapping("/dummy")
	public String dummyView(Model model) {
		model.addAttribute("name","Joaquin Coronado");
		return "login";
	}
}
