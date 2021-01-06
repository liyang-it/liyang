package com.liyang.controller;

import com.liyang.json.jsonResult;
import com.liyang.tools.httpRequestTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局错误处理控制器
 */
@RestControllerAdvice
@CrossOrigin
  public class exceptionController {
    private Logger log = LoggerFactory.getLogger(exceptionController.class);

    /**
     * 404 异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public jsonResult exceptionError404(NoHandlerFoundException e, HttpServletRequest request){
      String ip = httpRequestTools.getIpAddr(request);
      String  msg = "失败！找不到相关接口[{"+request.getRequestURL()+"},{"+ip+"}]";
      log.error(msg);
      return jsonResult.resultError(msg);
    }
    // 参数校验异常
    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    public jsonResult exceptionBr(BindException e){
      StringBuilder sb = new StringBuilder();
      for (ObjectError error: e.getBindingResult().getAllErrors()) {
        sb.append(error.getDefaultMessage());
        sb.append("、");
      }
      String errorMsg = sb.substring(0,sb.length() - 1).toString();
      return jsonResult.resultError(errorMsg);
    }
  /**
   * 全局异常
   */
  @ExceptionHandler(value = Exception.class)
  public jsonResult exceptionError(Exception e){
    String msg = e.getMessage();
    log.error(msg);
    return jsonResult.resultError(msg);
  }

}
