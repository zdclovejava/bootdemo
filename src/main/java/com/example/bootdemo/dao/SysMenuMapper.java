package com.example.bootdemo.dao;

import java.util.List;

import com.example.bootdemo.model.SysMenu;

public interface SysMenuMapper {

    int deleteByPrimaryKey(Integer menuId);
    
    List<SysMenu> findByUserId(Integer userId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectMenus(SysMenu sysMenu);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}