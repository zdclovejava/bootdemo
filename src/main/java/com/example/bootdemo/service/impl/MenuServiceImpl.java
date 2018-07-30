package com.example.bootdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bootdemo.dao.SysMenuMapper;

import com.example.bootdemo.model.SysMenu;
import com.example.bootdemo.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private SysMenuMapper menuMapper;
	
	@Override
	public List<SysMenu> findMenuByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return menuMapper.findByUserId(userId);
	}

}
