package com.example.bootdemo.exception;

/**
 * 自定义业务异常类
 * 文件名称:     BusinessException.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年5月17日下午1:54:42 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年5月17日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message,Throwable cause){
		super(message,cause);
	}

}
