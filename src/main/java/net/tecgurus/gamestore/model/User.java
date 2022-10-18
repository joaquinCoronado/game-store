package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Boolean enabled;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
}
