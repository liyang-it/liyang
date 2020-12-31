package com.liyang.config.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Aspect // 使用注解声明 此类为切面类
@Component
public class MyAspect {
  //execution(public * com.liyang.controller..*(..)) 表示 匹配 controller 下的所有包和包子类的所有方法
  //execution(public * com.liyang.controller.class.*(..)) 表示 匹配 class类的所有方法
  @Pointcut(value = "execution(public * com.liyang.controller..*(..))")
  public void poincut1(){}

  @Before(value = "poincut1()")
  public void methodBefore1(){
    HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    response.setHeader("cache-control:","public");
    response.setHeader("cache-control:","max-age=36000");
  }
}
