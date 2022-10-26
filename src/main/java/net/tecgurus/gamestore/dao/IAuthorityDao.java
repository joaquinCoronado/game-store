package net.tecgurus.gamestore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.tecgurus.gamestore.model.Authority;

@Repository
public interface IAuthorityDao {
	void create(Authority authority);
	List<Authority> listByUserId(Long userId);
}
