package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Authority {
	private Long id;
	private Long userId;
	private Role authority;
	private LocalDateTime createdAt;
	
	public enum Role {
		ROLE_USER,
		ROLE_ADMIN
	}
}
