package net.tecgurus.gamestore.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.dao.IAuthorityDao;
import net.tecgurus.gamestore.model.Authority;

@Service
public class AuthorityService {
	
	@Autowired
	private IAuthorityDao authorityDao;
	
	public void create(Authority authority) {
		authority.setCreatedAt(LocalDateTime.now());
		this.authorityDao.create(authority);
	}
	
	public List<Authority> listByUserId(Long userId){
		return this.authorityDao.listByUserId(userId);
	}
}
