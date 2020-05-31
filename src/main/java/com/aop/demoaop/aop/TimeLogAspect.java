package com.aop.demoaop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeLogAspect {
	
    @Around("@annotation(com.aop.demoaop.annotation.Timed) && execution(public * *(..))")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
    	
        long start = System.currentTimeMillis();
        Object value;

        try {
        
        	value = joinPoint.proceed();
        
        } catch (Throwable throwable) {
        
        	throw throwable;
        	
        } finally {
        	
            long duration = System.currentTimeMillis() - start;

            log.info(
                    "{}.{} tomó {} ms",
                    joinPoint.getSignature().getDeclaringType().getSimpleName(),
                    joinPoint.getSignature().getName(),
                    duration);
        }

        return value;
    }
    
}
