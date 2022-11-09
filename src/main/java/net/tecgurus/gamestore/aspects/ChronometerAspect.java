package net.tecgurus.gamestore.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Aspect
@Component
public class ChronometerAspect {
	//TODO: que pasa con variables globales en diferentes hilos
	//private Long startTime;
	//private Long finishTime;
	
	@Pointcut("execution(* net.tecgurus.gamestore.service.*.*(..))")
	private void pointcutService() {}
	
	@Pointcut("execution(* net.tecgurus.gamestore.dao.*.*(..))")
	private void pointcutDao() {}
	
	@Around("pointcutService() || pointcutDao()")
	public Object afterChronometer(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		
		Object response =joinPoint.proceed();
		
		long finishTime = System.currentTimeMillis();
		long diff = finishTime - startTime;
		
		Signature signature = joinPoint.getSignature();
		
		String className = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		
		log.info(className + " [" + methodName + "] Tiempo de ejecucion: " + diff + " milisegundos");
		
		return response;
	}
}
