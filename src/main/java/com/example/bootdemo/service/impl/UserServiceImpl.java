package com.example.bootdemo.service.impl;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.bootdemo.dao.SysUserMapper;
import com.example.bootdemo.model.SysUser;
import com.example.bootdemo.service.UserService;
import com.github.pagehelper.PageInfo;

/**
 * 用户表业务类
 * 文件名称:     UserServiceImpl.java
 * 内容摘要: 
 * @author:   Zeng Dongcheng
 * @version:  1.0  
 * @Date:     2018年6月27日下午4:38:01 
 * 
 * 修改历史:  
 * 修改日期                     修改人员                                   版本	            修改内容  
 * ----------------------------------------------  
 * 2018年6月27日     Zeng Dongcheng   1.0     新建
 *
 * 版权:   版权所有(C)2018
 * 公司:   深圳市至高通信技术发展有限公司
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	 @Autowired
	 private SysUserMapper userMapper;
	 
	 @Resource
	 private RedisTemplate<String, Object> redisTemplate;
	 
	@Override
	public PageInfo<SysUser> findUserPage(SysUser sysUser) {
		//将参数传给这个方法就可以实现物理分页了，非常简单。
        //PageHelper.startPage(pageNum, pageSize);
        List<SysUser> users =  userMapper.selectByPageNumSize(sysUser);
        return new PageInfo<SysUser>(users);
	}
	
	@Override
	public SysUser findUserByLoginName(String loginName){
		return userMapper.selectByLoginName(loginName);
	}

	@CachePut(value = "user", key = "'id_'+#user.userId")
	@Override
	public boolean addUser(SysUser user) {
		return userMapper.insert(user)>0;
	}
	
	@CachePut(value = "user", key = "'id_'+#user.userId")
	@Override
	public boolean updUser(SysUser user) {
		return userMapper.updateByPrimaryKey(user)>0;
	}

	/**
	 * 不缓存null
	 */
	@Cacheable(value = "user", key = "'id_'+#userId",unless="#result == null")
	@Override
	public SysUser getUser(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}


	@CacheEvict(value = "user", key = "'id_'+#userId")
	@Override
	public boolean delUser(Integer userId) {
		return userMapper.deleteByPrimaryKey(userId)>0;
	}

}
