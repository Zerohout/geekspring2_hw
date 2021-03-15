package com.geekbrains.geekspring.controllers.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspects {
    @Before("execution(public * com.geekbrains.geekspring.entities.Order.*(..))")
    public void allOrderEntityMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from OrderEntity method");
    }

    @Before("execution(public * com.geekbrains.geekspring.repositories.OrderRepository.*(..))")
    public void allOrderRepositoryMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from OrderRepository method");
    }

    @Before("execution(public * com.geekbrains.geekspring.services.OrderService.*(..))")
    public void allOrderServiceMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from OrderService method");
    }
}
