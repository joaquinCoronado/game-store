package net.tecgurus.gamestore.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChronometerAspect {
	
	@Pointcut("execution(* net.tecgurus.gamestore.service.*.*(..))")
	//@Pointcut("execution(* net.tecgurus.gamestore.service.*.*(..))")
	private void pointcutExample() {}
	
	//@After("")
	//@AfterThrowing("")
	@Before("pointcutExample()")
	public void aspectExample(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		
		String aspectClass = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		
		System.out.println(aspectClass +" - "+ methodName + " - "+ "Example Aspect");
	}

}
