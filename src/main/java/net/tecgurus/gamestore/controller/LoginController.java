package net.tecgurus.gamestore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.tecgurus.gamestore.model.User;
import net.tecgurus.gamestore.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String loginView(Model model) {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String createUser(@Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		this.userService.signup(user);
		return "redirect:/login";
	}
	
	
	
}
