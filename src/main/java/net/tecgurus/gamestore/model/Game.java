package net.tecgurus.gamestore.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class Game {
	private Long id;
	
	@NotEmpty(message = "The Title field can't be empty")
	private String title;
	
	@NotEmpty(message = "The Type field can't be empty")
	private String type;
	
	@Pattern(regexp = "^.*(http:|https:)/.*$", message = "Put a valid URL for image pleas")
	private String image;
	
	@NotNull( message = "The Price field can't be empty")
	@Positive(message = "Set a positive number for price")
	private Double price;
	
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
}
