package com.user.auth.Aop;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
@Aspect
@Component
public class AuthAop {
 
    private final Logger logger = LoggerFactory.getLogger(AuthAop.class);
 
    @Before("execution(* com.user.auth.userdata.UserDataService.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().toShortString());
        System.out.println("Entering method: " + joinPoint.getSignature().toShortString());
    }
 
    @AfterThrowing(pointcut = "execution(* com.user.auth.userdata.UserDataService.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception in method: " + joinPoint.getSignature().toShortString() + " => " + exception.getMessage());
        System.out.println("Exception in method: " + joinPoint.getSignature().toShortString() + " => " + exception.getMessage());
    }
}
