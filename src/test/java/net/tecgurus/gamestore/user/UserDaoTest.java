package net.tecgurus.gamestore.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.tecgurus.gamestore.config.SpringDataJDBCConfig;
import net.tecgurus.gamestore.config.WebAppConfig;
import net.tecgurus.gamestore.dao.IUserDao;
import net.tecgurus.gamestore.model.User;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {WebAppConfig.class, SpringDataJDBCConfig.class})
@Transactional
@Rollback
public class UserDaoTest {
	
	@Autowired
	private IUserDao userDao;
	
	@Test
	public void getByEmal() {
		User user = userDao.getByEmail("joaquin@hotmail.com");
		System.out.println(user);
	}

}
