package net.tecgurus.gamestore.dao;

import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.model.User;

@Repository
public interface IUserDao {
	User getByEmail(String email);
	void create(User user);
}
