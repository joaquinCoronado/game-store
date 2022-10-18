package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Authority {
	private Long id;
	private Long userId;
	private String authority;
	private LocalDateTime createdAt;
}
