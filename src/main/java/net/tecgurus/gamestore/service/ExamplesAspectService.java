package net.tecgurus.gamestore.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.annotations.CustomAnnotation;
import net.tecgurus.gamestore.model.User;

@Service
@CustomAnnotation
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
	
	public double addition(double number1, double number2) {
		double result = number1 + number2;
		System.out.println("Addition: " + result);
		return result;
	}
	
	public List<User> listUser(){
		System.out.println("List User");
		
		User user = new User();
		user.setEmail("joaquin@hotmail.com");
		user.setName("Joaquin Coronado");
		
		User userTwo = new User();
		userTwo.setEmail("jesica@gmail.com");
		userTwo.setName("Jesica Cervantes");
		
		List<User> users = Arrays.asList(user, userTwo);
		return users;
	}
	
	public String getUserEmail(User user) {
		System.out.println("getUserEmail");
		return user.getEmail();
	}
	
	
	
}
