package com.geekbrains.geekspring.controllers.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductAspects {
    @Before("execution(public * com.geekbrains.geekspring.controllers.ProductController.*(..))")
    public void allProductControllerMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from ProductController");
    }

    @Before("execution(public * com.geekbrains.geekspring.entities.Product.*(..))")
    public void allProductEntityMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from ProductEntity method");
    }

    @Before("execution(public * com.geekbrains.geekspring.repositories.ProductRepository.*(..))")
    public void allProductRepositoryMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from ProductRepository method");
    }

    @Before("execution(public * com.geekbrains.geekspring.services.ProductService.*(..))")
    public void allProductServiceMethodsLogging(JoinPoint joinPoint) {
        System.out.println("Calling "+ joinPoint.getSignature() + " from ProductService method");
    }
}
