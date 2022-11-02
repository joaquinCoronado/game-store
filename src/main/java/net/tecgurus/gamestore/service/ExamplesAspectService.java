package net.tecgurus.gamestore.service;

import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.model.User;

@Service
public class ExamplesAspectService {
	
	public void exampleOne() {
		System.out.println("exampleOne");
	}
	
	public double division(int number1, int numer2) {
		System.out.println("division");
		double result = number1/numer2;
		System.out.println(result);
		return result;
	}
	
	public void showUserName(User user) {
		System.out.println("Show user name");
		System.out.println(user.getName());
	}
	
	public void helloWorld() {
		System.out.println("Hello World");
	}
	
	public String greetingUser(String name) {
		String message = "Hello " + name;
		System.out.println(message);
		return message;
	}
	
	
	
}
