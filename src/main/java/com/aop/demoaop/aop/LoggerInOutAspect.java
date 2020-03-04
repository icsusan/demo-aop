package com.aop.demoaop.aop;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggerInOutAspect {
	
    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))")
    public void logBefore(final JoinPoint joinPoint) throws Throwable {
    	
    	try {
			
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes())
                    .getRequest();

           	Object [] args = joinPoint.getArgs();
           	if (Objects.nonNull(args) && args.length > 0) {
            	Object requestObject = args[0]; 
            	log.info(
                        "{} {} será ejecutado desde el host {} con request {}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        requestObject.toString());
           	} else {
            	log.info(
                        "{} {} será ejecutado desde el host {}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getRemoteAddr());
           	}
           	
		} catch (Exception e) {
			log.info("Se presentó una incidencia en la ejecución del aspecto @Before", e);
		}
    	
    }
    
    @AfterReturning(pointcut = "execution(* com.aop.demoaop.rest.controller.TaskController.*(..))", returning = "responseObject")    
    public void logAfterReturning(final JoinPoint joinPoint, Object responseObject) throws Throwable {
		
    	try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes())
                    .getRequest();
            
            if (Objects.nonNull(responseObject)) {
            	
            	log.info(
                        "{} {} fue ejecutado desde el host {} con response {}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        responseObject.toString());
            	
            } else {
            	
            	log.info(
                        "{} {} fue ejecutado desde el host {}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getRemoteAddr());
            	
            }
            
		} catch (Exception e) {
			log.info("Se presentó una incidencia en la ejecución del aspecto @AfterReturning", e);
		}
    			
    }
    
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}
	
	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}
    
	@AfterThrowing(pointcut = "requestMapping() && allMethod()", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		
		try {
			
			log.error("En el método {}.{} fue lanzada la excepción {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), exception.getClass());
			log.error("La causa es : " + exception.getCause());
			
		} catch (Exception e) {
			log.info("Se presentó una incidencia en la ejecución del aspecto @AfterThrowing", e);
		}
		
	}
    
}
