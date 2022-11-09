package net.tecgurus.gamestore.aspects;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import net.tecgurus.gamestore.model.User;

@Aspect
@Component
public class ExamplesAspect {
	
	//TODO: Porque un metodo y revisar si hay alternativas
	@Pointcut("execution(public void net.tecgurus.gamestore.service.ExamplesAspectService.*(net.tecgurus.gamestore.model.User))")
	private void pointCutExample() {}
	
	@Pointcut("execution(public * net.tecgurus.gamestore.service.ExamplesAspectService.greeting*(..))")
	private void startWithGreeting() {}
	
	@Pointcut("execution(public * net.tecgurus.gamestore.service.ExamplesAspectService.hello*(..))")
	private void startWithHello() {}
	
	@Pointcut("execution( * net.tecgurus.gamestore.service.ExamplesAspectService.*(..))")
	private void allMethodsOnExamplesAspects() {}
	
	@Pointcut("execution(public double net.tecgurus.gamestore.service.ExamplesAspectService.addition(double, double))")
	private void pointCutAddition() {}
	
	@Pointcut("execution(public java.util.List<net.tecgurus.gamestore.model.User> net.tecgurus.gamestore.service.ExamplesAspectService.*(..))")
	private void returnUserList() {}
	
	@Pointcut("execution(public String net.tecgurus.gamestore.service.ExamplesAspectService.*(..))")
	private void getUserEmail() {}
	
	
	@Pointcut("execution(public * net.tecgurus.gamestore.dao.mysql.*.*.getBy*(..))")
	private void getEntityBy() {}
	
	/*-----------------------*
	 * FILTER BY METHOD NAME *
	 *-----------------------*/
	@Before("execution(public void exampleOne())")
	public void before() {
		System.out.println("Advice @Before");
	}
	
	@After("execution(public void exampleOne())")
	public void after() {
		System.out.println("Advice @After");
	}
	
	/*-----------------*
	 * FILTER BY CLASS *
	 *-----------------*/
	
	@Before("execution(public double net.tecgurus.gamestore.service.ExamplesAspectService.*(int, int))")
	public void beforByClass() {
		System.out.println("Advice @Before - ByClassName");
	}
	
	@After("execution(public double net.tecgurus.gamestore.service.ExamplesAspectService.*(int, int))")
	public void afterByClass() {
		System.out.println("Advice @After - ByClassName");
	}
	
	@AfterReturning("execution(public double net.tecgurus.gamestore.service.ExamplesAspectService.*(int, int))")
	public void afterReturning() {
		System.out.println("Advice @AfterReturning - ByClassName");
	}
	
	@AfterThrowing("execution(public double net.tecgurus.gamestore.service.ExamplesAspectService.*(int, int))")
	public void afterThrowing() {
		System.out.println("Advice @AfterThrowing - ByClassName");
	}
	
	
	/*---------------------*
	 * FILTER BY POINT CUT *
	 *---------------------*/
	
	@Before("pointCutExample()")
	public void beforeByPointcut() {
		System.out.println("Advice @Before - ByPointCut");
	}
	
	@After("pointCutExample()")
	public void afterByPointcut() {
		System.out.println("Advice @After - ByPointCut");
	}
	
	@AfterReturning("pointCutExample()")
	public void afterReturningByPointcut() {
		System.out.println("Advice @AfterReturning - ByPointCut");
	}
	
	//TODO: Verificar uso con hilos, revisar la referencia al hilo ver si son las mismas
	@AfterThrowing(pointcut = "pointCutExample()", throwing = "exeption")
	public void afterThrowingByPointcut(Throwable exeption) {
		System.out.println("Advice @AfterThrowing - ByPointCut");
		
		System.out.println(exeption);
		if(exeption instanceof NullPointerException) {
			System.out.println("SEND EMAIL TO SUPPORT ");
		}
	}
	
	/*----------------------*
	 * COMBINING POINT CUTS *
	 *----------------------*/
	
	@Before("startWithHello() || startWithGreeting()")
	public void beforeCombiningPointCuts() {
		System.out.println("Advice @Before - CombiningPointCuts");
	}
	
	@Before("allMethodsOnExamplesAspects() && !(startWithHello() || startWithGreeting())")
	public void beforeAllExceptStartWithHello() {
		System.out.println("Advice @Before - All except start with hello");
	}
	
	/*-----------*
	 * JoinPoint *
	 *-----------*/
	
	@Before("pointCutAddition()")
	public void beforeJoinPoint(JoinPoint joinPoint) {
		System.out.println("Advice @Before - JoinPoint");
		Object[] args = joinPoint.getArgs();
		for(Object object : args) {
			System.out.println(object);
		}
	} 
	
	@AfterReturning(pointcut = "returnUserList()", returning = "userList")
	public void afterReturningList(List<User> userList) {
		System.out.println("Advice @AfterReturning - List");
		for(User user: userList) {
			user.setName(user.getName().toLowerCase());
			System.out.println(user);
		}
	}
	
	@Around("getUserEmail()")
	public Object aroundExample(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("@Before");
		
		Object response = null;
		
		try {
			response = joinPoint.proceed();
		} catch(NullPointerException ex) {
			response = "null object";
		} catch(Exception e) {
			//throw new RuntimeException();
			e.printStackTrace();
		}
		
		System.out.println("@After");
		return response;
	}
	
	
	/*----------------------*
	 * Pointcuts Designator *
	 *----------------------*/
	@Before("within(net.tecgurus.gamestore.service.ExamplesAspectService)")
	public void withinClass() {
		System.out.println("I'm in ExamplesAspectService class");
	}
	
	@Before("this(net.tecgurus.gamestore.dao.IGameDao)")
	public void withinInterface() {
		//System.out.println("I'm applying - IGameDao Interface");
	}
	
	@Before("@within(org.springframework.stereotype.Service)")
	public void annotationService() {
		//System.out.println("I have the annotation @Service");
	}
	
	@Before("@within(org.springframework.stereotype.Repository)")
	public void annotationRepository() {
		//System.out.println("I have the annotation @Repository");
	}
	
	@Before("@within(net.tecgurus.gamestore.annotations.CustomAnnotation)")
	public void annotationCustom() {
		System.out.println("I have the annotation @CustomAnnotation");
	}
	
	
	@Around("getEntityBy()")
	public Object aroundGetUserById(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null; 
		
		try {
			result = joinPoint.proceed();
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	
	

	
	
	
	
	
	
	
	
	
}
