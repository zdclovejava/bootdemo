package com.example.bootdemo.service;
import com.example.bootdemo.model.SysUser;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	public PageInfo<SysUser> findUserPage(SysUser sysUser);
	
	public SysUser findUserByLoginName(String loginName);
	
	public boolean addUser(SysUser user);
	
	public SysUser getUser(Integer userId);
	
	public boolean updUser(SysUser user);
	
	public boolean delUser(Integer userId);
}
