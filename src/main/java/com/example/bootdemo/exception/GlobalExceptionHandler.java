package com.example.bootdemo.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理类
 * 文件名称:     GlobalExceptionHandler.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月27日下午1:54:40 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月27日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 处理所有未知异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	String handleException(Exception e){
		LOGGER.error("----------出错了，系统错误：{}", e.getMessage());
		return e.getMessage();
	}
	
	
	/**
	 * 处理所有业务异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	String handleBusinessException(BusinessException e){
		LOGGER.error("----------出错了，业务异常：{}", e.getMessage());
		return e.getMessage();
	}
	
}
