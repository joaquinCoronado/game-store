package net.tecgurus.gamestore.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.dao.IAuthorityDao;
import net.tecgurus.gamestore.dao.IUserDao;
import net.tecgurus.gamestore.model.Authority;
import net.tecgurus.gamestore.model.User;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAuthorityDao authorityDao;
	
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}
	
	public void create(User user) {
		user.setCreatedAt(LocalDateTime.now());
		userDao.create(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.getByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid user name or password");
		}
		
		Collection<SimpleGrantedAuthority> authotities = this.getAuthoritiesFromUser(user.getId());
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authotities);
	}
	 
	public Collection<SimpleGrantedAuthority> getAuthoritiesFromUser(Long userId){
		List<Authority> authorities = this.authorityDao.listByUserId(userId);
		return authorities
			.stream()
			.map( authority -> new SimpleGrantedAuthority(authority.getAuthority().toString()))
			.collect(Collectors.toList());
	}

}
