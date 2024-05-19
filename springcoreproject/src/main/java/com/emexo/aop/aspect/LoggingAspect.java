package com.emexo.aop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.emexo.aop.dao.PassengerDAOImpl.getPassenger(..))")
    public void before(JoinPoint joinPoint) {
        Object[] methodArgs = joinPoint.getArgs();
        log.info("Input to Method :{} with args :{}", joinPoint.getSignature().getName(), methodArgs[0]);
    }

}
