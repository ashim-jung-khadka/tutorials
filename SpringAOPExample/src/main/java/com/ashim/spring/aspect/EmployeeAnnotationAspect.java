package com.ashim.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeAnnotationAspect {

	@Before("@annotation(com.ashim.spring.aspect.Loggable)")
	public void myAdvice() {
		System.out.println("Executing myAdvice!!");
	}
}
