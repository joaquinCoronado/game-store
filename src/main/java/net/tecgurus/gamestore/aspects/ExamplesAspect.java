package net.tecgurus.gamestore.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
	
	//@Before("startWithGreeting()")
	@Before("startWithHello()")
	public void beforeCombiningPointCuts() {
		System.out.println("Advice @Before - CombiningPointCuts");
	}
	
	
}
