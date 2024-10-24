package ru.tbank.edu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(ru.tbank.edu.aop.LogExecutionTime) || @within(ru.tbank.edu.aop.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        logger.info("Method called: {} in class: {} executed in {} ms",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                executionTime);

        return proceed;
    }
}
