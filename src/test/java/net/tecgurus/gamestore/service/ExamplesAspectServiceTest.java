package net.tecgurus.gamestore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import net.tecgurus.gamestore.config.AOPConfig;
import net.tecgurus.gamestore.config.WebAppConfig;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {WebAppConfig.class, AOPConfig.class})
public class ExamplesAspectServiceTest {
	
	/*@Autowired
	private ExamplesAspectService service;
	
	@Test
	public void  exampleOne() {
		service.exampleOne();
	}
	
	@Test
	public void  division() {
		service.division(15, 3);
	}
	
	@Test
	public void divisionByZero() {
		service.division(15, 0);
	}
	
	@Test
	public void showUserName() {
		User user = new User();
		user.setName("Joaquin Coronado");
		service.showUserName(user);
	}
	
	@Test
	public void showUserNameWithNullUser() {
		service.showUserName(null);
	}
	
	@Test
	public void helloWorldUser() {
		service.helloWorld();
		service.greetingUser("Joaquin Coronado");
	}
	
	@Test
	public void addition() {
		service.addition(4, 6);
	}
	
	@Test
	public void listUser() {
		service.listUser().forEach(System.out::println);
		
		/*List<User> users = service.listUser();
		
		for(User user: users) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void getUserEmail() {
		User user = new User();
		user.setEmail("joaquin@hotmail.com");
		System.out.println(service.getUserEmail(user));
	}
	
	@Test
	public void getUserEmailWithNullUser() {
		System.out.println(service.getUserEmail(null));
	}*/
	
}
