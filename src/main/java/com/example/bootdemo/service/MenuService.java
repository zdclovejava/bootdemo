package com.example.bootdemo.service;


import java.util.List;

import com.example.bootdemo.model.SysMenu;

public interface MenuService {
	public List<SysMenu> findMenuByUserId(Integer userId);
}
