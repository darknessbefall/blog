package com.cwb.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@ControllerAdvice           //拦截所有标注Controller的控制器
public class ControllerExceptionHandler {

    //获取日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //标识这个方法是做异常处理的(只要是Exception级别的都可以)
    public ModelAndView ExceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //记录日志信息
        logger.error("Request URL : {}, Exception : {}",request.getRequestURI(),e); //{} 有占位符的作用

        //用注解工具类,找异常状态码的注解进行判断,解除对有异常状态码的控制器解除拦截,让SpringBoot自己去处理
        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class) != null) {
                throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI() );
        mv.addObject("exception",e );
        mv.setViewName("error/error");

        return mv;

    }

}
