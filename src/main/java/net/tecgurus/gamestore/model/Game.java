package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Game {
	private Long id;
	private String title;
	private String type;
	private String image;
	private Double price;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
}
