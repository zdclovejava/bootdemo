package com.example.bootdemo.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.bootdemo.common.CommonResult;
import com.example.bootdemo.config.TestConfig;
import com.example.bootdemo.model.SysUser;
import com.example.bootdemo.service.UserService;


@Controller
@RequestMapping(value = "/user")
public class HelloCtrl {
	
	private static final Logger Log=LoggerFactory.getLogger(HelloCtrl.class);

	@Autowired
	private UserService userService;
	@Autowired
	private TestConfig testConfig;
	
	@Autowired  
    private StringRedisTemplate template;  
	
	@RequestMapping("/hello")
	public ModelAndView hello(){
		Log.info("------info 进入hello------");
		Log.warn("------warn 进入hello------");
		Log.error("------error 进入hello------");
		Log.debug("-----test.name={}-------",testConfig.getName());
		ModelAndView view = new ModelAndView();
		view.addObject("hello", "hello hhhhhh");
		view.setViewName("index");
		return view;
	}
	
	@RequiresPermissions("sys:logs:view")
	@RequestMapping("/all")
	@ResponseBody
	public Object findAllUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
	    int pageNum,
	    @RequestParam(name = "pageSize", required = false, defaultValue = "1")
	    int pageSize){
		SysUser sysUser = new SysUser();
		sysUser.setPageNum(pageNum);
		sysUser.setPageSize(pageSize);
		return userService.findUserPage(sysUser);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object addUser(@RequestParam(name = "loginName", required = false, defaultValue = "1") String loginName,
			HttpServletRequest request){
		Log.debug("-------添加用户--------");
		SysUser user=new SysUser();
		user.setLoginName(loginName);
		user.setUserName("张三"+loginName);
		userService.addUser(user);
		return CommonResult.COM_SYS_OK;
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public Object updUser(@RequestParam(name = "loginName", required = false, defaultValue = "1") String loginName,
			HttpServletRequest request){
		Log.debug("-------添加用户--------");
		SysUser user=new SysUser();
		user.setLoginName(loginName);
		user.setUserName("李四"+loginName);
		userService.updUser(user);
		return CommonResult.COM_SYS_OK;
		
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Object getUser(@RequestParam(name = "userId", required = false, defaultValue = "1") Integer userId,
			HttpServletRequest request){
		Log.debug("-------获取用户--------");
		SysUser user = userService.getUser(userId);
		Log.debug("RedisTest执行完成，userName= {}",user.getUserName());
		return user;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Object delUser(@RequestParam(name = "userId", required = false, defaultValue = "1") Integer userId,
			HttpServletRequest request){
		Log.debug("-------添加用户--------");
		userService.delUser(userId);
		return CommonResult.COM_SYS_OK;
		
	}
	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(@RequestParam(name = "userName", required = false, defaultValue = "zhangsan") String userName,
			HttpServletRequest request){
		Log.debug("--------系统登录--------");
		SysUser sysUser = new SysUser();
		sysUser.setUserName(userName);
		HttpSession session =  request.getSession();
		Log.debug("-----------sessionId={}",session.getId());
		session.setAttribute("sysUser", sysUser);
		return CommonResult.COM_SYS_OK;
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(HttpServletRequest request){
		Log.debug("--------系统退出--------");
		request.getSession().removeAttribute("sysUser");
		Log.debug("-----------sessionId={}",request.getSession().getId());
		return CommonResult.COM_SYS_OK;
	}
	
	
	
	
      
    @RequestMapping("/setValue")
    @ResponseBody
    public String setValue(){  
        if(!template.hasKey("shabao")){  
            template.opsForValue().set("shabao", "我是傻宝");  
            return "使用redis缓存保存数据成功";  
        }else{  
            template.delete("shabao");  
            return "key已存在";  
        }  
    }  
      
    @RequestMapping("/getValue")
    @ResponseBody
    public String getValue(){  
          
        if(!template.hasKey("shabao")){  
            return "key不存在，请先保存数据";  
        }else{  
            String shabao = (String) template.opsForValue().get("shabao");//根据key获取缓存中的val   
            return "获取到缓存中的数据：shabao="+shabao;  
        }  
    }  
	
	
}
