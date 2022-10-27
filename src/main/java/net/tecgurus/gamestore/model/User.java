package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class User {
	
	private Long id;
	
	@NotEmpty(message = "The Name field can't be empty")
	private String name;
	
	@Email(message = "Invalid format for email")
	@NotEmpty(message = "The Email field can't be empty")
	private String email;
	
	@NotEmpty(message = "The Password field can't be empty")
	private String password;
	
	private Boolean enabled;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
}
