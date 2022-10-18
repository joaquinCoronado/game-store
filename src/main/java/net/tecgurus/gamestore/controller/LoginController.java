package net.tecgurus.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginView(Model model) {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}
}
