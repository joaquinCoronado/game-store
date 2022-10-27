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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.tecgurus.gamestore.dao.IUserDao;
import net.tecgurus.gamestore.model.Authority;
import net.tecgurus.gamestore.model.User;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}
	
	public void create(User user) {
		user.setCreatedAt(LocalDateTime.now());
		user.setPassword(encoder.encode(user.getPassword()));
		user.setName(user.getName().toLowerCase());
		user.setEmail(user.getEmail().toLowerCase());
		user.setEnabled(true);
		userDao.create(user);
	}
	
	public void signup(User user) {
		this.create(user);
		
		//Create the authorities
		Authority authority = new Authority();
		authority.setUserId(user.getId());
		authority.setAuthority(Authority.Role.ROLE_USER);
		this.authorityService.create(authority);
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
		List<Authority> authorities = this.authorityService.listByUserId(userId);
		return authorities
			.stream()
			.map( authority -> new SimpleGrantedAuthority(authority.getAuthority().toString()))
			.collect(Collectors.toList());
	}

}
