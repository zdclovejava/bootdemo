package com.example.bootdemo.common;

public final class CommonResult {
	
	private String ret_code;
	private String ret_msg;
	
	public static final CommonResult COM_SYS_OK = new CommonResult("00","成功！");
	public static final CommonResult COM_SYS_ERR = new CommonResult("1001","系统错误");
	public static final CommonResult COM_SYS_EXCEPTION = new CommonResult("","");
	public static final CommonResult COM_DATA_ERR = new CommonResult("","");
	public static final CommonResult COM_DATA_EXCEPTION = new CommonResult("","");
	public static final CommonResult COM_CHANNEL_ERR = new CommonResult("","");
	
	private CommonResult(String retCode,String retMsg){
		this.setRet_code(retCode);
		this.setRet_msg(retMsg);
	}
	
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	
	
	
}
