package com.example.bootdemo.ctrl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.bootdemo.model.SysUser;
import com.example.bootdemo.service.UserService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserCtrl {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping({"/"})
	@ResponseBody
	public Object index( @RequestParam(name = "pageNum", required = false, defaultValue = "1")
	    int pageNum,
	    @RequestParam(name = "pageSize", required = false, defaultValue = "1")
	    int pageSize){
		SysUser sysUser = new SysUser();
		sysUser.setPageNum(pageNum);
		sysUser.setPageSize(pageSize);
		return userService.findUserPage(sysUser);
	}
}
