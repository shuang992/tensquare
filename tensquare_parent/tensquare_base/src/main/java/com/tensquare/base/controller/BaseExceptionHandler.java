package com.tensquare.base.controller;

import entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 如果捕获到异常以json形式进行返回
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Result error(Exception e){
     return  new Result(false,2001,"错误信息:"+e.getMessage());
    }
}
