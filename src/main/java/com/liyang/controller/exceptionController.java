package com.liyang.controller;

import com.liyang.json.jsonResult;
import com.liyang.tools.httpRequestTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
