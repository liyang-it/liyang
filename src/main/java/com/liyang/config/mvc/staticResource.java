package com.liyang.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class staticResource extends WebMvcConfigurerAdapter  {
  /*
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    //registry.addViewController("/").setViewName("index");
    //registry.addViewController("/index.html").setViewName("index");
  }



  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    //需要配置2：----------- 告知拦截器：哪些不需要拦截 （配置的是 路径）
    registry.addInterceptor(new requestInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**")
            .excludePathPatterns("/templates/**")
    ;

  }
*/
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
    //第一个方法设置访问路径前缀，第二个方法设置资源路径
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
  }
}
