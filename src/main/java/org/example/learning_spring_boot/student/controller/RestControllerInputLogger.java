package org.example.learning_spring_boot.student.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RestControllerInputLogger {

    private static final Logger logger = LoggerFactory.getLogger(RestControllerInputLogger.class);

    // Before any controller method runs
    @Before("execution(* org.example.learning_spring_boot.student.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        String arguments = Arrays.toString(joinPoint.getArgs());

        logger.info("Executing method: {} with arguments: {}", methodName, arguments);
    }

    // After successful method execution
    @AfterReturning(pointcut = "execution(* org.example.learning_spring_boot.student.controller..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();

        logger.info("Method: {} executed successfully with result: {}", methodName, result);
    }

    // After exception is thrown
    @AfterThrowing(pointcut = "execution(* org.example.learning_spring_boot.student.controller..*(..))", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();

        logger.error("Method: {} thrown an exception: {}", methodName, exception.getMessage());
    }
}
