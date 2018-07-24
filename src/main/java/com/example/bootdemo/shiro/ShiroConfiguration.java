package com.example.bootdemo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfiguration {
	/**
	* ShiroFilterFactoryBean 处理拦截资源文件问题。
	* 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	* 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	*
	Filter Chain定义说明
	1、一个URL可以配置多个Filter，使用逗号分隔
	2、当设置多个过滤器时，全部验证通过，才视为通过
	3、部分过滤器可指定参数，如perms，roles
	*
	*/
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		//配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
		//authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	/**
	* 身份认证realm
	* @return 
	*/ 
	@Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
	
	/**
	* 凭证匹配器 
	* （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 
	* 所以我们需要修改下doGetAuthenticationInfo中的代码;）
	* @return 
	* */ 
	@Bean 
	public HashedCredentialsMatcher hashedCredentialsMatcher(){ 
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//散列算法:这里使用MD5算法; 
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}



    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    
    /**
    * 开启shiro aop注解支持. 
    * 使用代理方式;所以需要开启代码支持; 
    * @param securityManager 
    * @return 
    */ 
    @Bean 
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){ 
    	AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor(); 
    	authorizationAttributeSourceAdvisor.setSecurityManager(securityManager); 
    	return authorizationAttributeSourceAdvisor;
    }

}
